package sample.services.impl;

import org.modelmapper.ModelMapper;
import sample.DBService.DatabaseService;
import sample.models.DTOs.LoginDTO;
import sample.models.DTOs.RegisterDTO;
import sample.models.people.Person;
import sample.services.LoginService;
import sample.services.RegisterService;
import sample.services.UserService;

import javax.persistence.NoResultException;

public class RegisterServiceImpl implements RegisterService {
    ModelMapper modelMapper = new ModelMapper();
    LoginService loginService = new LoginServiceImpl();
    DatabaseService dbService = new DatabaseService();
    UserService userService = new UserServiceImpl();
    @Override
    public boolean registerAndLogin(RegisterDTO registerDTO) {
        if(checkIfPasswordMatches(registerDTO)) {
            Person person = modelMapper.map(registerDTO, Person.class);
            boolean exists = true;
            try {
               userService.getPersonByUsername(person.getUsername());
            }catch (NoResultException e){
                exists = false;
            }
            if(!exists) {
                dbService.saveObject(person);
                loginService.login(modelMapper.map(registerDTO, LoginDTO.class));
            }
            return !exists;
        }
    return true;

    }

    private boolean checkIfPasswordMatches(RegisterDTO registerDTO){
        return registerDTO.getPassword().equals(registerDTO.getPasswordRepeat());
    }
}
