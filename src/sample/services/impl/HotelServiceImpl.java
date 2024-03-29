package sample.services.impl;

import org.modelmapper.ModelMapper;
import sample.currentLogin.CurrentLoggedUser;
import sample.logger.Logger;
import sample.models.DTOs.HotelDTO;
import sample.models.DTOs.RoomDTO;
import sample.models.hotels.Hotel;
import sample.models.hotels.Room;
import sample.models.people.Person;
import sample.models.viemModels.HotelViewModel;
import sample.models.viemModels.PersonForCreateHotelViewModel;
import sample.repository.HotelRepository;
import sample.repository.HotelRepositoryImpl;
import sample.services.HotelService;
import sample.services.RoomService;
import sample.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {
    private HotelRepository hotelRepository = new HotelRepositoryImpl();
    private ModelMapper modelMapper = new ModelMapper();
    private UserService userService = new UserServiceImpl();
    private RoomService roomService = new RoomServiceImpl();

    @Override
    public void createHotel(HotelDTO hotelDTO, List<RoomDTO> rooms) {
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
        Person manager = userService.getPersonByUsername(hotelDTO.getManager().getUsername());
        Person owner = CurrentLoggedUser.getLoggedUser();
        List<Person> receptionists = userService.getPeopleByListOfUsernames(hotelDTO.getReceptionists().stream().map(PersonForCreateHotelViewModel::getUsername).collect(Collectors.toList()));
        hotel.setManager(manager);
        hotel.setOwner(owner);
        hotel.setReceptionists(receptionists);
        hotelRepository.save(hotel);
        Logger.log("Hotel " + hotel.getName() + " created");
        Hotel savedHotel = hotelRepository.getHotelByName(hotel.getName());
        roomService.createRooms(rooms, savedHotel);
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.getById(id);
    }

    @Override
    public List<HotelViewModel> getAllHotels() {
        return hotelRepository.getAllHotels().stream().map(h -> modelMapper.map(h, HotelViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public void addReceptionistToHotel(Long hotelId, List<String> receptionistUsernames) {
        Hotel hotel = hotelRepository.getById(hotelId);
        for(String username : receptionistUsernames) {
            Person receptionist = userService.getPersonByUsername(username);
            hotel.getReceptionists().add(receptionist);
            Logger.log("Receptionist " + username + " added to hotel " + hotel.getName());
        }
        hotelRepository.update(hotel);

    }
}
