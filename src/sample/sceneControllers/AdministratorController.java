package sample.sceneControllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.people.Gender;
import sample.models.viemModels.AdministratorViewModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdministratorController implements Initializable {

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
        List<AdministratorViewModel> administratorViewModels = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/OOP4",
                            "postgres", "postgres");

            stmt = c.createStatement();
            String sql = "SELECT person.username, person.role FROM person";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Object user = rs.getString("username");
                SimpleStringProperty username = new SimpleStringProperty((String) user);
                Object rol = rs.getString("role");
                SimpleStringProperty role = new SimpleStringProperty((String) rol);
                AdministratorViewModel avm = new AdministratorViewModel(username,role);
                administratorViewModels.add(avm);
            }
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        ObservableList<AdministratorViewModel> oAdministratorViewModels = FXCollections.observableArrayList(administratorViewModels);
        return oAdministratorViewModels;
    }

    public void changeRole() {
        AdministratorViewModel avm = (AdministratorViewModel) tbData.getSelectionModel().getSelectedCells();
        avm.getUsername();
        String newRole = (String) roleFXML.getValue();
    }
}
