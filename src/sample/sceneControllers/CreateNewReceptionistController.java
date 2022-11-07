package sample.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Utilities.RedirectScenes;
import sample.models.DTOs.RegisterDTO;
import sample.models.people.Gender;
import sample.models.people.Role;
import sample.services.RegisterService;
import sample.services.UserService;
import sample.services.impl.RegisterServiceImpl;
import sample.services.impl.UserServiceImpl;
import sample.validation.ValidationUtil;

import java.io.IOException;

public class CreateNewReceptionistController {
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
    public void createNewReceptionist(ActionEvent event) throws IOException {
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
        RegisterDTO registerDTO = new RegisterDTO(Role.RECEPTIONIST, fullNameFXML.getText(), gender, usernameFXML.getText(), passwordFXML.getText(),
                passwordRepeatFXML.getText(), telephoneFXML.getText());
        userService.createPerson(registerDTO);
        RedirectScenes.redirect(event, "main");
    }
}
