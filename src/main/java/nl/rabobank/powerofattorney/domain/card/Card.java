package nl.rabobank.powerofattorney.domain.card;

import nl.rabobank.powerofattorney.domain.account.User;

public interface Card {
    CardId getId();

    User getCardHolder();
}
