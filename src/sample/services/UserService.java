package sample.services;

import sample.models.people.Person;

import java.util.List;

public interface UserService {
    Person getPersonByUsername(String username);
    void changePersonRole(String username, String role);
    List<Person> getAllPeopleWithRoles();
}
