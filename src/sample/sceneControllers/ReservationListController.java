package sample.sceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.DTOs.RoomDTO;
import sample.models.viemModels.ReservationViewModel;
import sample.models.viemModels.RoomViewModel;
import sample.services.ReservationService;
import sample.services.impl.ReservationServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservationListController implements Initializable {

    ReservationService reservationService = new ReservationServiceImpl();

    @FXML
    private TableView<ReservationViewModel> tbDataReservations;

    @FXML
    public TableColumn<String,String> reservationType;

    @FXML
    public TableColumn<String,String> reservationDateOfArrival;

    @FXML
    public TableColumn<String,String> reservationDays;

    @FXML
    public TableColumn<String,String> reservationRoomId;

    @FXML
    public TableColumn<String,String> reservationRoomCategory;

    @FXML
    public TableColumn<String,String> reservationHotelName;

    @FXML
    public TableColumn<String,String> reservationEnded;

    @FXML
    public TableColumn<String,String> reservationNearlyEnded;

    @FXML
    public TableColumn<String,String> reservationRisky;

    @FXML
    public TableColumn<String,String> reservationPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<ReservationViewModel> reservationsViewModelObservableList = FXCollections.observableArrayList(reservationService.getReservationsForUser());

        reservationType.setCellValueFactory(new PropertyValueFactory<>("type"));
        reservationDateOfArrival.setCellValueFactory(new PropertyValueFactory<>("dateOfArrival"));
        reservationDays.setCellValueFactory(new PropertyValueFactory<>("days"));
        reservationRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        reservationRoomCategory.setCellValueFactory(new PropertyValueFactory<>("roomCategory"));
        reservationHotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        reservationEnded.setCellValueFactory(new PropertyValueFactory<>("ended"));
        reservationNearlyEnded.setCellValueFactory(new PropertyValueFactory<>("nearlyEnded"));
        reservationRisky.setCellValueFactory(new PropertyValueFactory<>("risky"));
        reservationPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tbDataReservations.setItems(reservationsViewModelObservableList);
    }
}
