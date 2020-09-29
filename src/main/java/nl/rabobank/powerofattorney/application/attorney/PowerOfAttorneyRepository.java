package nl.rabobank.powerofattorney.application.attorney;

import java.util.Collection;
import java.util.Optional;

import nl.rabobank.powerofattorney.domain.account.AccountId;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;
import nl.rabobank.powerofattorney.domain.card.CardId;

public interface PowerOfAttorneyRepository {

     Collection<PowerOfAttorney> findAll();

     Optional<PowerOfAttorney> findWithCardId(CardId cardId);

     Optional<PowerOfAttorney> findWithAccountId(AccountId accountId);

}
