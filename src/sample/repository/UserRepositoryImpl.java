package sample.repository;

import sample.DBService.DatabaseService;
import sample.models.people.Person;
import sample.models.people.Role;
import sample.models.viemModels.PersonWithRoleViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl extends RepositoryImpl implements UserRepository{
    private DatabaseService databaseService = new DatabaseService();


    @Override
    public Person getPersonByUsername(String username) {
        String hql = "FROM Person p WHERE p.username = '" + username + "'";
        return (Person) databaseService.getObjectByQuery(hql);
    }

    @Override
    public boolean personExistsByUsername(String username) {
        String hql = "FROM Person p WHERE p.username = '" + username + "'";
        return databaseService.objectExistsByQuery(hql);
    }

    @Override
    public List<Person> getPeopleByRole(Role role) {
        String hql = "FROM Person p WHERE p.role = '" + role + "'";
       return (List<Person>) databaseService.getListOfObjectsByQuery(hql);
    }

    @Override
    public void updatePerson(Person person) {
        databaseService.updateObject(person);
    }

    @Override
    public List<Person> getAllPeopleNonClients() {
        String hql = "FROM Person p WHERE p.role != 'CLIENT'";
        return (List<Person>) databaseService.getListOfObjectsByQuery(hql);
    }
}
