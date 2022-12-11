package sample.repository;

import sample.DBService.DatabaseService;
import sample.models.entertainment.Entertainment;

import java.util.List;

public class EntertainmentRepositoryImpl extends RepositoryImpl implements EntertainmentRepository{
    private DatabaseService databaseService = new DatabaseService();
    @Override
    public List<Entertainment> getEntertainmentsByReservationId(Long reservationId) {
        String hql = "FROM Entertainment WHERE reservation_id = " + reservationId;
        return (List<Entertainment>) databaseService.getListOfObjectsByQuery(hql);
    }

    @Override
    public List<Entertainment> getAllEntertainmentsByHotelId(Long hotelId) {
        String hql = "FROM Entertainment WHERE hotel_id = " + hotelId;
        return (List<Entertainment>) databaseService.getListOfObjectsByQuery(hql);
    }

    @Override
    public Entertainment getById(Long id) {
        String hql = "FROM Entertainment WHERE id = " + id;
        return (Entertainment) databaseService.getObjectByQuery(hql);
    }
}
