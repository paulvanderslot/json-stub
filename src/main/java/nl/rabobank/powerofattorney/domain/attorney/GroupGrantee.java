package nl.rabobank.powerofattorney.domain.attorney;

import java.util.Collection;

import lombok.NonNull;
import lombok.Value;
import nl.rabobank.powerofattorney.domain.account.User;

@Value
public class GroupGrantee implements Grantee {
    String name;
    Collection<User> usersInGroup;

    @Override public boolean isGrantedAccess(@NonNull User user) {
        return usersInGroup.contains(user);
    }
}
