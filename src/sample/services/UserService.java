package sample.services;

import sample.models.DTOs.PersonPasswordDTO;
import sample.models.people.Person;
import sample.models.viemModels.AdministratorViewModel;

import java.util.List;

public interface UserService {
    Person getPersonByUsername(String username);
    void changePersonRole(String username, String role);
    List<AdministratorViewModel> getAllPeopleWithRoles();
    boolean personExistsByUsername(String username);
    PersonPasswordDTO getPersonPasswordDTO(String username);
}
