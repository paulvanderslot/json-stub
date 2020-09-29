package nl.rabobank.powerofattorney.domain.card;

import lombok.Value;

@Value
public class Limit {
    long limit; //money
    Period periodUnit; //money
}
