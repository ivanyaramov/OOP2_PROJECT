package sample.services.impl;

import javafx.beans.property.SimpleStringProperty;
import org.modelmapper.ModelMapper;
import sample.DBService.DatabaseService;
import sample.models.DTOs.PersonPasswordDTO;
import sample.models.DTOs.RegisterDTO;
import sample.models.ratings.ClientRating;
import sample.models.viemModels.PersonForChoosingView;
import sample.models.people.Person;
import sample.models.people.Role;
import sample.models.viemModels.AdministratorViewModel;
import sample.models.viemModels.PersonWithRoleViewModel;
import sample.repository.UserRepository;
import sample.repository.UserRepositoryImpl;
import sample.services.UserService;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private ModelMapper modelMapper = new ModelMapper();
    private UserRepository userRepository = new UserRepositoryImpl();

    public boolean personExistsByUsername(String username){
    return userRepository.personExistsByUsername(username);
    }

    @Override
    public void createPerson(RegisterDTO registerDTO) {
        Person person = modelMapper.map(registerDTO, Person.class);
        userRepository.save(person);
    }

    public Person getPersonByUsername(String username){
        return userRepository.getPersonByUsername(username);
    }

    public List<Person> getPeopleByListOfUsernames(List<String> usernames){
        List<Person> list = new ArrayList<>();
        for(String username: usernames){
            Person p = getPersonByUsername(username);
            list.add(p);
        }
        return list;
    }

    @Override
    public void updatePersonRating(Person person, List<ClientRating> ratings) {
        double avg = 0;
    for(ClientRating rating : ratings){
        avg += rating.getRating();
    }
    avg = avg / ratings.size();
    person.setRating(avg);
    userRepository.updatePerson(person);
    }

    public PersonPasswordDTO getPersonPasswordDTO(String username){
        return modelMapper.map(getPersonByUsername(username), PersonPasswordDTO.class);
    }

    @Override
    public List<PersonForChoosingView> getPersonViewByRole(Role role) {
        List<Person> listOfObjectsByQuery = userRepository.getPeopleByRole(role);
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
        userRepository.updatePerson(person);
    }

    public List<PersonWithRoleViewModel> getAllPeopleNonClients(){
        List<PersonWithRoleViewModel> peoplewithRoles = new ArrayList<>();
        List<Person> listOfObjectsByQuery = userRepository.getAllPeopleNonClients();
        for(Person p : listOfObjectsByQuery) {
            PersonWithRoleViewModel personWithRoleViewModel = modelMapper.map(p, PersonWithRoleViewModel.class);
            peoplewithRoles.add(personWithRoleViewModel);
        }
        return peoplewithRoles;
    }
}
