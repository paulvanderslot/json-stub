package nl.rabobank.powerofattorney.application.account;

import java.util.Optional;

import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.AccountId;

public interface AccountRepository {

    Optional<Account> find(AccountId id);
}
