package sample.services.impl;

import org.modelmapper.ModelMapper;
import sample.DBService.DatabaseService;
import sample.models.DTOs.RegisterDTO;
import sample.models.people.Person;
import sample.services.LoginService;
import sample.services.RegisterService;

public class RegisterServiceImpl implements RegisterService {
    ModelMapper modelMapper = new ModelMapper();
    LoginService loginService = new LoginServiceImpl();
    @Override
    public void registerAndLogin(RegisterDTO registerDTO) {
        if(checkIfPasswordMatches(registerDTO)) {
            Person person = modelMapper.map(registerDTO, Person.class);
            DatabaseService.saveObject(person);
            loginService.login(person);
        }


    }

    private boolean checkIfPasswordMatches(RegisterDTO registerDTO){
        return registerDTO.getPassword().equals(registerDTO.getPasswordRepeat());
    }
}
