package sample.repository;

import sample.models.hotels.Room;

import java.util.List;

public interface RoomRepository extends Repository {
    boolean roomNumberExists(int number, Long hotelId);
    List<Room> getAvailableRoomsByHotelId(Long hotelId);
    Room getByRoomId(Long roomId);
    List<Room> getReservationsByHotelIds(List<Long> ids);
}
