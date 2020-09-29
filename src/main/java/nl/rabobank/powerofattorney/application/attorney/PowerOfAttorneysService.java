package nl.rabobank.powerofattorney.application.attorney;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import nl.rabobank.powerofattorney.application.AuthorizationService;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorneyId;
import nl.rabobank.powerofattorney.domain.exceptions.NotFoundException;
import nl.rabobank.powerofattorney.domain.exceptions.UnauthorizedException;

@Service
public class PowerOfAttorneysService {
    private final AuthorizationService authorizationService;
    private final PowerOfAttorneyRepository repository;

    public PowerOfAttorneysService(AuthorizationService authorizationService,
            PowerOfAttorneyRepository repository) {
        this.authorizationService = authorizationService;
        this.repository = repository;
    }

    public List<PowerOfAttorneyId> getAllIds() {
        return repository.findAll().stream().map(PowerOfAttorney::getId).collect(Collectors.toList());
    }

    public PowerOfAttorney getForId(@NonNull PowerOfAttorneyId powerOfAttorneyId, @NonNull User loggedInUser) {
        PowerOfAttorney powerOfAttorney = repository.findForId(powerOfAttorneyId).orElseThrow(notFound(powerOfAttorneyId));

        if (!authorizationService.isAllowedToView(loggedInUser, powerOfAttorney)) {
            throw new UnauthorizedException(
                    loggedInUser.getName() + " is not allowed to view Power Of Attorney " + powerOfAttorneyId);
        }

        return powerOfAttorney;
    }

    private Supplier<NotFoundException> notFound(PowerOfAttorneyId powerOfAttorneyId) {
        return () -> new NotFoundException("Power Of Attorney with " + powerOfAttorneyId + " not found");
    }
}
