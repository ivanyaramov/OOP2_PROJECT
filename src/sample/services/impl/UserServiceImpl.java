package sample.services.impl;

import sample.DBService.DatabaseService;
import sample.models.people.Person;
import sample.services.UserService;

public class UserServiceImpl implements UserService {

    public Person getPersonByUsername(String username){
        String hql = "FROM Person p WHERE p.username = '" + username + "'";
        return (Person) DatabaseService.getObjectByQuery(hql);
    }
}
