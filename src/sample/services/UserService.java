package sample.services;

import sample.models.people.Person;
import sample.models.viemModels.AdministratorViewModel;

import java.util.List;

public interface UserService {
    Person getPersonByUsername(String username);
    void changePersonRole(String username, String role);
    List<AdministratorViewModel> getAllPeopleWithRoles();
}
