package sample.sceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.DTOs.ReservationDTO;
import sample.models.DTOs.RoomDTO;
import sample.models.viemModels.HotelView;
import sample.models.viemModels.PersonForCreateHotelViewModel;
import sample.services.HotelService;
import sample.services.ReservationService;
import sample.services.impl.HotelServiceImpl;
import sample.services.impl.ReservationServiceImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    HotelService hotelService = new HotelServiceImpl();

    @FXML
    private TableView<HotelView> tbDataHotels;

    @FXML
    public TableColumn<String,String> hotelName;

    @FXML
    public TableColumn<String,String> hotelCity;

    @FXML
    public TableColumn<String,String> hotelStars;

    @FXML
    private TableView<HotelView> tbDataRooms;

    @FXML
    public TableColumn<String,String> roomNumber;

    @FXML
    public TableColumn<String,String> roomCategory;

    @FXML
    public TableColumn<String,String> roomPricePerNight;

    @FXML
    public TableColumn<String,String> roomRating;

    @FXML
    public ComboBox reservationTypeFXML;

    @FXML
    public DatePicker datePickerFXML;

    @FXML
    public TextField nightCountFXML;

    public List<RoomViewModel> rooms = new ArrayList<>();

    ReservationService reservationService = new ReservationServiceImpl();

    public void reserve()
    {
        ReservationDTO reservationDTO = new ReservationDTO();

        //reservationService.createReservation();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hotelName.setCellValueFactory(new PropertyValueFactory<>("name"));
        hotelCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        hotelStars.setCellValueFactory(new PropertyValueFactory<>("stars"));
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("name"));
        roomCategory.setCellValueFactory(new PropertyValueFactory<>("city"));
        roomPricePerNight.setCellValueFactory(new PropertyValueFactory<>("stars"));
        roomRating.setCellValueFactory(new PropertyValueFactory<>("stars"));
        ObservableList<RoomViewModel> roomsList = FXCollections.observableArrayList(rooms);
        tbDataRooms.setItems(roomsList);
        ObservableList<HotelView> hotelList = hotelsProperties();
        tbDataHotels.setItems(hotelList);

        tbDataHotels.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tbDataRooms =
            }
        });
    }



    private ObservableList<HotelView> hotelsProperties()
    {
        return FXCollections.observableArrayList(hotelService.getAllHotels());
    }
}
