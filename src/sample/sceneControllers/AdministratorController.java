package sample.sceneControllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.utilities.RedirectScenes;
import sample.models.viemModels.AdministratorViewModel;
import sample.models.viemModels.PersonWithRoleViewModel;
import sample.services.UserService;
import sample.services.impl.UserServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdministratorController implements Initializable {
    UserService userService = new UserServiceImpl();

    @FXML
    private TableView<AdministratorViewModel> tbData;

    @FXML
    public TableColumn<AdministratorViewModel,String> username;

    @FXML
    public TableColumn<AdministratorViewModel,String> role;

    @FXML
    public ComboBox roleFXML;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<AdministratorViewModel> administratorViewModels = loadProperties();
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));

        tbData.setItems(administratorViewModels);

    }

    private ObservableList<AdministratorViewModel> loadProperties(){
        List<PersonWithRoleViewModel> peopleWithRoles = null;
        List<AdministratorViewModel> administratorViewModelList = new ArrayList<>();
        try{
            peopleWithRoles = userService.getAllPeopleWithRoles();
            for(PersonWithRoleViewModel p : peopleWithRoles) {
            SimpleStringProperty username = new SimpleStringProperty(p.getUsername());
            String rol = p.getRole().toString();
            SimpleStringProperty role = new SimpleStringProperty(rol);
            AdministratorViewModel avm = new AdministratorViewModel(username,role);
            administratorViewModelList.add(avm);
            }
        }
        catch ( Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return FXCollections.observableArrayList(administratorViewModelList);
    }

    public void changeRole() {
        AdministratorViewModel avm = tbData.getSelectionModel().getSelectedItem();
        String newRole = (String) roleFXML.getValue();
        userService.changePersonRole(avm.getUsername(), newRole);
    }

    public void createNewOwner(ActionEvent event) throws IOException {
        RedirectScenes.redirect(event,"createNewOwner");
    }
}
