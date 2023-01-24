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
import java.util.ArrayList;
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

    @FXML
    public Label labelForValidationFXML;

    @FXML
    public Button prematureEndReservationFXML;

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
        prematureEndReservationFXML.setVisible(false);
        tbDataReservations.setItems(reservationsViewModelObservableList);

        tbDataReservations.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
               rateFXML.setVisible(false);
               buttonForRatingFXML.setVisible(false);

               if(!tbDataReservations.getSelectionModel().getSelectedItem().isEnded())
                return;
               if(CurrentLoggedUser.getLoggedUser().getRole().equals(Role.CLIENT)){
                   if(!roomRatingService.getRatingByReservationId(tbDataReservations.getSelectionModel().getSelectedItem().getId()).equals("NOT RATED")){
                       labelForTextFXML.setText("Вече си оценил стаята за тази резервация");
                       labelForTextFXML.setVisible(true);
                       return;
                   }
                   labelForTextFXML.setText("Оцени стаята за тази резервация от 1-10");
                   labelForTextFXML.setVisible(true);
                   rateFXML.setVisible(true);
                   buttonForRatingFXML.setVisible(true);
               }
               else if(CurrentLoggedUser.getLoggedUser().getRole().equals(Role.MANAGER)){
                   if(!tbDataReservations.getSelectionModel().getSelectedItem().isEnded())
                   {
                       prematureEndReservationFXML.setVisible(true);
                   }
               }
               else{
                   if(!clientRatingService.getRatingByReservationId(tbDataReservations.getSelectionModel().getSelectedItem().getId()).equals("NOT RATED")){
                       labelForTextFXML.setText("Вече си оценил клиента за тази резервация");
                       labelForTextFXML.setVisible(true);
                       return;
                   }

                   labelForTextFXML.setText("Оцени клиента за тази резервация 1-10");
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

    public void endReservation(){
        reservationService.endReservationPrematurely(tbDataReservations.getSelectionModel().getSelectedItem().getId());
        prematureEndReservationFXML.setVisible(false);
    }

    public void rate(){

        labelForTextFXML.setVisible(false);
        int result = tryParseInt(rateFXML.getText(),11);
        if(result == 11)
        {
            labelForTextFXML.setVisible(true);
            labelForTextFXML.setText("Невалиден рейтинг");
        }
        Double rate = Double.valueOf(rateFXML.getText());
        if(CurrentLoggedUser.getLoggedUser().getRole().equals(Role.CLIENT)){
            roomRatingService.createRating(tbDataReservations.getSelectionModel().getSelectedItem().getRoomId(),rate,tbDataReservations.getSelectionModel().getSelectedItem().getId());
            rateFXML.setVisible(false);
            buttonForRatingFXML.setVisible(false);
            return;
        }
        else {
            clientRatingService.createRatingForPerson(CurrentLoggedUser.getLoggedUser().getUsername(),rate,tbDataReservations.getSelectionModel().getSelectedItem().getId());
            rateFXML.setVisible(false);
            buttonForRatingFXML.setVisible(false);
            return;
        }
    }

    private int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }
}
