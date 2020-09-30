package nl.rabobank.powerofattorney.web.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import lombok.Value;
import nl.rabobank.powerofattorney.domain.Authorization;
import nl.rabobank.powerofattorney.domain.attorney.Direction;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;

@Value
public class PowerOfAttorneyDto {
    String id;
    String grantor;
    String grantee;
    String account;
    Direction direction;
    Collection<Authorization> authorizations;
    Collection<CardSummaryDto> cards;

    public PowerOfAttorneyDto(PowerOfAttorney powerOfAttorney) {
        this.id = powerOfAttorney.getId().getId();
        this.grantor = powerOfAttorney.getGrantor().getName();
        this.grantee = powerOfAttorney.getGrantee().getName();
        this.account = powerOfAttorney.getAccountId().getFullId();
        this.direction = powerOfAttorney.getDirection();
        this.authorizations = powerOfAttorney.getAuthorizations();
        this.cards = powerOfAttorney.getCardSummaries().stream().map(CardSummaryDto::new).collect(Collectors.toList());
    }

}
