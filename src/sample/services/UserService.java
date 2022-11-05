package sample.services;

import sample.models.people.Person;

public interface UserService {
    Person getPersonByUsername(String username);
}
