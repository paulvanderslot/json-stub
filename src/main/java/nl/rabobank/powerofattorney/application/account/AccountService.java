package nl.rabobank.powerofattorney.application.account;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.tomakehurst.wiremock.admin.NotFoundException;

import lombok.NonNull;
import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.AccountId;

@Service
public class AccountService {
    private final AccountRepository repository;

    AccountService(AccountRepository repository){
        this.repository = repository;
    }

    public Account getForId(@NonNull AccountId accountId){
        // TODO authorization

        Optional<Account> account = repository.find(accountId);
        return account.orElseThrow(()-> new NotFoundException("Account with " + accountId + " not found"));
    }
}
