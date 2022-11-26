package sample.services;

import sample.models.DTOs.RoomDTO;

public interface RoomService {
    boolean roomNumberExists(int number);
    boolean createRoom(RoomDTO roomDTO);
}
