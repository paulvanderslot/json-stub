package nl.rabobank.powerofattorney.application.account;

import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import nl.rabobank.powerofattorney.application.AuthorizationService;
import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.AccountId;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.exceptions.NotFoundException;
import nl.rabobank.powerofattorney.domain.exceptions.UnauthorizedException;

@Service
public class AccountsService {
    private final AuthorizationService authorizationService;
    private final AccountRepository repository;

    AccountsService(AuthorizationService authorizationService, AccountRepository repository) {
        this.authorizationService = authorizationService;
        this.repository = repository;
    }

    public Account getForId(@NonNull AccountId accountId,@NonNull User loggedInUser) {
        Account account = repository.find(accountId).orElseThrow(notFound(accountId));

        if (!authorizationService.isAllowedToView(loggedInUser, account)) {
            throw new UnauthorizedException(loggedInUser.getName() + " is not allowed to view account " + accountId);
        }

        return account;
    }

    private Supplier<NotFoundException> notFound(AccountId accountId) {
        return () -> new NotFoundException("Account with " + accountId + " not found");
    }
}
