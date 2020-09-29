package nl.rabobank.powerofattorney.application;

import static nl.rabobank.powerofattorney.domain.Authorization.VIEW;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import nl.rabobank.powerofattorney.application.attorney.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;
import nl.rabobank.powerofattorney.domain.card.Card;
import nl.rabobank.powerofattorney.domain.card.CreditCard;

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

    private boolean hasPowerOfAttorneyToView(User loggedInUser, Account account) {
        return powerOfAttorneyRepository.findWithAccountId(account.getId()).stream()
                .filter(authorizedToView())
                .anyMatch(isGrantedAccess(loggedInUser));
    }

    private boolean hasPowerOfAttorneyToView(User loggedInUser, Card card) {
        return powerOfAttorneyRepository.findWithCardId(card.getId()).stream()
                .filter(authorizedToView())
                .anyMatch(isGrantedAccess(loggedInUser));
    }

    private Predicate<PowerOfAttorney> isGrantedAccess(User loggedInUser) {
        return poa -> poa.getGrantee().equals(loggedInUser);
    }

    private Predicate<PowerOfAttorney> authorizedToView() {
        return poa -> poa.getAuthorizations().contains(VIEW);
    }


}
