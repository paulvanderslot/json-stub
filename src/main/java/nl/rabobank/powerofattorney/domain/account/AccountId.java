package nl.rabobank.powerofattorney.domain.account;

import lombok.NonNull;
import lombok.Value;

@Value
public class AccountId {
    private static final String ACCOUNT_PREFIX = "NL23RABO";

    @NonNull
    String id; //TODO validate 9 long

    public String getFullId() {
        return ACCOUNT_PREFIX + id;
    }
}
