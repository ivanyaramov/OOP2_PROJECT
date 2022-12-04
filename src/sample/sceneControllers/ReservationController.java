package sample.sceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.DTOs.ReservationDTO;
import sample.models.reservations.ReservationType;
import sample.models.viemModels.HotelViewModel;
import sample.models.viemModels.RoomViewModel;
import sample.services.HotelService;
import sample.services.ReservationService;
import sample.services.RoomService;
import sample.services.impl.HotelServiceImpl;
import sample.services.impl.ReservationServiceImpl;
import sample.services.impl.RoomServiceImpl;
import sample.utilities.RedirectScenes;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    HotelService hotelService = new HotelServiceImpl();

    RoomService roomService = new RoomServiceImpl();

    ReservationService reservationService = new ReservationServiceImpl();

    @FXML
    private TableView<HotelViewModel> tbDataHotels;

    @FXML
    public TableColumn<String,String> hotelName;

    @FXML
    public TableColumn<String,String> hotelCity;

    @FXML
    public TableColumn<String,String> hotelStars;

    @FXML
    private TableView<RoomViewModel> tbDataRooms;

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

    public void reserve(ActionEvent event) throws IOException {
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setType(ReservationType.valueOf(reservationTypeFXML.getSelectionModel().getSelectedItem().toString()));
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = datePickerFXML.getValue();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        reservationDTO.setDateOfArrival(date);
        reservationDTO.setDays(Integer.parseInt(nightCountFXML.getText()));
        reservationDTO.setHotel(tbDataHotels.getSelectionModel().getSelectedItem().getId());
        reservationDTO.setRoom(tbDataRooms.getSelectionModel().getSelectedItem().getId());
        reservationService.createReservation(reservationDTO);
        RedirectScenes.redirect(event,"main");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hotelName.setCellValueFactory(new PropertyValueFactory<>("name"));
        hotelCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        hotelStars.setCellValueFactory(new PropertyValueFactory<>("stars"));
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        roomCategory.setCellValueFactory(new PropertyValueFactory<>("roomCategory"));
        roomPricePerNight.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        roomRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ObservableList<RoomViewModel> roomsList = FXCollections.observableArrayList(rooms);
        tbDataRooms.setItems(roomsList);
        ObservableList<HotelViewModel> hotelList = hotelsProperties();
        tbDataHotels.setItems(hotelList);

        tbDataHotels.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                List<RoomViewModel> availableRooms = roomService.getAvailableRoomsByHotelId(tbDataHotels.getSelectionModel().getSelectedItem().getId());
                ObservableList<RoomViewModel> availableRoomsObservable = FXCollections.observableArrayList(availableRooms);
                tbDataRooms.setItems(availableRoomsObservable);
            }
        });
    }



    private ObservableList<HotelViewModel> hotelsProperties()
    {
        return FXCollections.observableArrayList(hotelService.getAllHotels());
    }
}
