package nl.rabobank.powerofattorney.domain.attorney;

import lombok.NonNull;
import lombok.Value;
import nl.rabobank.powerofattorney.domain.account.User;

@Value
public class SingleGrantee implements Grantee {
    @NonNull String name;

    @Override public boolean isGrantedAccess(@NonNull User user) {
        return name.equals(user.getName());
    }
}
