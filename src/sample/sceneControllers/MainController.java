package sample.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Utilities.RedirectScenes;

import java.io.IOException;

public class MainController {
    public void administratorPage(ActionEvent event) throws IOException {
        RedirectScenes.redirect(event,"createNewReceptionist");
    }
}
