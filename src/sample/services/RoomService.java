package sample.services;

import sample.models.DTOs.RoomDTO;
import sample.models.hotels.Room;
import sample.models.ratings.RoomRating;
import sample.models.viemModels.RoomViewModel;

import java.util.List;

public interface RoomService {
    boolean roomNumberExists(int number);
    boolean createRoom(RoomDTO roomDTO);
    void createRooms(List<RoomDTO> rooms, Long hotelId);
    List<RoomViewModel> getAvailableRoomsByHotelId(Long hotelId);
    Room getRoomByRoomId(Long roomId);
    void updateRoomRating(Room room, List<RoomRating> ratings);
    List<RoomViewModel> getReservationsForUser();
}
