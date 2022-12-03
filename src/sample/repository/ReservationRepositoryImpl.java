package sample.repository;

import sample.DBService.DatabaseService;
import sample.models.reservations.Reservation;

import java.util.List;

public class ReservationRepositoryImpl extends RepositoryImpl implements ReservationRepository{
    private DatabaseService databaseService = new DatabaseService();

    @Override
    public List<Reservation> getReservationsByClientId(Long id) {
        String hql = "FROM Reservation WHERE client_id = " + id;
        return (List<Reservation>) databaseService.getListOfObjectsByQuery(hql);
    }

    @Override
    public void updateReservation(Reservation reservation) {
        databaseService.updateObject(reservation);
    }

    @Override
    public Reservation getReservationById(Long id) {
        String hql = "FROM Reservation WHERE id = " + id;
        return (Reservation) databaseService.getObjectByQuery(hql);
    }

    @Override
    public List<Reservation> getReservationsByHotelIds(List<Long> ids) {
        String hql = createHQLForGetByHotelIds(ids);
        return (List<Reservation>) databaseService.getListOfObjectsByQuery(hql);
    }

    private String createHQLForGetByHotelIds(List<Long> ids){
        StringBuilder sb = new StringBuilder("FROM Reservation WHERE");
        for(int i = 0 ; i < ids.size(); i++){
            sb.append(" hotel_id = " + ids.get(i));
            if(i != ids.size() - 1){
                sb.append(" OR");
            }
        }
        return sb.toString();
    }
}
