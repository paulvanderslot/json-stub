package nl.rabobank.powerofattorney.storage;

import static nl.rabobank.powerofattorney.storage.Constants.SUPER_DUPER_COMPANY;
import static nl.rabobank.powerofattorney.storage.Constants.SUPER_DUPER_EMPLOYEE;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import nl.rabobank.powerofattorney.application.account.AccountRepository;
import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.AccountId;

@Repository
public class InMemoryAccountRepository implements AccountRepository {

    private final List<Account> allAccounts = createAccounts();

    @Override public Optional<Account> find(AccountId id) {
        return allAccounts.stream()
                .filter(account -> account.getId().equals(id))
                .findAny();
    }

    private List<Account> createAccounts() {
        return List.of(
                Account.builder()
                        .id(new AccountId("123123123"))
                        .owner(SUPER_DUPER_EMPLOYEE)
                        .balance(-125)
                        .createDate(LocalDate.of(2007, 10, 12))
                        .build(),
                Account.builder()
                        .id(new AccountId("123456789"))
                        .owner(SUPER_DUPER_COMPANY)
                        .balance(750)
                        .createDate(LocalDate.of(2007, 10, 12))
                        .build(),
                Account.builder()
                        .id(new AccountId("343434343"))
                        .owner(SUPER_DUPER_COMPANY)
                        .balance(6000)
                        .createDate(LocalDate.of(2007, 10, 12))
                        .build(),
                Account.builder()
                        .id(new AccountId("987654321"))
                        .owner(SUPER_DUPER_COMPANY)
                        .balance(0)
                        .createDate(LocalDate.of(2007, 10, 12))
                        .closedDate(LocalDate.of(2019, 9, 1))
                        .build());
    }
}
