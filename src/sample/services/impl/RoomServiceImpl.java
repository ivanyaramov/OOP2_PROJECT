package sample.services.impl;

import org.modelmapper.ModelMapper;
import sample.DBService.DatabaseService;
import sample.models.DTOs.RoomDTO;
import sample.models.hotels.Room;
import sample.models.people.Person;
import sample.services.RoomService;

import javax.persistence.NoResultException;

public class RoomServiceImpl implements RoomService {
    DatabaseService dbService = new DatabaseService();
    ModelMapper modelMapper = new ModelMapper();
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
}
