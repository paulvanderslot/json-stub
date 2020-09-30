package nl.rabobank.powerofattorney.domain.account;

import lombok.NonNull;
import lombok.Value;

@Value
public class AccountId {
    private static final String ACCOUNT_PREFIX = "NL23RABO";

    @NonNull
    String id;

    public AccountId(String id) {
        this.id = validateLength(id);
        ;
    }

    private String validateLength(String id) {
        if (id.length() != 9) {
            throw new IllegalArgumentException("Account id must be 9 digits (excluding prefix " + ACCOUNT_PREFIX + ")");
        }
        return id;
    }

    public String getFullId() {
        return ACCOUNT_PREFIX + id;
    }
}
