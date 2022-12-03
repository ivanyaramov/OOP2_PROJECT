package sample.services.impl;

import org.modelmapper.ModelMapper;
import sample.currentLogin.CurrentLoggedUser;
import sample.models.DTOs.RoomDTO;
import sample.models.hotels.Hotel;
import sample.models.hotels.Room;
import sample.models.people.Person;
import sample.models.ratings.ClientRating;
import sample.models.ratings.RoomRating;
import sample.models.reservations.Reservation;
import sample.models.viemModels.ReservationViewModel;
import sample.models.viemModels.RoomViewModel;
import sample.repository.HotelRepository;
import sample.repository.HotelRepositoryImpl;
import sample.repository.RoomRepository;
import sample.repository.RoomRepositoryImpl;
import sample.services.HotelService;
import sample.services.RoomService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {
    private ModelMapper modelMapper = new ModelMapper();
    private HotelService hotelService = new HotelServiceImpl();
    private RoomRepository roomRepository = new RoomRepositoryImpl();
    private HotelRepository hotelRepository = new HotelRepositoryImpl();
    @Override
    public boolean roomNumberExists(int number) {
        return roomRepository.roomNumberExists(number);
    }

    @Override
    public boolean createRoom(RoomDTO roomDTO) {
        if(!roomNumberExists(roomDTO.getNumber())){
            Room room = modelMapper.map(roomDTO, Room.class);
            roomRepository.save(room);
            return true;
        }
        return false;
    }

    @Override
    public void createRooms(List<RoomDTO> rooms, Long hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        for(RoomDTO roomDTO : rooms){
            roomDTO.setHotel(hotel);
            createRoom(roomDTO);
        }
    }

    @Override
    public List<RoomViewModel> getAvailableRoomsByHotelId(Long hotelId) {
        return roomRepository.getAvailableRoomsByHotelId(hotelId).stream().map(r->modelMapper.map(r, RoomViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public Room getRoomByRoomId(Long roomId) {
        return roomRepository.getByRoomId(roomId);
    }

    @Override
    public void updateRoomRating(Room room, List<RoomRating> ratings) {
        double avg = 0;
        for(RoomRating rating : ratings){
            avg += rating.getRating();
        }
        avg = avg / ratings.size();
        room.setRating(avg);
        roomRepository.update(room);
    }

    public List<RoomViewModel> getReservationsForUser() {
        Person currentUser = CurrentLoggedUser.getLoggedUser();
        List<Room> rooms;
        switch (currentUser.getRole()){

            case MANAGER:
                rooms = roomRepository.getReservationsByHotelIds(Arrays.asList(hotelRepository.getIdOfHotelsForManager(currentUser.getId())));
                break;
            case RECEPTIONIST:
                rooms = roomRepository.getReservationsByHotelIds(Arrays.asList(hotelRepository.getIdOfHotelsForReceptionist(currentUser.getId())));
                break;
            case OWNER:
                rooms = roomRepository.getReservationsByHotelIds(hotelRepository.getIdsOfHotelsForOwner(currentUser.getId()));
                break;
            default:
                return null;
        }
        return rooms
                .stream().map(r->modelMapper.map(r, RoomViewModel.class)).collect(Collectors.toList());

    }
}
