package sample.repository;

import sample.models.people.Person;
import sample.models.people.Role;
import sample.models.viemModels.PersonForChoosingView;

import java.util.List;

public interface UserRepository extends Repository{
    Person getPersonByUsername(String username);
    boolean personExistsByUsername(String username);
    List<Person> getPeopleByRole(Role role);
    void updatePerson(Person person);
    List<Person> getAllPeopleNonClients();
}
