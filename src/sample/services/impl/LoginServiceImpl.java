package sample.services.impl;

import sample.currentLogin.CurrentLoggedUser;
import sample.models.people.Person;
import sample.services.LoginService;

public class LoginServiceImpl implements LoginService {
    @Override
    public void login(Person person) {
        CurrentLoggedUser.login(person);
    }
}
