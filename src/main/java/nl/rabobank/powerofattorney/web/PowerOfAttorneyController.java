package nl.rabobank.powerofattorney.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nl.rabobank.powerofattorney.application.PowerOfAttorneyService;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorneyId;

@RestController
@RequestMapping(path = "/power-of-attorneys", produces = MediaType.APPLICATION_JSON_VALUE)
public class PowerOfAttorneyController {

    private final PowerOfAttorneyService service;

    PowerOfAttorneyController(PowerOfAttorneyService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PowerOfAttorneyId> get() {
        return service.findAll();
    }
}
