package nl.rabobank.powerofattorney.application;

import java.util.Collection;

import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;

public interface PowerOfAttorneyRepository {

     Collection<PowerOfAttorney> findAll();
}
