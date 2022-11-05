package sample.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    public void administratorPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../scenes/administrator.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1200, 800);
        window.setTitle("administratorPage");
        window.setScene(scene);
        window.show();
    }
}
