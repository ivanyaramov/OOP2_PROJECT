package sample.sceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.viemModels.RoomViewModel;
import sample.services.RoomService;
import sample.services.impl.RoomServiceImpl;
import sample.utilities.RedirectScenes;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomListController implements Initializable {

    RoomService roomService = new RoomServiceImpl();

    @FXML
    private TableView<RoomViewModel> tbDataRooms;

    @FXML
    public TableColumn<String,String> roomNumber;

    @FXML
    public TableColumn<String,String> roomRoomCategory;

    @FXML
    public TableColumn<String,String> roomPricePerNight;

    @FXML
    public TableColumn<String,String> roomRating;

    @FXML
    public TableColumn<String,String> roomHotelName;

    @FXML
    public Button rateButtonFXML;

    @FXML
    public TextField rateFieldFXML;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<RoomViewModel> roomViewModelObservableList = FXCollections.observableArrayList(roomService.getRoomsForUserHotels());
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        roomRoomCategory.setCellValueFactory(new PropertyValueFactory<>("roomCategory"));
        roomPricePerNight.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        roomRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        roomHotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));

        tbDataRooms.setItems(roomViewModelObservableList);

    }

    public void redirectBack(ActionEvent event) throws IOException {
        RedirectScenes.redirect(event,"main");
    }
}
