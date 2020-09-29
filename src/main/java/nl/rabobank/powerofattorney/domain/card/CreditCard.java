package nl.rabobank.powerofattorney.domain.card;

import lombok.Value;

@Value
public class CreditCard {
    CardId id;
    CardStatus status;
    String cardNumber;
    int sequenceNumber; //?? kan dubbel zijn
    String cardHolder;
    String monthlyLimit;
}
