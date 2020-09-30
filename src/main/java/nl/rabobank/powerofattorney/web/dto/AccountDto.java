package nl.rabobank.powerofattorney.web.dto;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Value;
import nl.rabobank.powerofattorney.domain.account.Account;

@Value
public class AccountDto {
    String owner;
    long balance;
    String created;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String ended;

    public AccountDto(Account account) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        this.owner = account.getOwner().getName();
        this.balance = account.getBalance();
        this.created = account.getCreateDate().format(formatter);
        this.ended = account.getClosedDate().map(date -> date.format(formatter)).orElse(null);
    }
}
