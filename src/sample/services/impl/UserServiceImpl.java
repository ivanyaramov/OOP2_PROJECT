package sample.services.impl;

import sample.DBService.DatabaseService;
import sample.models.people.Person;
import sample.models.people.Role;
import sample.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    DatabaseService databaseService = new DatabaseService();

    public Person getPersonByUsername(String username){
        String hql = "FROM Person p WHERE p.username = '" + username + "'";
        return (Person) databaseService.getObjectByQuery(hql);
    }

    @Override
    public void changePersonRole(String username, String role) {
        Person person = getPersonByUsername(username);
        person.setRole(Role.valueOf(role));
        databaseService.saveObject(person);
    }

    public List<Person> getAllPeopleWithRoles(){
        String hql = "SELECT p.username, p.role FROM Person p WHERE p.role != 'CLIENT'";
        return (List<Person>)databaseService.getListOfObjectsByQuery(hql);

    }
}
