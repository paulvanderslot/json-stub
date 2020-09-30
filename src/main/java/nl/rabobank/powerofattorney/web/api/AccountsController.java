package nl.rabobank.powerofattorney.web.api;

import static nl.rabobank.powerofattorney.web.AuthenticationHelper.getLoggedInUser;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nl.rabobank.powerofattorney.application.account.AccountsService;
import nl.rabobank.powerofattorney.domain.account.Account;
import nl.rabobank.powerofattorney.domain.account.AccountId;
import nl.rabobank.powerofattorney.web.dto.AccountDto;

@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

    private final AccountsService service;

    AccountsController(AccountsService service) {
        this.service = service;
    }

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto get(@PathVariable String accountId) {
        Account account = service.getForId(new AccountId(accountId), getLoggedInUser());
        return new AccountDto(account);
    }
}
