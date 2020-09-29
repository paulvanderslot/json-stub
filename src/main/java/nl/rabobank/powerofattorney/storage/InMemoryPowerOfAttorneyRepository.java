package nl.rabobank.powerofattorney.storage;

import static nl.rabobank.powerofattorney.domain.Authorization.DEBIT_CARD;
import static nl.rabobank.powerofattorney.domain.Authorization.PAYMENT;
import static nl.rabobank.powerofattorney.domain.Authorization.VIEW;
import static nl.rabobank.powerofattorney.domain.attorney.Direction.GIVEN;
import static nl.rabobank.powerofattorney.domain.attorney.Direction.RECEIVED;
import static nl.rabobank.powerofattorney.storage.Constants.SUPER_DUPER_COMPANY;
import static nl.rabobank.powerofattorney.storage.Constants.SUPER_DUPER_EMPLOYEE;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import nl.rabobank.powerofattorney.application.attorney.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.domain.account.AccountId;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorneyId;
import nl.rabobank.powerofattorney.domain.card.CardId;

@Repository
public class InMemoryPowerOfAttorneyRepository implements PowerOfAttorneyRepository {
    private final List<PowerOfAttorney> allPowerOfAttorneys = createPowerOfAttorneys();

    @Override public Collection<PowerOfAttorney> findAll() {
        return allPowerOfAttorneys;
    }

    private List<PowerOfAttorney> createPowerOfAttorneys() {
        return List.of(
                PowerOfAttorney.builder()
                        .id(new PowerOfAttorneyId("0001"))
                        .grantor(SUPER_DUPER_COMPANY)
                        .grantee(new User("Fellowship of the ring")) //TODO: User group ipv User
                        .accountId(new AccountId("123456789"))
                        .direction(GIVEN)
                        .authorizations(List.of(VIEW, PAYMENT, DEBIT_CARD))
                        .cardIds(List.of(new CardId("1111"), new CardId("2222"), new CardId("3333")))
                        .build()
                , PowerOfAttorney.builder()
                        .id(new PowerOfAttorneyId("0002"))
                        .grantor(SUPER_DUPER_COMPANY)
                        .grantee(SUPER_DUPER_EMPLOYEE)
                        .accountId(new AccountId("987654321"))
                        .direction(GIVEN)
                        .authorizations(List.of(VIEW, PAYMENT, DEBIT_CARD))
                        .cardIds(List.of(new CardId("4444")))
                        .build()
                , PowerOfAttorney.builder()
                        .id(new PowerOfAttorneyId("0003"))
                        .grantor(SUPER_DUPER_COMPANY)
                        .grantee(SUPER_DUPER_EMPLOYEE)
                        .accountId(new AccountId("343434343"))
                        .direction(GIVEN)
                        .authorizations(List.of(VIEW, PAYMENT))
                        .cardIds(List.of())
                        .build()
                , PowerOfAttorney.builder()
                        .id(new PowerOfAttorneyId("0004"))
                        .grantor(SUPER_DUPER_EMPLOYEE)
                        .grantee(SUPER_DUPER_COMPANY)
                        .accountId(new AccountId("123123123"))
                        .direction(RECEIVED)
                        .authorizations(List.of(VIEW, PAYMENT))
                        .cardIds(List.of())
                        .build());
    }
}
