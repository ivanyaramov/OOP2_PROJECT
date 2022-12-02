package sample.repository;

import sample.DBService.DatabaseService;
import sample.models.hotels.Room;

public class RoomRepositoryImpl extends RepositoryImpl  implements RoomRepository {
    private DatabaseService databaseService = new DatabaseService();

    @Override
    public boolean roomNumberExists(int number) {
        String hql = "FROM Room r WHERE r.number = " + number;
        return databaseService.objectExistsByQuery(hql);
    }
}
