package sample.services;

import sample.models.DTOs.RoomDTO;

import java.util.List;

public interface RoomService {
    boolean roomNumberExists(int number);
    boolean createRoom(RoomDTO roomDTO);
    void createRooms(List<RoomDTO> rooms, Long hotelId);
}
