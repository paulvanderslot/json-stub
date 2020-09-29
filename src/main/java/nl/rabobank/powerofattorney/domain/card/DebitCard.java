package nl.rabobank.powerofattorney.domain.card;

import lombok.Value;

@Value()
public class DebitCard {
    CardId id;
    CardStatus status;
    String cardNumber;
    int sequenceNumber; //?? kan dubbel zijn
    String cardHolder;
    Limit atmLimit;
    Limit posLimit;
    boolean contactless;
}
