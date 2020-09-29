package nl.rabobank.powerofattorney.domain.card;

import lombok.Builder;
import lombok.Value;
import nl.rabobank.powerofattorney.domain.account.User;

@Value
@Builder
public class CreditCard {
    CardId id;
    CardStatus status;
    String cardNumber;
    int sequenceNumber; //?? kan dubbel zijn
    User cardHolder;
    long monthlyLimit; // money
}
