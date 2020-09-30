package nl.rabobank.powerofattorney.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.rabobank.powerofattorney.application.attorney.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.domain.Authorization;
import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.AccountId;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.attorney.Direction;
import nl.rabobank.powerofattorney.domain.attorney.Grantee;
import nl.rabobank.powerofattorney.domain.attorney.GroupGrantee;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorneyId;
import nl.rabobank.powerofattorney.domain.attorney.SingleGrantee;

class AuthorizationServiceTest {

    private static final User LOGGED_IN = new User("loggedIn");
    private static final Grantee GROUP_OF_LOGGED_IN = new GroupGrantee("Group", List.of(LOGGED_IN));
    private static final User OTHER = new User("other");
    private static final AccountId ACCOUNT_ID = new AccountId("111111111");

    private AuthorizationService authorizationService;
    private PowerOfAttorneyRepository repositoryMock;

    @BeforeEach
    void before() {
        repositoryMock = mock(PowerOfAttorneyRepository.class);
        authorizationService = new AuthorizationService(repositoryMock);
    }

    @Test
    void isAuthorized_whenAccountOwnerIsLoggedInUser() {
        boolean allowedToView = authorizationService.isAllowedToView(LOGGED_IN, account(LOGGED_IN));

        assertThat(allowedToView).isTrue();
    }

    @Test
    void isAuthorized_whenGotPowerOfAttorney() {
        when(repositoryMock.findForAccountId(any())).thenReturn(Optional.of(powerOfAttorney(LOGGED_IN)));

        boolean allowedToView = authorizationService.isAllowedToView(LOGGED_IN, account(OTHER));

        assertThat(allowedToView).isTrue();
    }

    @Test
    void isNotAuthorized_whenGotNoPowerOfAttorney() {
        when(repositoryMock.findForAccountId(any())).thenReturn(Optional.of(powerOfAttorney(OTHER)));

        boolean allowedToView = authorizationService.isAllowedToView(LOGGED_IN, account(OTHER));

        assertThat(allowedToView).isFalse();
    }

    @Test
    void isAuthorized_whenGotPowerOfAttorneyForGroup() {
        when(repositoryMock.findForAccountId(any())).thenReturn(Optional.of(powerOfAttorney(GROUP_OF_LOGGED_IN)));

        boolean allowedToView = authorizationService.isAllowedToView(LOGGED_IN, account(OTHER));

        assertThat(allowedToView).isTrue();
    }

    private PowerOfAttorney powerOfAttorney(User singleUser) {
        return powerOfAttorney(new SingleGrantee(singleUser.getName()));
    }

    private PowerOfAttorney powerOfAttorney(Grantee grantee) {
        return PowerOfAttorney.builder()
                .id(new PowerOfAttorneyId("id"))
                .grantee(grantee)
                .grantor(OTHER)
                .accountId(ACCOUNT_ID)
                .direction(Direction.GIVEN)
                .cardSummaries(List.of())
                .authorizations(List.of(Authorization.VIEW))
                .build();
    }

    private Account account(User loggedIn) {
        return Account.builder()
                .id(ACCOUNT_ID)
                .owner(loggedIn)
                .balance(0)
                .createDate(LocalDate.now())
                .build();
    }
}