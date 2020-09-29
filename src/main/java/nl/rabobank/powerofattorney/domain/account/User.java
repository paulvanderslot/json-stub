package nl.rabobank.powerofattorney.domain.account;

import lombok.Value;

/**
 * Person/Company/anything else that is registered by the bank
 */
@Value
public class User {
    String name;
}
