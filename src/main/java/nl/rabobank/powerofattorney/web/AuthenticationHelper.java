package nl.rabobank.powerofattorney.web;

import lombok.experimental.UtilityClass;
import nl.rabobank.powerofattorney.domain.account.User;

@UtilityClass
public class AuthenticationHelper {

    //TODO: get user from principal, for now use static user
    static User getLoggedInUser() {
        return new User("Super duper employee");
    }
}
