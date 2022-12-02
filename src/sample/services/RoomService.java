package sample.services;

import sample.models.DTOs.RoomDTO;
import sample.models.viemModels.RoomView;

import java.util.List;

public interface RoomService {
    boolean roomNumberExists(int number);
    boolean createRoom(RoomDTO roomDTO);
    void createRooms(List<RoomDTO> rooms, Long hotelId);
    List<RoomView> getAvailableRoomsByHotelId(Long id);
}
