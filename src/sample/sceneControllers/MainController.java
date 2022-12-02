package sample.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.currentLogin.CurrentLoggedUser;
import sample.models.people.Person;
import sample.models.people.Role;
import sample.utilities.RedirectScenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button actionFXML;

    public void redirectPage(ActionEvent event) throws IOException {
        if(actionFXML.getText().equals("admin panel")){
            RedirectScenes.redirect(event,"administrator");
            return;
        }
        if(actionFXML.getText().equals("create hotel")){
            RedirectScenes.redirect(event,"createNewHotel");
            return;
        }
        if(actionFXML.getText().equals("create receptionists")){
            RedirectScenes.redirect(event,"createNewReceptionist");
            return;
        }
        if(actionFXML.getText().equals("view client state")){
            RedirectScenes.redirect(event,"viewClientState");
            return;
        }
        RedirectScenes.redirect(event,"createNewReservation");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Person loggedUser = CurrentLoggedUser.getLoggedUser();
        Role role = loggedUser.getRole();
        if(role == Role.ADMIN) {
            actionFXML.setText("admin panel");
        }
        else if(role == Role.OWNER){
            actionFXML.setText("create hotel");
        }
        else if(role == Role.MANAGER){
            actionFXML.setText("create receptionists");
        }
        else if(role == Role.RECEPTIONIST){
            actionFXML.setText("view client state");
        }
        else {
            actionFXML.setText("make reservation");
        }
    }
}
