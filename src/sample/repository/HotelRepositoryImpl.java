package sample.repository;

import sample.DBService.DatabaseService;
import sample.models.hotels.Hotel;

public class HotelRepositoryImpl extends RepositoryImpl implements HotelRepository{
private DatabaseService databaseService = new DatabaseService();

    @Override
    public Hotel getById(Long id) {
        String hql = "FROM Hotel h WHERE h.id = " + id;
        return (Hotel) databaseService.getObjectByQuery(hql);
    }
}
