package nl.rabobank.powerofattorney.web.dto;

import lombok.Value;
import nl.rabobank.powerofattorney.domain.card.CardStatus;
import nl.rabobank.powerofattorney.domain.card.CreditCard;

@Value
public class CreditCardDto {
    String id;
    CardStatus status;
    long cardNumber;
    long sequenceNumber;
    String cardHolder;
    long monthlyLimit;

    public CreditCardDto(CreditCard creditCard) {
        this.id = creditCard.getId().getId();
        this.status = creditCard.getStatus();
        this.cardNumber = creditCard.getCardNumber();
        this.sequenceNumber = creditCard.getSequenceNumber();
        this.cardHolder = creditCard.getCardHolder().getName();
        this.monthlyLimit = creditCard.getMonthlyLimit();
    }
}
