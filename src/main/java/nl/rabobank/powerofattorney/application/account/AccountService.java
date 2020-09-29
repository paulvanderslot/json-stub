package nl.rabobank.powerofattorney.application.account;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.github.tomakehurst.wiremock.admin.NotFoundException;

import lombok.NonNull;
import nl.rabobank.powerofattorney.application.AuthorizationService;
import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.AccountId;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.exceptions.UnauthorizedException;

@Service
public class AccountService {
    private AuthorizationService authorizationService;
    private final AccountRepository repository;

    AccountService(AuthorizationService authorizationService, AccountRepository repository) {
        this.authorizationService = authorizationService;
        this.repository = repository;
    }

    public Account getForId(@NonNull AccountId accountId, User loggedInUser) {
        Account account = repository.find(accountId).orElseThrow(notFound(accountId));

        if (!authorizationService.isAllowedToView(loggedInUser, account)) {
            throw new UnauthorizedException(loggedInUser.getName() + " is not allowed to view account " + accountId);
        }

        return account;
    }

    private Supplier<NotFoundException> notFound(@NonNull AccountId accountId) {
        return () -> new NotFoundException("Account with " + accountId + " not found");
    }
}
