package nl.rabobank.powerofattorney.application.cards;

import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import nl.rabobank.powerofattorney.application.AuthorizationService;
import nl.rabobank.powerofattorney.application.account.AccountRepository;
import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.AccountId;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.card.Card;
import nl.rabobank.powerofattorney.domain.card.CardId;
import nl.rabobank.powerofattorney.domain.card.CreditCard;
import nl.rabobank.powerofattorney.domain.card.DebitCard;
import nl.rabobank.powerofattorney.domain.exceptions.NotFoundException;
import nl.rabobank.powerofattorney.domain.exceptions.UnauthorizedException;

@Service
public class CardsService {
    private AuthorizationService authorizationService;
    private final CardRepository repository;

    CardsService(AuthorizationService authorizationService, CardRepository repository) {
        this.authorizationService = authorizationService;
        this.repository = repository;
    }

    public CreditCard getCreditCardForId(CardId cardId, User loggedInUser) {
        CreditCard creditCard = repository.findCreditCardFor(cardId).orElseThrow(notFound(cardId));
        checkAuthorization(loggedInUser, creditCard);
        return creditCard;
    }

    public DebitCard getDebitCardForId(CardId cardId, User loggedInUser) {
        DebitCard debitCard = repository.findDebitCardFor(cardId).orElseThrow(notFound(cardId));
        checkAuthorization(loggedInUser, debitCard);
        return debitCard;
    }

    private void checkAuthorization(User loggedInUser, Card card) {
        if (!authorizationService.isAllowedToView(loggedInUser, card)) {
            throw new UnauthorizedException(unauthorizedMessage(loggedInUser, card));
        }
    }

    private String unauthorizedMessage(User loggedInUser, Card card) {
        return loggedInUser.getName() + " is not allowed to view " + card.getClass().getSimpleName() + " " + card.getId();
    }

    private Supplier<NotFoundException> notFound(@NonNull CardId cardId) {
        return () -> new NotFoundException("Card with " + cardId + " not found");
    }
}
