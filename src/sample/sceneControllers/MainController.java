package sample.sceneControllers;

import javafx.event.ActionEvent;
import sample.utilities.RedirectScenes;

import java.io.IOException;

public class MainController {
    public void administratorPage(ActionEvent event) throws IOException {
        RedirectScenes.redirect(event,"createNewHotel");
    }
}
