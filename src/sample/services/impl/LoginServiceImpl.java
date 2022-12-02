package sample.services.impl;

import sample.currentLogin.CurrentLoggedUser;
import sample.models.DTOs.LoginDTO;
import sample.models.people.Person;
import sample.services.LoginService;
import sample.services.UserService;

public class LoginServiceImpl implements LoginService {
    private UserService userService = new UserServiceImpl();
    @Override
    public boolean login(LoginDTO dto) {
       if(!userService.personExistsByUsername(dto.getUsername())){
           return false;
       }
       Person existingPerson = userService.getPersonByUsername(dto.getUsername());
       if(dto.getPassword().equals(existingPerson.getPassword())) {
            CurrentLoggedUser.login(existingPerson);
            return true;
        }
        return false;
    }
}
