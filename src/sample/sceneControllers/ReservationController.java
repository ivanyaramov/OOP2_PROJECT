package sample.sceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.DTOs.EntertainmentDTO;
import sample.models.DTOs.ReservationDTO;
import sample.models.reservations.ReservationType;
import sample.models.viemModels.EntertainmentViewModel;
import sample.models.viemModels.HotelViewModel;
import sample.models.viemModels.RoomViewModel;
import sample.services.EntertainmentService;
import sample.services.HotelService;
import sample.services.ReservationService;
import sample.services.RoomService;
import sample.services.impl.EntertainmentServiceImpl;
import sample.services.impl.HotelServiceImpl;
import sample.services.impl.ReservationServiceImpl;
import sample.services.impl.RoomServiceImpl;
import sample.utilities.RedirectScenes;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReservationController implements Initializable {

    HotelService hotelService = new HotelServiceImpl();

    RoomService roomService = new RoomServiceImpl();

    ReservationService reservationService = new ReservationServiceImpl();

    EntertainmentService entertainmentService = new EntertainmentServiceImpl();

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
    private TableView<EntertainmentViewModel> tbDataEntertainments;

    @FXML
    public TableColumn<String,String> entertainmentName;

    @FXML
    public TableColumn<String,String> entertainmentPrice;

    @FXML
    public ComboBox reservationTypeFXML;

    @FXML
    public DatePicker datePickerFXML;

    @FXML
    public TextField nightCountFXML;

    @FXML
    public Label validationFXML;

    public List<RoomViewModel> rooms = new ArrayList<>();

    public void reserve(ActionEvent event) throws IOException {
        validationFXML.setVisible(false);
        if(nightCountFXML.getText().isEmpty() || Integer.parseInt(nightCountFXML.getText())<1)
        {
            validationFXML.setVisible(true);
            validationFXML.setText("Невалиден брой дни");
            return;
        }
        LocalDate now = LocalDate.now();
        LocalDate date1 = datePickerFXML.getValue();
        int compareResult = date1.compareTo(now);
        if(compareResult<1)
        {
            validationFXML.setVisible(true);
            validationFXML.setText("Невалидна дата");
            return;
        }
        ReservationDTO reservationDTO = new ReservationDTO();
        ObservableList<EntertainmentViewModel> chosenEntertainments = tbDataEntertainments.getSelectionModel().getSelectedItems();
        List<Long> entertainmentIds = chosenEntertainments.stream().map(EntertainmentViewModel::getId).collect(Collectors.toList());
        reservationDTO.setType(ReservationType.valueOf(reservationTypeFXML.getSelectionModel().getSelectedItem().toString()));
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = datePickerFXML.getValue();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        reservationDTO.setDateOfArrival(date);
        reservationDTO.setDays(Integer.parseInt(nightCountFXML.getText()));
        reservationDTO.setHotel(tbDataHotels.getSelectionModel().getSelectedItem().getId());
        reservationDTO.setRoom(tbDataRooms.getSelectionModel().getSelectedItem().getId());
        reservationDTO.setEntertainmentIds(entertainmentIds);
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
        entertainmentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        entertainmentPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        ObservableList<RoomViewModel> roomsList = FXCollections.observableArrayList(rooms);
        tbDataRooms.setItems(roomsList);
        ObservableList<HotelViewModel> hotelList = hotelsProperties();
        tbDataHotels.setItems(hotelList);
        tbDataEntertainments.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tbDataHotels.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                List<EntertainmentViewModel> entertainments = entertainmentService.getAllEntertainmentsByHotelId(tbDataHotels.getSelectionModel().getSelectedItem().getId());
                List<RoomViewModel> availableRooms = roomService.getAvailableRoomsByHotelId(tbDataHotels.getSelectionModel().getSelectedItem().getId());
                ObservableList<RoomViewModel> availableRoomsObservable = FXCollections.observableArrayList(availableRooms);
                tbDataRooms.setItems(availableRoomsObservable);
                ObservableList<EntertainmentViewModel> entertainmentsObservable = FXCollections.observableArrayList(entertainments);
                tbDataEntertainments.setItems(entertainmentsObservable);
            }
        });
    }

    public void redirectBack(ActionEvent event) throws IOException{
        RedirectScenes.redirect(event,"main");
    }

    private ObservableList<HotelViewModel> hotelsProperties()
    {
        return FXCollections.observableArrayList(hotelService.getAllHotels());
    }
}
