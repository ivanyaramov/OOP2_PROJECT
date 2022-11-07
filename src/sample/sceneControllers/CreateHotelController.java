package sample.sceneControllers;

import javafx.event.ActionEvent;
import sample.Utilities.RedirectScenes;

import java.io.IOException;

public class CreateHotelController {




    public void createNewHotel(){

    }

    public void createNewManager(ActionEvent event) throws IOException {
        RedirectScenes.redirect(event,"createNewManager");
    }
}
