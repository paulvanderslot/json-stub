package nl.rabobank.powerofattorney.domain.card;

import lombok.NonNull;
import lombok.Value;

@Value
public class Limit {
    long limit; //money
    @NonNull Period periodUnit;
}
