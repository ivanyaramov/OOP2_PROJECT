package sample.sceneControllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.DTOs.HotelDTO;
import sample.models.DTOs.RoomDTO;
import sample.models.hotels.RoomCategory;
import sample.services.HotelService;
import sample.services.impl.HotelServiceImpl;
import sample.utilities.RedirectScenes;
import sample.models.people.Role;
import sample.models.viemModels.PersonForCreateHotelViewModel;
import sample.models.viemModels.PersonForChoosingViewModel;
import sample.services.UserService;
import sample.services.impl.UserServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateNewHotelController implements Initializable {

    UserService userService = new UserServiceImpl();
    HotelService hotelService = new HotelServiceImpl();

    @FXML
    private TableView<PersonForCreateHotelViewModel> tbDataManagers;

    @FXML
    private TableView<PersonForCreateHotelViewModel> tbDataReceptionists;

    @FXML
    private TableView<RoomDTO> tbDataRooms;

    @FXML
    public TableColumn<PersonForCreateHotelViewModel,String> managerUsername;

    @FXML
    public TableColumn<PersonForCreateHotelViewModel,String> managerFullname;

    @FXML
    public TableColumn<PersonForCreateHotelViewModel,String> receptionistUsername;

    @FXML
    public TableColumn<PersonForCreateHotelViewModel,String> receptionistFullname;

    @FXML
    public TableColumn<String,String> roomNumber;

    @FXML
    public TableColumn<String,String> roomType;

    @FXML
    public TableColumn<String,String> roomPrice;

    @FXML
    public TextField nameOfHotel;

    @FXML
    public TextField addressOfHotel;

    @FXML
    public ComboBox stars;

    @FXML
    private Label roomNumberTakenLabelFXML;
    @FXML
    private TextField roomNumberFXML;
    @FXML
    private ComboBox typeOfRoomFXML;
    @FXML
    private TextField priceOfRoomFXML;


    public List<RoomDTO> rooms = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<PersonForCreateHotelViewModel> managers = loadProperties(Role.MANAGER);
        ObservableList<PersonForCreateHotelViewModel> receptionists = loadProperties(Role.RECEPTIONIST);

        managerUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        managerFullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        receptionistUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        receptionistFullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomCategory"));
        roomPrice.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        ObservableList<RoomDTO> roomsList = FXCollections.observableArrayList(rooms);
        tbDataManagers.setItems(managers);
        tbDataReceptionists.setItems(receptionists);
        tbDataRooms.setItems(roomsList);
        tbDataReceptionists.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void redirectBack(ActionEvent event) throws IOException{
        RedirectScenes.redirect(event,"main");
    }

    private ObservableList<PersonForCreateHotelViewModel> loadProperties(Role role){
        List<PersonForChoosingViewModel> peopleWithRoles = null;
        List<PersonForCreateHotelViewModel> properties = new ArrayList<>();
        try{
            peopleWithRoles = userService.getPersonViewByRole(role);
            for(PersonForChoosingViewModel p : peopleWithRoles) {
                SimpleStringProperty username = new SimpleStringProperty(p.getUsername());
                SimpleStringProperty fullname = new SimpleStringProperty(p.getFullName());
                PersonForCreateHotelViewModel chvm = new PersonForCreateHotelViewModel(username,fullname);
                properties.add(chvm);
            }
        }
        catch ( Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return FXCollections.observableArrayList(properties);
    }

    public void createNewHotel(){
        PersonForCreateHotelViewModel chvmM = tbDataManagers.getSelectionModel().getSelectedItem();
        List<PersonForCreateHotelViewModel> chvmR = tbDataReceptionists.getSelectionModel().getSelectedItems();
        String nameOfHotelData = nameOfHotel.getText();
        String nameOfAddressData = addressOfHotel.getText();
        String starsCount = stars.getValue().toString();

        //TODO: Create createHotel method which takes first parameter one CreateHotelViewModel the manager and List if CreateHotelViewModel the receptionists
        HotelDTO hotelDTO = new HotelDTO(nameOfHotelData, nameOfAddressData, Integer.parseInt(starsCount), chvmM, chvmR);
        hotelService.createHotel(hotelDTO, rooms);
    }

    public void createNewEntertainment(ActionEvent event) throws  IOException{
        RedirectScenes.redirect(event,"createEntertainment");
    }

    public void createNewRoom(){
        for(RoomDTO room : rooms)
        {
            if(room.getNumber() == Integer.parseInt(roomNumberFXML.getText()))
            {
                roomNumberTakenLabelFXML.setText("Вече има стая с този номер");
                return;
            }
        }
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setNumber(Integer.parseInt(roomNumberFXML.getText()));
        roomDTO.setRoomCategory(RoomCategory.valueOf((String) typeOfRoomFXML.getValue()));
        roomDTO.setPricePerNight(Double.parseDouble(priceOfRoomFXML.getText()));
        rooms.add(roomDTO);
        ObservableList<RoomDTO> roomsList = FXCollections.observableArrayList(rooms);
        tbDataRooms.setItems(roomsList);
        return;
    }

    public void createNewManager(ActionEvent event) throws IOException {
        RedirectScenes.redirect(event,"createNewManager");
    }
}
