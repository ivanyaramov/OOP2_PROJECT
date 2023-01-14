package sample.services;

import sample.models.DTOs.RoomDTO;
import sample.models.hotels.Hotel;
import sample.models.hotels.Room;
import sample.models.ratings.RoomRating;
import sample.models.viemModels.RoomViewModel;

import java.util.List;

public interface RoomService {
    boolean roomNumberExists(int number, Long hotelId);
    boolean createRoom(RoomDTO roomDTO, Hotel hotel);
    void createRooms(List<RoomDTO> rooms, Hotel hotel);
    List<RoomViewModel> getAvailableRoomsByHotelId(Long hotelId);
    Room getRoomByRoomId(Long roomId);
    void updateRoomRating(Room room, List<RoomRating> ratings);
    List<RoomViewModel> getRoomsForUserHotels();
    void updateRoomIsTaken(Room room, boolean taken);
}
