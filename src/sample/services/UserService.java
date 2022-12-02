package sample.services;

import sample.models.DTOs.PersonPasswordDTO;
import sample.models.DTOs.RegisterDTO;
import sample.models.ratings.ClientRating;
import sample.models.viemModels.PersonForChoosingView;
import sample.models.people.Person;
import sample.models.people.Role;
import sample.models.viemModels.AdministratorViewModel;
import sample.models.viemModels.PersonWithRoleViewModel;

import java.util.List;

public interface UserService {
    Person getPersonByUsername(String username);
    void changePersonRole(String username, String role);
    List<PersonWithRoleViewModel> getAllPeopleNonClients();
    boolean personExistsByUsername(String username);
    void createPerson(RegisterDTO registerDTO);
    PersonPasswordDTO getPersonPasswordDTO(String username);
    List<PersonForChoosingView> getPersonViewByRole(Role role);
    List<Person> getPeopleByListOfUsernames(List<String> usernames);
    void updatePersonRating(Person person, List<ClientRating> ratings);
}
