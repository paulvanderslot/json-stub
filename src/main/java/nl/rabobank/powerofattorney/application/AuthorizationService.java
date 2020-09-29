package nl.rabobank.powerofattorney.application;

import static nl.rabobank.powerofattorney.domain.Authorization.VIEW;

import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import nl.rabobank.powerofattorney.application.attorney.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;
import nl.rabobank.powerofattorney.domain.card.Card;

@Service
public class AuthorizationService {
    private final PowerOfAttorneyRepository powerOfAttorneyRepository;

    public AuthorizationService(PowerOfAttorneyRepository powerOfAttorneyRepository) {
        this.powerOfAttorneyRepository = powerOfAttorneyRepository;
    }

    public boolean isAllowedToView(User loggedInUser, Account account) {
        if (loggedInUser.equals(account.getOwner())) {
            return true;
        }
        return hasPowerOfAttorneyToView(loggedInUser, account);
    }

    public boolean isAllowedToView(User loggedInUser, Card card) {
        if (loggedInUser.equals(card.getCardHolder())) {
            return true;
        }
        return hasPowerOfAttorneyToView(loggedInUser, card);
    }

    public boolean isAllowedToView(User loggedInUser, PowerOfAttorney powerOfAttorney) {
        return isGrantorOrGrantee(loggedInUser, powerOfAttorney);
    }

    private boolean isGrantorOrGrantee(User loggedInUser, PowerOfAttorney powerOfAttorney) {
        return loggedInUser.equals(powerOfAttorney.getGrantor()) || loggedInUser.equals(powerOfAttorney.getGrantee());
    }

    private boolean hasPowerOfAttorneyToView(User loggedInUser, Account account) {
        Optional<PowerOfAttorney> poaWithAccountId = powerOfAttorneyRepository.findForAccountId(account.getId());
        return isAuthorizedToViewAndGrantedAccess(poaWithAccountId, loggedInUser);
    }

    private boolean hasPowerOfAttorneyToView(User loggedInUser, Card card) {
        Optional<PowerOfAttorney> poaWithCardId = powerOfAttorneyRepository.findForCardId(card.getId());
        return isAuthorizedToViewAndGrantedAccess(poaWithCardId, loggedInUser);
    }

    private boolean isAuthorizedToViewAndGrantedAccess(Optional<PowerOfAttorney> powerOfAttorney, User loggedInUser) {
        return powerOfAttorney.stream()
                .filter(authorizedToView())
                .anyMatch(poa -> isGrantedAccess(loggedInUser, poa));
    }

    private boolean isGrantedAccess(User loggedInUser, PowerOfAttorney poa) {
        return poa.getGrantee().isGrantedAccess(loggedInUser);
    }

    private Predicate<PowerOfAttorney> authorizedToView() {
        return poa -> poa.getAuthorizations().contains(VIEW);
    }

}
