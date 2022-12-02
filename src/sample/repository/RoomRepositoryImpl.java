package sample.repository;

import sample.DBService.DatabaseService;
import sample.models.hotels.Room;

import java.util.List;

public class RoomRepositoryImpl extends RepositoryImpl implements RoomRepository {
    private DatabaseService databaseService = new DatabaseService();

    @Override
    public boolean roomNumberExists(int number) {
        String hql = "FROM Room r WHERE r.number = " + number;
        return databaseService.objectExistsByQuery(hql);
    }

    @Override
    public List<Room> getAvailableRoomsByHotelId(Long hotelId) {
        String hql = "FROM Room r WHERE r.hotel_id = " + hotelId + " AND istaken = false";
        return (List<Room>) databaseService.getListOfObjectsByQuery(hql);
    }
}
