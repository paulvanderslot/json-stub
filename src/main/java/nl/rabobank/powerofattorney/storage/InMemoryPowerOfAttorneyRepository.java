package nl.rabobank.powerofattorney.storage;

import static nl.rabobank.powerofattorney.domain.Authorization.PAYMENT;
import static nl.rabobank.powerofattorney.domain.Authorization.VIEW;
import static nl.rabobank.powerofattorney.domain.attorney.Direction.GIVEN;
import static nl.rabobank.powerofattorney.domain.attorney.Direction.RECEIVED;
import static nl.rabobank.powerofattorney.storage.Constants.ARAGORN;
import static nl.rabobank.powerofattorney.storage.Constants.BOROMIR;
import static nl.rabobank.powerofattorney.storage.Constants.FRODO;
import static nl.rabobank.powerofattorney.storage.Constants.SUPER_DUPER_COMPANY;
import static nl.rabobank.powerofattorney.storage.Constants.SUPER_DUPER_EMPLOYEE;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Repository;

import nl.rabobank.powerofattorney.application.attorney.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.domain.Authorization;
import nl.rabobank.powerofattorney.domain.account.AccountId;
import nl.rabobank.powerofattorney.domain.attorney.GroupGrantee;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorneyId;
import nl.rabobank.powerofattorney.domain.attorney.SingleGrantee;
import nl.rabobank.powerofattorney.domain.card.CardId;
import nl.rabobank.powerofattorney.domain.card.CardSummary;
import nl.rabobank.powerofattorney.domain.card.CardType;

@Repository
public class InMemoryPowerOfAttorneyRepository implements PowerOfAttorneyRepository {
    private final List<PowerOfAttorney> allPowerOfAttorneys = createPowerOfAttorneys();

    @Override public Collection<PowerOfAttorney> findAll() {
        return allPowerOfAttorneys;
    }

    @Override public Optional<PowerOfAttorney> findForId(PowerOfAttorneyId id) {
        return allPowerOfAttorneys.stream().filter(matchingId(id)).findAny();
    }

    @Override public Optional<PowerOfAttorney> findForCardId(CardId cardId) {
        return allPowerOfAttorneys.stream().filter(matchingCardId(cardId)).findAny();
    }

    @Override public Optional<PowerOfAttorney> findForAccountId(AccountId accountId) {
        return allPowerOfAttorneys.stream().filter(matchingAccountId(accountId)).findAny();
    }

    private Predicate<? super PowerOfAttorney> matchingId(PowerOfAttorneyId id) {
        return poa -> poa.getId().equals(id);
    }

    private Predicate<PowerOfAttorney> matchingCardId(CardId cardId) {
        return poa -> poa.getCardSummaries().stream().map(CardSummary::getCardId).anyMatch(id -> id.equals(cardId));
    }

    private Predicate<PowerOfAttorney> matchingAccountId(AccountId accountId) {
        return poa -> poa.getAccountId().equals(accountId);
    }

    private List<PowerOfAttorney> createPowerOfAttorneys() {
        return List.of(
                PowerOfAttorney.builder()
                        .id(new PowerOfAttorneyId("0001"))
                        .grantor(SUPER_DUPER_COMPANY)
                        .grantee(new GroupGrantee("Fellowship of the ring",
                                List.of(BOROMIR, ARAGORN, FRODO)))
                        .accountId(new AccountId("123456789"))
                        .direction(GIVEN)
                        .authorizations(List.of(Authorization.DEBIT_CARD, VIEW, PAYMENT))
                        .cardSummaries(List.of(new CardSummary(new CardId("1111"), CardType.DEBIT_CARD),
                                new CardSummary(new CardId("2222"), CardType.DEBIT_CARD),
                                new CardSummary(new CardId("3333"), CardType.CREDIT_CARD)))
                        .build()
                , PowerOfAttorney.builder()
                        .id(new PowerOfAttorneyId("0002"))
                        .grantor(SUPER_DUPER_COMPANY)
                        .grantee(new SingleGrantee(SUPER_DUPER_EMPLOYEE.getName()))
                        .accountId(new AccountId("987654321"))
                        .direction(GIVEN)
                        .authorizations(List.of(Authorization.DEBIT_CARD, VIEW, PAYMENT))
                        .cardSummaries(List.of(new CardSummary(new CardId("4444"), CardType.DEBIT_CARD)))
                        .build()
                , PowerOfAttorney.builder()
                        .id(new PowerOfAttorneyId("0003"))
                        .grantor(SUPER_DUPER_COMPANY)
                        .grantee(new SingleGrantee(SUPER_DUPER_EMPLOYEE.getName()))
                        .accountId(new AccountId("343434343"))
                        .direction(GIVEN)
                        .authorizations(List.of(VIEW, PAYMENT))
                        .cardSummaries(List.of())
                        .build()
                , PowerOfAttorney.builder()
                        .id(new PowerOfAttorneyId("0004"))
                        .grantor(SUPER_DUPER_EMPLOYEE)
                        .grantee(new SingleGrantee(SUPER_DUPER_EMPLOYEE.getName()))
                        .accountId(new AccountId("123123123"))
                        .direction(RECEIVED)
                        .authorizations(List.of(VIEW, PAYMENT))
                        .cardSummaries(List.of())
                        .build());
    }
}
