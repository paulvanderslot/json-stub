package nl.rabobank.powerofattorney.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorneyId;

@Service
public class PowerOfAttorneyService {

    public List<PowerOfAttorneyId> findAll() {
        return List.of("0001", "0002", "0003", "0004").stream().map(PowerOfAttorneyId::new).collect(Collectors.toList());
    }
}
