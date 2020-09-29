package nl.rabobank.powerofattorney.application.attorney;

import java.util.Collection;

import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;

public interface PowerOfAttorneyRepository {

     Collection<PowerOfAttorney> findAll();
}
