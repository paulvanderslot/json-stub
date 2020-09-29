package nl.rabobank.powerofattorney.domain.card;

import lombok.NonNull;
import lombok.Value;

@Value
public class CardId {
    @NonNull
    String id;
}
