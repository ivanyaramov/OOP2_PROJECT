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

    @FXML
    private Button actionSpecificFXML;

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
        if(actionFXML.getText().equals("view room busyness")){
            RedirectScenes.redirect(event,"roomList");
            return;
        }
        RedirectScenes.redirect(event,"reservation");
    }

    public void redirectPageSpecific(ActionEvent event) throws IOException {
        RedirectScenes.redirect(event,"reservationList");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Person loggedUser = CurrentLoggedUser.getLoggedUser();
        Role role = loggedUser.getRole();
        actionSpecificFXML.setText("view reservations");
        if(role == Role.ADMIN) {
            actionFXML.setText("admin panel");
            actionSpecificFXML.setVisible(false);
        }
        else if(role == Role.OWNER){
            actionFXML.setText("create hotel");
        }
        else if(role == Role.MANAGER){
            actionFXML.setText("create receptionists");
        }
        else if(role == Role.RECEPTIONIST){
            actionFXML.setText("view room busyness");
        }
        else {
            actionFXML.setText("make reservation");
            actionSpecificFXML.setVisible(false);
        }
    }
}
