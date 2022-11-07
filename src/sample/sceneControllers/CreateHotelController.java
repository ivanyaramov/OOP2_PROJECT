package sample.sceneControllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Utilities.RedirectScenes;
import sample.models.people.Role;
import sample.models.viemModels.CreateHotelViewModel;
import sample.models.viemModels.PersonForChoosingView;
import sample.services.UserService;
import sample.services.impl.UserServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateHotelController implements Initializable {

    UserService userService = new UserServiceImpl();

    @FXML
    private TableView<CreateHotelViewModel> tbDataManagers;

    @FXML
    private TableView<CreateHotelViewModel> tbDataReceptionists;

    @FXML
    public TableColumn<CreateHotelViewModel,String> managerUsername;

    @FXML
    public TableColumn<CreateHotelViewModel,String> managerFullname;

    @FXML
    public TableColumn<CreateHotelViewModel,String> receptionistUsername;

    @FXML
    public TableColumn<CreateHotelViewModel,String> receptionistFullname;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<CreateHotelViewModel> managers = loadProperties(Role.MANAGER);
        ObservableList<CreateHotelViewModel> receptionists = loadProperties(Role.RECEPTIONIST);
        managerUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        managerFullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        receptionistUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        receptionistFullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));

        tbDataManagers.setItems(managers);
        tbDataReceptionists.setItems(receptionists);

    }

    private ObservableList<CreateHotelViewModel> loadProperties(Role role){
        List<PersonForChoosingView> peopleWithRoles = null;
        List<CreateHotelViewModel> properties = new ArrayList<>();
        try{
            peopleWithRoles = userService.getPersonViewByRole(role);
            for(PersonForChoosingView p : peopleWithRoles) {
                SimpleStringProperty username = new SimpleStringProperty(p.getUsername());
                SimpleStringProperty fullname = new SimpleStringProperty(p.getFullName());
                CreateHotelViewModel pfcv = new CreateHotelViewModel(username,fullname);
                properties.add(pfcv);
            }
        }
        catch ( Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return FXCollections.observableArrayList(properties);
    }

    public void createNewHotel(){

    }

    public void createNewManager(ActionEvent event) throws IOException {
        RedirectScenes.redirect(event,"createNewManager");
    }
}
