package nl.rabobank.powerofattorney.application;

import static nl.rabobank.powerofattorney.domain.Authorization.VIEW;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import nl.rabobank.powerofattorney.application.attorney.PowerOfAttorneyRepository;
import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.attorney.PowerOfAttorney;
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

    // TODO: introduce Card interface when implement debit card
    public boolean isAllowedToView(User loggedInUser, CreditCard card) {
        if (loggedInUser.equals(card.getCardHolder())) {
            return true;
        }
        return hasPowerOfAttorneyToView(loggedInUser, card);
    }

    private boolean hasPowerOfAttorneyToView(User loggedInUser, Account account) {
        return powerOfAttorneyRepository.findAll().stream()
                .filter(matchingAccount(account))
                .filter(authorizedToView())
                .anyMatch(isGrantedAccess(loggedInUser));
    }

    private boolean hasPowerOfAttorneyToView(User loggedInUser, CreditCard card) {
        return powerOfAttorneyRepository.findAll().stream()
                .filter(matchingCard(card))
                .filter(authorizedToView())
                .anyMatch(isGrantedAccess(loggedInUser));
    }

    private Predicate<PowerOfAttorney> matchingCard(CreditCard card) {
        return poa -> poa.getCardIds().contains(card.getId());
    }

    private Predicate<PowerOfAttorney> isGrantedAccess(User loggedInUser) {
        return poa -> poa.getGrantee().equals(loggedInUser);
    }

    private Predicate<PowerOfAttorney> authorizedToView() {
        return poa -> poa.getAuthorizations().contains(VIEW);
    }

    private Predicate<PowerOfAttorney> matchingAccount(Account account) {
        return poa -> poa.getAccountId().equals(account.getId());
    }
}
