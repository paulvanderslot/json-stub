package nl.rabobank.powerofattorney.domain.attorney;

import nl.rabobank.powerofattorney.domain.account.User;

public interface Grantee {
    String getName();

    boolean isGrantedAccess(User user);
}
