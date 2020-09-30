package nl.rabobank.powerofattorney.domain.card;

import lombok.Value;

@Value
public class CardSummary {
    CardId cardId;
    CardType type;
}
