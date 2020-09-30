package nl.rabobank.powerofattorney.domain.account;

import java.time.LocalDate;
import java.util.Optional;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Account {
    @NonNull AccountId id;
    @NonNull User owner; // can have multiple accounts
    long balance; // money
    @NonNull LocalDate createDate;
    LocalDate closedDate; //optional

    public Optional<LocalDate> getClosedDate() {
        return Optional.ofNullable(closedDate);
    }
}
