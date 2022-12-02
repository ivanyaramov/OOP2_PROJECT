package sample.sceneControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.models.DTOs.ReservationDTO;
import sample.models.hotels.Hotel;
import sample.models.people.Role;
import sample.models.viemModels.HotelViewModel;
import sample.models.viemModels.PersonForCreateHotelViewModel;
import sample.services.HotelService;
import sample.services.ReservationService;
import sample.services.impl.HotelServiceImpl;
import sample.services.impl.ReservationServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    HotelService hotelService = new HotelServiceImpl();

    @FXML
    private TableView<PersonForCreateHotelViewModel> tbDataHotels;

    @FXML
    public TableColumn<String,String> hotelName;

    @FXML
    public TableColumn<String,String> hotelCity;

    @FXML
    public TableColumn<String,String> hotelStars;

    @FXML
    public ComboBox reservationTypeFXML;

    @FXML
    public DatePicker datePickerFXML;

    @FXML
    public TextField nightCountFXML;

    ReservationService reservationService = new ReservationServiceImpl();

    public void reserve()
    {
        ReservationDTO reservationDTO = new ReservationDTO();

        //reservationService.createReservation();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<HotelViewModel> managers = hotelsProperties();
    }

    private ObservableList<HotelViewModel> hotelsProperties()
    {
        List<Hotel> hotels = hotelService.getAllHotels();
    }
}
