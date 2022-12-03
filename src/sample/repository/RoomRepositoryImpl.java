package sample.repository;

import sample.DBService.DatabaseService;
import sample.models.hotels.Room;
import sample.models.reservations.Reservation;

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
        String hql = "FROM Room WHERE hotel_id = 1 AND istaken = false";
        return (List<Room>) databaseService.getListOfObjectsByQuery(hql);
    }

    @Override
    public Room getByRoomId(Long roomId) {
        String hql = "FROM Room WHERE id = " + roomId;
        return (Room) databaseService.getObjectByQuery(hql);
    }

    @Override
    public List<Room> getReservationsByHotelIds(List<Long> ids) {
        String hql = createHQLForGetByHotelIds(ids);
        return (List<Room>) databaseService.getListOfObjectsByQuery(hql);
    }

    private String createHQLForGetByHotelIds(List<Long> ids){
        StringBuilder sb = new StringBuilder("FROM Room WHERE");
        for(int i = 0 ; i < ids.size(); i++){
            sb.append(" hotel_id = " + ids.get(i));
            if(i != ids.size() - 1){
                sb.append(" OR");
            }
        }
        return sb.toString();
    }
}
