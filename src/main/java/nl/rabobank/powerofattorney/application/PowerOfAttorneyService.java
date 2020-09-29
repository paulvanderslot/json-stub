package nl.rabobank.powerofattorney.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import nl.rabobank.powerofattorney.domain.PowerOfAtterneyId;

@Service
public class PowerOfAttorneyService {

    public List<PowerOfAtterneyId> findAll() {
        return List.of("0001", "0002", "0003", "0004").stream().map(PowerOfAtterneyId::new).collect(Collectors.toList());
    }
}
