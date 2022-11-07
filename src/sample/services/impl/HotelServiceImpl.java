package sample.services.impl;

import org.modelmapper.ModelMapper;
import sample.DBService.DatabaseService;
import sample.currentLogin.CurrentLoggedUser;
import sample.models.DTOs.HotelDTO;
import sample.models.hotels.Hotel;
import sample.models.people.Person;
import sample.models.viemModels.PersonForCreateHotelViewModel;
import sample.services.HotelService;
import sample.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {
    DatabaseService dbService = new DatabaseService();
    ModelMapper modelMapper = new ModelMapper();
    UserService userService = new UserServiceImpl();
    @Override
    public void createHotel(HotelDTO hotelDTO) {
    Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
    Person manager = userService.getPersonByUsername(hotelDTO.getManager().getUsername());
    Person owner = CurrentLoggedUser.getLoggedUser();
    List<Person> receptionists = userService.getPeopleByListOfUsernames(hotelDTO.getReceptionists().stream().map(PersonForCreateHotelViewModel::getUsername).collect(Collectors.toList()));
    hotel.setManager(manager);
    hotel.setOwner(owner);
    hotel.setReceptionists(receptionists);
    dbService.saveObject(hotel);
    }
}
