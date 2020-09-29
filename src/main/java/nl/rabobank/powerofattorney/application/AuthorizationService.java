package nl.rabobank.powerofattorney.application;

import static nl.rabobank.powerofattorney.domain.Authorization.VIEW;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import nl.rabobank.powerofattorney.domain.Authorization;
import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;

@Service
public class AuthorizationService {
    private final PowerOfAttorneyRepository powerOfAttorneyRepository;

    public AuthorizationService(PowerOfAttorneyRepository powerOfAttorneyRepository) {
        this.powerOfAttorneyRepository = powerOfAttorneyRepository;
    }

    public boolean isAllowedToView(User loggedInUser, Account account) {
        if (loggedInUser.equals(account.getOwner())) {
            return true;
        }
        return hasPowerOfAttorneyToView(loggedInUser, account);
    }

    private boolean hasPowerOfAttorneyToView(User loggedInUser, Account account) {
        return powerOfAttorneyRepository.findAll().stream()
                .filter(matchingAccount(account))
                .filter(authorizedToView())
                .anyMatch(isGrantedAccess(loggedInUser));
    }

    private Predicate<PowerOfAttorney> isGrantedAccess(User loggedInUser) {
        return poa -> poa.getGrantee().equals(loggedInUser);
    }

    private Predicate<PowerOfAttorney> authorizedToView() {
        return poa -> poa.getAuthorizations().contains(VIEW);
    }

    private Predicate<PowerOfAttorney> matchingAccount(Account account) {
        return poa -> poa.getAccountId().equals(account.getId());
    }
}
