package sample.Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RedirectScenes {
    public static void redirect(ActionEvent event, String scene) throws IOException {
        Parent root = FXMLLoader.load(RedirectScenes.class.getResource("../scenes/"+scene+".fxml"));
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root, 800, 1000);
        window.setTitle(scene);
        window.setScene(scene1);
        window.show();
    }
}
