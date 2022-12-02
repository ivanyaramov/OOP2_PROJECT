package sample.repository;

import sample.models.hotels.Room;

import java.util.List;

public interface RoomRepository extends Repository {
    boolean roomNumberExists(int number);
    List<Room> getAvailableRoomsByHotelId(Long hotelId);
}
