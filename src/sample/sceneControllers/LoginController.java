package sample.sceneControllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.utilities.RedirectScenes;
import sample.models.DTOs.LoginDTO;
import sample.services.LoginService;
import sample.services.impl.LoginServiceImpl;

import java.io.IOException;

public class LoginController extends Application{
    @FXML
    private TextField usernameFXML;
    @FXML
    private PasswordField passwordFXML;
    @FXML
    private Label errorFXML;

    private final LoginService loginService = new LoginServiceImpl();

    public void login(ActionEvent event) throws IOException {
        LoginDTO loginDTO = new LoginDTO(usernameFXML.getText(), passwordFXML.getText());
        boolean successLogin = loginService.login(loginDTO);
        if(!successLogin){
            errorFXML.setText("Invalid credentials");
            return;
        }
        loginService.login(loginDTO);
        RedirectScenes.redirect(event,"main");
    }
    public void register(ActionEvent event) throws Exception {
        RedirectScenes.redirect(event,"register");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

}
