package sample.repository;

import sample.models.people.Person;
import sample.models.people.Role;

import java.util.List;

public interface UserRepository extends Repository {
    Person getPersonByUsername(String username);
    boolean personExistsByUsername(String username);
    List<Person> getPeopleByRole(Role role);
    List<Person> getAllPeopleNonClients();
    List<Person> getAllPeopleByRole(Role role);
}
