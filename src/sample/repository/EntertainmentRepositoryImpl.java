package sample.repository;

import sample.DBService.DatabaseService;
import sample.models.entertainment.Entertainment;

import java.util.List;

public class EntertainmentRepositoryImpl extends RepositoryImpl implements EntertainmentRepository{
    private DatabaseService databaseService = new DatabaseService();
    @Override
    public List<Entertainment> getEntertainmentsByReservationId(Long reservationId) {
        String hql = "FROM entertainment WHERE reservation_id = " + reservationId;
        return (List<Entertainment>) databaseService.getListOfObjectsByQuery(hql);
    }

    @Override
    public List<Entertainment> getAllEntertainments() {
        String hql = "FROM entertainment";
        return (List<Entertainment>) databaseService.getListOfObjectsByQuery(hql);
    }

    @Override
    public Entertainment getById(Long id) {
        String hql = "FROM entertainment WHERE id = " + id;
        return (Entertainment) databaseService.getListOfObjectsByQuery(hql);
    }
}
