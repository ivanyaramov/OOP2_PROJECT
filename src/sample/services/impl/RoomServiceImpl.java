package sample.services.impl;

import org.modelmapper.ModelMapper;
import sample.DBService.DatabaseService;
import sample.models.DTOs.RoomDTO;
import sample.models.hotels.Hotel;
import sample.models.hotels.Room;
import sample.models.people.Person;
import sample.services.HotelService;
import sample.services.RoomService;

import javax.persistence.NoResultException;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    DatabaseService dbService = new DatabaseService();
    ModelMapper modelMapper = new ModelMapper();
    HotelService hotelService = new HotelServiceImpl();
    @Override
    public boolean roomNumberExists(int number) {
        String hql = "FROM Room r WHERE r.number = " + number;
        return dbService.objectExistsByQuery(hql);
    }

    @Override
    public boolean createRoom(RoomDTO roomDTO) {
        if(!roomNumberExists(roomDTO.getNumber())){
            Room room = modelMapper.map(roomDTO, Room.class);
            dbService.saveObject(room);
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
}
