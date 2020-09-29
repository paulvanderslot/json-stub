package nl.rabobank.powerofattorney.domain.attorney;

import java.util.Collection;

import lombok.Builder;
import lombok.Value;
import nl.rabobank.powerofattorney.domain.Authorization;
import nl.rabobank.powerofattorney.domain.account.AccountId;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.card.CardId;

@Value
@Builder
public class PowerOfAttorney {
    PowerOfAttorneyId id;
    User grantor; //account owner (niet nodig in model, wel in API)
    User grantee; //account owner? -> Fellowship is a group, with cards for individuals
    AccountId accountId; //account id of grantor
    Direction direction; //unclear if this field matters. Grantor and accountId always seem to match.
    Collection<Authorization> authorizations;
    Collection<CardId> cardIds; //only when debit card authorizations
}
