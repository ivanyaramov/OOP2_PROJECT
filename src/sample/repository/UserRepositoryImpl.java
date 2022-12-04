package sample.repository;

import sample.DBService.DatabaseService;
import sample.models.people.Person;
import sample.models.people.Role;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl extends RepositoryImpl implements UserRepository{
    private DatabaseService databaseService = new DatabaseService();
    private HotelRepository hotelRepository = new HotelRepositoryImpl();


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

    public List<Person> getAvailableManagers(){
        List<Person> list = getAllPeopleByRole(Role.MANAGER);
        List<Person> listToReturn = new ArrayList<>();
        for(Person person : list){
            if(!hotelRepository.managerHasHotels(person.getId())){
                listToReturn.add(person);
            }
        }
        return listToReturn;
    }

    public List<Person> getAvailableReceptionists(){
        List<Person> list = getAllPeopleByRole(Role.RECEPTIONIST);
        List<Person> listToReturn = new ArrayList<>();
        for(Person person : list){
            if(!hotelRepository.recptionistHasHotels(person.getId())){
                listToReturn.add(person);
            }
        }
        return listToReturn;
    }


    @Override
    public List<Person> getAllPeopleNonClients() {
        String hql = "FROM Person p WHERE p.role != 'CLIENT'";
        return (List<Person>) databaseService.getListOfObjectsByQuery(hql);
    }

    @Override
    public List<Person> getAllPeopleByRole(Role role) {
        String hql = "FROM Person p WHERE p.role = '" + role.name() + "'";
        return (List<Person>) databaseService.getListOfObjectsByQuery(hql);
    }
}
