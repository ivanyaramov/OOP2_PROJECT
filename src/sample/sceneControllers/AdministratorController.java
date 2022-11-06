package sample.sceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.viemModels.AdministratorViewModel;
import sample.services.UserService;
import sample.services.impl.UserServiceImpl;

import java.net.URL;
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
        List<AdministratorViewModel> administratorViewModels = null;
        try{
            administratorViewModels = userService.getAllPeopleWithRoles();
        }
        catch ( Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return FXCollections.observableArrayList(administratorViewModels);
    }

    public void changeRole() {
        ObservableList<TablePosition> selectedCells = tbData.getSelectionModel().getSelectedCells();
        TablePosition tablePosition = selectedCells.get(0);
        int row = tablePosition.getRow();
        String newRole = (String) roleFXML.getValue();
        userService.changePersonRole((String)tablePosition.getTableColumn().getCellData(row), newRole);
    }
}
