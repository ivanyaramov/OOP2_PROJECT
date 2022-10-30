package sample.sceneControllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.models.DTOs.RegisterDTO;
import sample.models.people.Gender;
import sample.models.people.Role;
import sample.services.RegisterService;
import sample.services.impl.RegisterServiceImpl;

import javax.persistence.Column;

public class RegisterController {
    RegisterService registerService = new RegisterServiceImpl();
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
    public void register() {
        Gender gender;
        if (genderFXML.getValue().equals("Male")) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }
        RegisterDTO registerDTO = new RegisterDTO(Role.CLIENT, fullNameFXML.getText(), gender, usernameFXML.getText(), passwordFXML.getText(),
                passwordRepeatFXML.getText(), telephoneFXML.getText());
        registerService.registerAndLogin(registerDTO);
    }


}
