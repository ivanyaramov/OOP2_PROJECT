package sample.services.impl;

import javafx.beans.property.SimpleStringProperty;
import org.modelmapper.ModelMapper;
import sample.DBService.DatabaseService;
import sample.models.DTOs.PersonPasswordDTO;
import sample.models.DTOs.RegisterDTO;
import sample.models.viemModels.PersonForChoosingView;
import sample.models.people.Person;
import sample.models.people.Role;
import sample.models.viemModels.AdministratorViewModel;
import sample.models.viemModels.PersonWithRoleViewModel;
import sample.services.UserService;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    DatabaseService dbService = new DatabaseService();
    ModelMapper modelMapper = new ModelMapper();

    public boolean personExistsByUsername(String username){
        Person person = null;
        try {
            person = getPersonByUsername(username);
        }catch (NoResultException e){
            return false;
        }
        return person != null;
    }

    @Override
    public void createPerson(RegisterDTO registerDTO) {
        Person person = modelMapper.map(registerDTO, Person.class);
        dbService.saveObject(person);
    }

    public Person getPersonByUsername(String username){
        String hql = "FROM Person p WHERE p.username = '" + username + "'";
        return (Person) dbService.getObjectByQuery(hql);
    }

    public PersonPasswordDTO getPersonPasswordDTO(String username){
        return modelMapper.map(getPersonByUsername(username), PersonPasswordDTO.class);
    }

    @Override
    public List<PersonForChoosingView> getPersonViewByRole(Role role) {
        String hql = "FROM Person p WHERE p.role = '" + role + "'";
        List<Person> listOfObjectsByQuery = (List<Person>) dbService.getListOfObjectsByQuery(hql);
        List<PersonForChoosingView> listOfDTOs = new ArrayList<>();
        for(Person p : listOfObjectsByQuery) {
            PersonForChoosingView viewPersonForChoosingDTO = modelMapper.map(p, PersonForChoosingView.class);
            listOfDTOs.add(viewPersonForChoosingDTO);
        }
        return listOfDTOs;
    }

    @Override
    public void changePersonRole(String username, String role) {
        Person person = getPersonByUsername(username);
        person.setRole(Role.valueOf(role));
        dbService.updateObject(person);
    }

    public List<PersonWithRoleViewModel> getAllPeopleWithRoles(){
        String hql = "FROM Person p WHERE p.role != 'CLIENT'";
        List<PersonWithRoleViewModel> peoplewithRoles = new ArrayList<>();
        List<Person> listOfObjectsByQuery = (List<Person>) dbService.getListOfObjectsByQuery(hql);
        for(Person p : listOfObjectsByQuery) {
            PersonWithRoleViewModel personWithRoleViewModel = modelMapper.map(p, PersonWithRoleViewModel.class);
            peoplewithRoles.add(personWithRoleViewModel);
        }
        return peoplewithRoles;
    }
}
