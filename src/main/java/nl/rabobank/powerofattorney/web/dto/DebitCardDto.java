package nl.rabobank.powerofattorney.web.dto;

import lombok.Value;
import nl.rabobank.powerofattorney.domain.card.CardStatus;
import nl.rabobank.powerofattorney.domain.card.DebitCard;
import nl.rabobank.powerofattorney.domain.card.Limit;

@Value
public class DebitCardDto {
    String id;
    CardStatus status;
    long cardNumber;
    long sequenceNumber;
    String cardHolder;
    Limit atmLimit;
    Limit posLimit;
    boolean contactless;

    public DebitCardDto(DebitCard debitCard) {
        this.id = debitCard.getId().getId();
        this.status = debitCard.getStatus();
        this.cardNumber = debitCard.getCardNumber();
        this.sequenceNumber = debitCard.getSequenceNumber();
        this.cardHolder = debitCard.getCardHolder().getName();
        this.atmLimit = debitCard.getAtmLimit();
        this.posLimit = debitCard.getPosLimit();
        this.contactless = debitCard.isContactless();
    }
}
