package sample.services;

import sample.models.DTOs.LoginDTO;
import sample.models.people.Person;

public interface LoginService {
    void login(LoginDTO dto);
}
