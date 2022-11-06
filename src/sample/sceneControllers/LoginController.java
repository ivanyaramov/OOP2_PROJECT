package sample.sceneControllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.DTOs.LoginDTO;
import sample.services.LoginService;
import sample.services.impl.LoginServiceImpl;

import java.io.IOException;

public class LoginController extends Application{
    LoginService loginService = new LoginServiceImpl();
    @FXML
    private TextField usernameFXML;
    @FXML
    private PasswordField passwordFXML;
    public void login(ActionEvent event) throws IOException {
        LoginDTO loginDTO = new LoginDTO(usernameFXML.getText(), passwordFXML.getText());
        boolean successLogin = loginService.login(loginDTO);
    }
    public void register(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../scenes/register.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 590, 600);
        window.setTitle("Register");
        window.setScene(scene);
        window.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

}
