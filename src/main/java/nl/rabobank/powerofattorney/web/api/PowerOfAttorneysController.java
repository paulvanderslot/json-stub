package nl.rabobank.powerofattorney.web.api;

import static nl.rabobank.powerofattorney.web.AuthenticationHelper.getLoggedInUser;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nl.rabobank.powerofattorney.application.attorney.PowerOfAttorneysService;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorneyId;
import nl.rabobank.powerofattorney.web.AuthenticationHelper;

@RestController
@RequestMapping(path = "/power-of-attorneys", produces = MediaType.APPLICATION_JSON_VALUE)
public class PowerOfAttorneysController {

    private final PowerOfAttorneysService service;

    PowerOfAttorneysController(PowerOfAttorneysService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PowerOfAttorneyId> getAll() {
        return service.getAllIds();
    }

    @GetMapping("/{powerOfAttorneyId}")
    @ResponseStatus(HttpStatus.OK)
    public PowerOfAttorney getId(@PathVariable String powerOfAttorneyId) {
        return service.getForId(new PowerOfAttorneyId(powerOfAttorneyId), getLoggedInUser());
    }
}
