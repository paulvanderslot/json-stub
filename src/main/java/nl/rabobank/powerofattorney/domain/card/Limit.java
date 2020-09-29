package nl.rabobank.powerofattorney.domain.card;

import lombok.Value;

@Value
public class Limit {
    String limit; //money
    Period periodUnit; //money
}
