package nl.rabobank.powerofattorney.domain.card;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import nl.rabobank.powerofattorney.domain.account.User;

@Value
@Builder
public class CreditCard implements Card {
    @NonNull CardId id;
    @NonNull CardStatus status;
    @NonNull String cardNumber;
    int sequenceNumber;
    @NonNull User cardHolder;
    long monthlyLimit; // money
}
