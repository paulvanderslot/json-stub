package nl.rabobank.powerofattorney.domain.attorney;

import java.util.Collection;

import lombok.Value;
import nl.rabobank.powerofattorney.domain.account.AccountId;
import nl.rabobank.powerofattorney.domain.card.CardId;

@Value
public class PowerOfAttorney {
    PowerOfAttorneyId id;
    String grantor; //account owner (niet nodig in model, wel in API)
    String grantee; //account owner? -> Fellowship is a group, with cards for individuals
    AccountId account; //account id of grantor
    Direction direction;
    Collection<Authorization> authorizations;
    Collection<CardId> cardIds; //only when debit card authorizations
}
