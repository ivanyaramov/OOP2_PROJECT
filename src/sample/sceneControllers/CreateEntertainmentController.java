package sample.sceneControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.DTOs.EntertainmentDTO;
import sample.models.viemModels.HotelViewModel;
import sample.models.viemModels.RoomViewModel;
import sample.services.EntertainmentService;
import sample.services.HotelService;
import sample.services.impl.EntertainmentServiceImpl;
import sample.services.impl.HotelServiceImpl;
import sample.utilities.RedirectScenes;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateEntertainmentController implements Initializable {

    HotelService hotelService = new HotelServiceImpl();
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
    public TextField entertainmentNameFXML;

    @FXML
    public TextField priceOfEntertainmentFXML;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hotelName.setCellValueFactory(new PropertyValueFactory<>("name"));
        hotelCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        hotelStars.setCellValueFactory(new PropertyValueFactory<>("stars"));
        ObservableList<HotelViewModel> hotelList = hotelsProperties();
        tbDataHotels.setItems(hotelList);
    }

    private ObservableList<HotelViewModel> hotelsProperties()
    {
        return FXCollections.observableArrayList(hotelService.getAllHotels());
    }

    public void redirectBack(ActionEvent event) throws IOException {
        RedirectScenes.redirect(event,"main");
    }

    public void addEntertainment(){
        String nameOfEntertainment = entertainmentNameFXML.getText();
        double priceOfEntertainment = Double.parseDouble(priceOfEntertainmentFXML.getText());
        EntertainmentDTO entertainmentDTO = new EntertainmentDTO();
        entertainmentDTO.setName(nameOfEntertainment);
        entertainmentDTO.setPrice(priceOfEntertainment);
        entertainmentDTO.setHotelId(tbDataHotels.getSelectionModel().getSelectedItem().getId());
        entertainmentService.createEntertainment(entertainmentDTO);
    }

}
