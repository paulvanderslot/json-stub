package nl.rabobank.powerofattorney.storage;

import static nl.rabobank.powerofattorney.domain.card.CardStatus.ACTIVE;
import static nl.rabobank.powerofattorney.domain.card.Period.PER_DAY;
import static nl.rabobank.powerofattorney.domain.card.Period.PER_MONTH;
import static nl.rabobank.powerofattorney.domain.card.Period.PER_WEEK;
import static nl.rabobank.powerofattorney.storage.Constants.ARAGORN;
import static nl.rabobank.powerofattorney.storage.Constants.BOROMIR;
import static nl.rabobank.powerofattorney.storage.Constants.FRODO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import nl.rabobank.powerofattorney.application.cards.CardRepository;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.card.CardId;
import nl.rabobank.powerofattorney.domain.card.CardStatus;
import nl.rabobank.powerofattorney.domain.card.CreditCard;
import nl.rabobank.powerofattorney.domain.card.DebitCard;
import nl.rabobank.powerofattorney.domain.card.Limit;

@Repository
public class InMemoryCardRepository implements CardRepository {

    private final List<CreditCard> allCreditCards = createCreditCards();
    private final List<DebitCard> allDebitCards = createDebitCards();

    @Override public Optional<CreditCard> findCreditCardFor(CardId id) {
        return allCreditCards.stream().filter(card -> card.getId().equals(id)).findAny();
    }

    @Override public Optional<DebitCard> findDebitCardFor(CardId id) {
        return allDebitCards.stream().filter(card -> card.getId().equals(id)).findAny();
    }

    private List<CreditCard> createCreditCards() {
        return List.of(CreditCard.builder()
                .id(new CardId("3333"))
                .cardNumber(5075)
                .cardHolder(BOROMIR)
                .sequenceNumber(1)
                .status(ACTIVE)
                .monthlyLimit(3000)
                .build()
        );
    }

    private List<DebitCard> createDebitCards() {
        return List.of(DebitCard.builder()
                        .id(new CardId("1111"))
                        .cardNumber(1234)
                        .cardHolder(FRODO)
                        .sequenceNumber(5)
                        .status(ACTIVE)
                        .atmLimit(new Limit(3000, PER_WEEK))
                        .posLimit(new Limit(50, PER_MONTH))
                        .contactless(true)
                        .build(),
                DebitCard.builder()
                        .id(new CardId("2222"))
                        .cardNumber(6527)
                        .cardHolder(ARAGORN)
                        .sequenceNumber(1)
                        .status(ACTIVE)
                        .contactless(true)
                        .atmLimit(new Limit(100, PER_DAY))
                        .posLimit(new Limit(10000, PER_MONTH))
                        .build(),
                DebitCard.builder()
                        .id(new CardId("4444"))
                        .cardNumber(1111)
                        .cardHolder(Constants.SUPER_DUPER_EMPLOYEE)
                        .sequenceNumber(32)
                        .status(ACTIVE)
                        .contactless(false)
                        .atmLimit(new Limit(100, PER_DAY))
                        .posLimit(new Limit(10000, PER_MONTH))
                        .build(),
                DebitCard.builder()
                        .id(new CardId("5555"))
                        .cardNumber(5678)
                        .cardHolder(new User("Darth Vader"))
                        .sequenceNumber(5)
                        .status(CardStatus.BLOCKED)
                        .contactless(true)
                        .atmLimit(new Limit(500, PER_DAY))
                        .posLimit(new Limit(50, PER_MONTH))
                        .build()
        );
    }
}
