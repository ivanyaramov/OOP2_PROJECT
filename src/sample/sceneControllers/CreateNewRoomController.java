package sample.sceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.models.DTOs.RoomDTO;
import sample.models.hotels.RoomCategory;
import sample.services.RoomService;
import sample.services.UserService;
import sample.services.impl.RoomServiceImpl;
import sample.services.impl.UserServiceImpl;
import sample.utilities.RedirectScenes;

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
        RoomService roomService = new RoomServiceImpl();
        if(!roomService.roomNumberExists(Integer.parseInt(roomNumberFXML.getText())))
        {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setNumber(Integer.parseInt(roomNumberFXML.getText()));
            roomDTO.setRoomCategory(RoomCategory.valueOf((String) typeOfRoomFXML.getValue()));
            roomDTO.setPricePerNight(Double.parseDouble(priceOfRoomFXML.getText()));
            roomService.createRoom(roomDTO);
            RedirectScenes.redirect(event, "createNewHotel");
        }else
        {
            roomNumberTakenLabelFXML.setText("Room number is already taken");
            return;
        }
    }
}
