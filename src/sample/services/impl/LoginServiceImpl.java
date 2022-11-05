package sample.services.impl;

import sample.DBService.DatabaseService;
import sample.currentLogin.CurrentLoggedUser;
import sample.models.DTOs.LoginDTO;
import sample.models.people.Person;
import sample.services.LoginService;
import sample.services.UserService;

public class LoginServiceImpl implements LoginService {
    private UserService userService = new UserServiceImpl();
    @Override
    public void login(LoginDTO dto) {
        Person existingPerson = userService.getPersonByUsername(dto.getUseranme());
        if(dto.getPassword().equals(existingPerson.getPassword())) {
            CurrentLoggedUser.login(existingPerson);
        }
    }
}
