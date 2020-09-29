package nl.rabobank.powerofattorney.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorneyId;

@Service
public class PowerOfAttorneyService {

    private final PowerOfAttorneyRepository repository;

    public PowerOfAttorneyService(PowerOfAttorneyRepository repository) {
        this.repository = repository;
    }

    public List<PowerOfAttorneyId> getAllIds() {
        return repository.findAll().stream().map(PowerOfAttorney::getId).collect(Collectors.toList());
    }
}
