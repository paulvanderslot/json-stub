package nl.rabobank.powerofattorney.web.dto;

import lombok.Value;
import nl.rabobank.powerofattorney.domain.card.CardSummary;
import nl.rabobank.powerofattorney.domain.card.CardType;

@Value
public class CardSummaryDto {
    String id;
    CardType type;

    public CardSummaryDto(CardSummary cardSummary) {
        this.id = cardSummary.getCardId().getId();
        this.type = cardSummary.getType();
    }
}
