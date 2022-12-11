package sample.sceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.currentLogin.CurrentLoggedUser;
import sample.models.DTOs.RoomDTO;
import sample.models.people.Role;
import sample.models.ratings.RoomRating;
import sample.models.viemModels.EntertainmentViewModel;
import sample.models.viemModels.ReservationViewModel;
import sample.models.viemModels.RoomViewModel;
import sample.services.ClientRatingService;
import sample.services.ReservationService;
import sample.services.RoomRatingService;
import sample.services.impl.ClientRatingServiceImpl;
import sample.services.impl.ReservationServiceImpl;
import sample.services.impl.RoomRatingServiceImpl;
import sample.utilities.RedirectScenes;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationListController implements Initializable {

    ReservationService reservationService = new ReservationServiceImpl();
    RoomRatingService roomRatingService = new RoomRatingServiceImpl();
    ClientRatingService clientRatingService = new ClientRatingServiceImpl();

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

    @FXML
    public Label labelForTextFXML;

    @FXML
    public TextField rateFXML;

    @FXML
    public Button buttonForRatingFXML;

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
        labelForTextFXML.setVisible(false);
        rateFXML.setVisible(false);
        buttonForRatingFXML.setVisible(false);

        tbDataReservations.setItems(reservationsViewModelObservableList);

        tbDataReservations.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
               if(CurrentLoggedUser.getLoggedUser().getRole().equals(Role.CLIENT)){
                   labelForTextFXML.setText("rate your room for this reservation");
                   labelForTextFXML.setVisible(true);
                   rateFXML.setVisible(true);
                   buttonForRatingFXML.setVisible(true);
               }
               else{
                   labelForTextFXML.setText("rate client for this reservation");
                   labelForTextFXML.setVisible(true);
                   rateFXML.setVisible(true);
                   buttonForRatingFXML.setVisible(true);
               }
            }
        });
    }

    public void redirectBack(ActionEvent event) throws IOException {
        RedirectScenes.redirect(event,"main");
    }

    public void rate(){
        Double rate = Double.valueOf(rateFXML.getText());
        if(CurrentLoggedUser.getLoggedUser().getRole().equals(Role.CLIENT)){
            roomRatingService.createRating(tbDataReservations.getSelectionModel().getSelectedItem().getRoomId(),rate,tbDataReservations.getSelectionModel().getSelectedItem().getId());
            return;
        }
        else {
            clientRatingService.createRatingForPerson(CurrentLoggedUser.getLoggedUser().getUsername(),rate,tbDataReservations.getSelectionModel().getSelectedItem().getId());
            return;
        }
    }
}
