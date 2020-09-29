package nl.rabobank.powerofattorney.application.cards;

import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.github.tomakehurst.wiremock.admin.NotFoundException;

import lombok.NonNull;
import nl.rabobank.powerofattorney.application.AuthorizationService;
import nl.rabobank.powerofattorney.application.account.AccountRepository;
import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.AccountId;
import nl.rabobank.powerofattorney.domain.account.User;
import nl.rabobank.powerofattorney.domain.card.CardId;
import nl.rabobank.powerofattorney.domain.card.CreditCard;
import nl.rabobank.powerofattorney.domain.exceptions.UnauthorizedException;

@Service
public class CreditCardsService {
    private AuthorizationService authorizationService;
    private final CardRepository repository;

    CreditCardsService(AuthorizationService authorizationService, CardRepository repository) {
        this.authorizationService = authorizationService;
        this.repository = repository;
    }

    public CreditCard getForId(CardId cardId, User loggedInUser) {
        CreditCard creditCard = repository.findCreditCardFor(cardId).orElseThrow(notFound(cardId));

        if (!authorizationService.isAllowedToView(loggedInUser, creditCard)) {
            throw new UnauthorizedException(loggedInUser.getName() + " is not allowed to view CreditCard " + cardId);
        }
        return creditCard;
    }

    private Supplier<NotFoundException> notFound(@NonNull CardId cardId) {
        return () -> new NotFoundException("CreditCard with " + cardId + " not found");
    }
}
