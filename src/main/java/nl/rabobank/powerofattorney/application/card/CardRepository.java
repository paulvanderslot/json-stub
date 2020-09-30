package nl.rabobank.powerofattorney.application.card;

import java.util.Optional;

import nl.rabobank.powerofattorney.domain.card.CardId;
import nl.rabobank.powerofattorney.domain.card.CreditCard;
import nl.rabobank.powerofattorney.domain.card.DebitCard;

public interface CardRepository {
    Optional<CreditCard> findCreditCardFor(CardId id);

    Optional<DebitCard> findDebitCardFor(CardId id);

}
