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
                dbService.saveObject(person);
                return loginService.login(modelMapper.map(registerDTO, LoginDTO.class));
        }
    return false;

    }

    private boolean checkIfPasswordMatches(RegisterDTO registerDTO){
        return registerDTO.getPassword().equals(registerDTO.getPasswordRepeat());
    }
}
