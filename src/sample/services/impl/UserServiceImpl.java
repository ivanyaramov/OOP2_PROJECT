package sample.services.impl;

import javafx.beans.property.SimpleStringProperty;
import sample.DBService.DatabaseService;
import sample.models.people.Person;
import sample.models.people.Role;
import sample.models.viemModels.AdministratorViewModel;
import sample.services.UserService;

import java.util.ArrayList;
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

    public List<AdministratorViewModel> getAllPeopleWithRoles(){
        String hql = "FROM Person p WHERE p.role != 'CLIENT'";
        List<AdministratorViewModel> administratorViewModels = new ArrayList<>();
        List<Person> listOfObjectsByQuery = (List<Person>) databaseService.getListOfObjectsByQuery(hql);
        for(Person p : listOfObjectsByQuery) {
            Object user = p.getUsername();
            SimpleStringProperty username = new SimpleStringProperty((String) user);
            Object rol = p.getRole().toString();
            SimpleStringProperty role = new SimpleStringProperty((String) rol);
            AdministratorViewModel avm = new AdministratorViewModel(username,role);
            administratorViewModels.add(avm);
        }
        return administratorViewModels;
    }
}
