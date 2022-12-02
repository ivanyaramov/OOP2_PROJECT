package sample.services.impl;

import org.modelmapper.ModelMapper;
import sample.DBService.DatabaseService;
import sample.models.DTOs.RoomDTO;
import sample.models.hotels.Hotel;
import sample.models.hotels.Room;
import sample.models.people.Person;
import sample.models.viemModels.RoomView;
import sample.repository.RoomRepository;
import sample.repository.RoomRepositoryImpl;
import sample.services.HotelService;
import sample.services.RoomService;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {
    private ModelMapper modelMapper = new ModelMapper();
    private HotelService hotelService = new HotelServiceImpl();
    private RoomRepository roomRepository = new RoomRepositoryImpl();
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
    public List<RoomView> getAvailableRoomsByHotelId(Long id) {
        return roomRepository.getAvailableRoomsByHotelId(id).stream().map(r->modelMapper.map(r, RoomView.class)).collect(Collectors.toList());
    }
}
