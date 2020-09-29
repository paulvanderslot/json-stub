package nl.rabobank.powerofattorney.application;

import org.springframework.stereotype.Repository;

import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorneyId;

@Repository
public interface PowerOfAttorneyRepository {

     PowerOfAttorneyId findAll();
}
