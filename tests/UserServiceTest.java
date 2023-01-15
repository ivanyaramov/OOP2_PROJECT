import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import sample.models.DTOs.RegisterDTO;
import sample.models.people.Gender;
import sample.models.people.Person;
import sample.models.people.Role;
import sample.models.ratings.ClientRating;
import sample.models.reservations.Reservation;
import sample.repository.UserRepository;
import sample.services.UserService;
import sample.services.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTest {
    UserRepository userRepository;
    UserService userService;
    ModelMapper modelMapper;
    private final String username = "testName";
    private final String username2 = "test2Name";

    @BeforeEach
    public void before(){
        userService = new UserServiceImpl();
        userRepository = Mockito.mock(UserRepository.class);
        userService.setRepository(userRepository);
        modelMapper = new ModelMapper();
    }
    @Test
    public void personExistsByUsernameTest(){
        Mockito.when(userRepository.personExistsByUsername(username)).thenReturn(true);
        Assertions.assertTrue(userService.personExistsByUsername(username));
        Mockito.when(userRepository.personExistsByUsername(username)).thenReturn(false);
        Assertions.assertFalse(userService.personExistsByUsername(username));
    }

    @Test
    public void createPersonTest(){
        RegisterDTO registerDTO = new RegisterDTO(Role.MANAGER,  "fullname", Gender.FEMALE, "username",
                "pw", "pw", "tel");
        userService.createPerson(registerDTO);
        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        Mockito.verify(userRepository).save(argument.capture());
        assertEquals(registerDTO.getRole(), argument.getValue().getRole());
        assertEquals(registerDTO.getFullName(), argument.getValue().getFullName());
        assertEquals(registerDTO.getGender(), argument.getValue().getGender());
        assertEquals(registerDTO.getPassword(), argument.getValue().getPassword());
        assertEquals(registerDTO.getUsername(), argument.getValue().getUsername());
    }

    @Test
    public void getPersonByUsernameTest(){
        Person person = new Person();
        person.setFullName("Ivan Ivanov");
        Mockito.when(userRepository.getPersonByUsername(username)).thenReturn(person);
        Person actualPerson = userService.getPersonByUsername(username);
        assertNotNull(actualPerson);
        assertEquals(person.getFullName(), actualPerson.getFullName());
    }

    @Test
    public void getPeopleByUsernameTest(){
        Person person1 = new Person();
        person1.setFullName("Ivan Ivanov");
        Person person2 = new Person();
        person1.setFullName("Dimitar Dimitrov");
        Mockito.when(userRepository.getPersonByUsername(username)).thenReturn(person1);
        Mockito.when(userRepository.getPersonByUsername(username2)).thenReturn(person2);
        List<String> listOfUsernames = new ArrayList<>();
        listOfUsernames.add(username);
        listOfUsernames.add(username2);
        List<Person> list = userService.getPeopleByListOfUsernames(listOfUsernames);
        Person actualPerson1 = list.get(0);
        Person actualPerson2= list.get(1);
        assertNotNull(actualPerson1);
        assertEquals(person1.getFullName(), actualPerson1.getFullName());
        assertNotNull(actualPerson2);
        assertEquals(person2.getFullName(), actualPerson2.getFullName());
    }

    @Test
    public void updatePersonRatingTest(){
        Person person1 = new Person();
        person1.setFullName("Ivan Ivanov");
        List<ClientRating> list = new ArrayList<>();
        ClientRating clientRating1 = new ClientRating(person1, 8, new Reservation());
        ClientRating clientRating2 = new ClientRating(person1, 4, new Reservation());
        list.add(clientRating1);
        list.add(clientRating2);
        double avg = (clientRating1.getRating() + clientRating2.getRating()) / 2;
        userService.updatePersonRating(person1, list);
        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        Mockito.verify(userRepository).update(argument.capture());
        Assertions.assertEquals(person1.getFullName(), argument.getValue().getFullName());
        Assertions.assertEquals(avg, argument.getValue().getRating());
    }

    @Test
    public void changePersonRoleTest(){
        Person person1 = new Person();
        person1.setFullName("Ivan Ivanov");
        Mockito.when(userRepository.getPersonByUsername(username)).thenReturn(person1);
        String role = "MANAGER";
        userService.changePersonRole(username, role);
        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        Mockito.verify(userRepository).update(argument.capture());
        Assertions.assertEquals(role, String.valueOf(argument.getValue().getRole()));
    }




}
