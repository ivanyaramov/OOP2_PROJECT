package sample.sceneControllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.people.Role;
import sample.models.viemModels.HotelViewModel;
import sample.models.viemModels.PersonForChoosingViewModel;
import sample.models.viemModels.PersonForCreateHotelViewModel;
import sample.services.HotelService;
import sample.services.UserService;
import sample.services.impl.HotelServiceImpl;
import sample.services.impl.UserServiceImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditHotelController implements Initializable {

    HotelService hotelService = new HotelServiceImpl();

    UserService userService = new UserServiceImpl();

    @FXML
    private TableView<HotelViewModel> tbDataHotels;

    @FXML
    public TableColumn<String,String> hotelName;

    @FXML
    public TableColumn<String,String> hotelCity;

    @FXML
    public TableColumn<String,String> hotelStars;

    @FXML
    private TableView<PersonForCreateHotelViewModel> tbDataReceptionists;

    @FXML
    public TableColumn<PersonForCreateHotelViewModel,String> receptionistUsername;

    @FXML
    public TableColumn<PersonForCreateHotelViewModel,String> receptionistFullname;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<PersonForCreateHotelViewModel> receptionists = loadProperties(Role.RECEPTIONIST);
        hotelName.setCellValueFactory(new PropertyValueFactory<>("name"));
        hotelCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        hotelStars.setCellValueFactory(new PropertyValueFactory<>("stars"));
        receptionistUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        receptionistFullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        tbDataReceptionists.setItems(receptionists);
        ObservableList<HotelViewModel> hotelList = hotelsProperties();
        tbDataHotels.setItems(hotelList);
        tbDataReceptionists.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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

    private ObservableList<HotelViewModel> hotelsProperties()
    {
        return FXCollections.observableArrayList(hotelService.getAllHotels());
    }

    public void addNewReceptionist(){
        List<PersonForCreateHotelViewModel> chvmR = tbDataReceptionists.getSelectionModel().getSelectedItems();
        List<String> usernames = null;
        for(PersonForCreateHotelViewModel rec : chvmR)
        {
            usernames.add(rec.getUsername());
        }
        Long hotelId = tbDataHotels.getSelectionModel().getSelectedItem().getId();


    }
}
