package sample.sceneControllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends Application{
    public void login()
    {
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
