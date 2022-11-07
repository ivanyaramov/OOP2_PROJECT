package sample.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Utilities.RedirectScenes;
import sample.models.DTOs.RegisterDTO;
import sample.models.people.Gender;
import sample.models.people.Role;
import sample.services.UserService;
import sample.services.impl.UserServiceImpl;
import sample.validation.ValidationUtil;

import java.io.IOException;

public class CreateOwnerController {
    UserService userService = new UserServiceImpl();
    @FXML
    private Label passwordRepeatLabelFXML;
    @FXML
    private TextField usernameFXML;
    @FXML
    private PasswordField passwordFXML;
    @FXML
    private PasswordField passwordRepeatFXML;
    @FXML
    private TextField fullNameFXML;
    @FXML
    private ComboBox genderFXML;
    @FXML
    private TextField telephoneFXML;
    @FXML
    private Label errorFXML;

    @FXML
    public void createNewOwner(ActionEvent event) throws IOException {

       boolean success = ValidationUtil.validatePassword(passwordFXML, passwordRepeatFXML, passwordRepeatLabelFXML);
        boolean userNameNotTaken = ValidationUtil.validateUserNameNotTaken(usernameFXML.getText(), errorFXML);
       if(!success || !userNameNotTaken){
           return;
       }
        Gender gender;
        if (genderFXML.getValue().equals("Male")) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }
        RegisterDTO registerDTO = new RegisterDTO(Role.OWNER, fullNameFXML.getText(), gender, usernameFXML.getText(), passwordFXML.getText(),
                passwordRepeatFXML.getText(), telephoneFXML.getText());
        userService.createPerson(registerDTO);
        RedirectScenes.redirect(event,"administrator");
    }
}
