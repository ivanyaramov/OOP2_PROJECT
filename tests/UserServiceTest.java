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
import sample.repository.UserRepository;
import sample.services.UserService;
import sample.services.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTest {
    UserRepository userRepository;
    UserService userService;
    ModelMapper modelMapper;
    private final String username = "testName";

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
}
