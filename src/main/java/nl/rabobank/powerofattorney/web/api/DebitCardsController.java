package nl.rabobank.powerofattorney.web.api;

import static nl.rabobank.powerofattorney.web.AuthenticationHelper.getLoggedInUser;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nl.rabobank.powerofattorney.application.cards.CardsService;
import nl.rabobank.powerofattorney.domain.card.CardId;
import nl.rabobank.powerofattorney.domain.card.DebitCard;
import nl.rabobank.powerofattorney.web.dto.DebitCardDto;

@RestController
@RequestMapping(path = "/debit-cards", produces = MediaType.APPLICATION_JSON_VALUE)
public class DebitCardsController {
    private final CardsService service;

    DebitCardsController(CardsService service) {
        this.service = service;
    }

    @GetMapping("/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public DebitCardDto get(@PathVariable String cardId) {
        DebitCard debitCard = service.getDebitCardForId(new CardId(cardId), getLoggedInUser());
        return new DebitCardDto(debitCard);
    }

}
