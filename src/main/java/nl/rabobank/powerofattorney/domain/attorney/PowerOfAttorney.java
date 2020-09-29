package nl.rabobank.powerofattorney.domain.attorney;

import java.util.Collection;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import nl.rabobank.powerofattorney.domain.Authorization;
import nl.rabobank.powerofattorney.domain.account.AccountId;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.card.CardId;

@Value
@Builder
public class PowerOfAttorney {
    @NonNull PowerOfAttorneyId id;
    @NonNull User grantor; //account owner (niet nodig in model, wel in API)
    @NonNull Grantee grantee;
    @NonNull AccountId accountId; //account id of grantor
    @NonNull Direction direction; //unclear if this field matters. Grantor and accountId always seem to match.
    @NonNull Collection<Authorization> authorizations;
    @NonNull Collection<CardId> cardIds; //only when debit card authorizations
}
