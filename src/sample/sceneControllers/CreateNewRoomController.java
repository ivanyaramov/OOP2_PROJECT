package sample.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.services.UserService;
import sample.services.impl.UserServiceImpl;

import java.io.IOException;

public class CreateNewRoomController {
    UserService userService = new UserServiceImpl();
    @FXML
    private Label roomNumberTakenLabelFXML;
    @FXML
    private TextField roomNumberFXML;
    @FXML
    private ComboBox typeOfRoomFXML;
    @FXML
    private TextField priceOfRoomFXML;

    @FXML
    public void createNewRoom(ActionEvent event) throws IOException {

    }
}
