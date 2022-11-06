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
import sample.services.impl.RegisterServiceImpl;

import java.io.IOException;

public class RegisterController {
    RegisterService registerService = new RegisterServiceImpl();
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
    public void register(ActionEvent event) throws IOException {
        if(passwordFXML.getText().trim().length()<6)
        {
            passwordRepeatLabelFXML.setText("Password is less than 6 symbols, please change it");
            return;
        }
        if(!passwordFXML.getText().equals(passwordRepeatFXML.getText()))
        {
            passwordRepeatLabelFXML.setText("Passwords don't match \r\nPlease correct the password to match");
            return;
        }
        Gender gender;
        if (genderFXML.getValue().equals("Male")) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }
        RegisterDTO registerDTO = new RegisterDTO(Role.CLIENT, fullNameFXML.getText(), gender, usernameFXML.getText(), passwordFXML.getText(),
                passwordRepeatFXML.getText(), telephoneFXML.getText());
        if(!registerService.registerAndLogin(registerDTO)) {
            errorFXML.setText("Username is already taken.");
            return;
        }
        RedirectScenes.redirect(event,"main");
    }
}
