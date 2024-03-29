package sample.repository;

import sample.DBService.DatabaseService;
import sample.models.ratings.ClientRating;

import java.util.List;

public class ClientRatingRepositoryImpl extends RepositoryImpl implements ClientRatingRepository{
    private DatabaseService databaseService = new DatabaseService();
    @Override
    public List<ClientRating> getRatingsByPersonId(Long id) {
        String hql = "FROM ClientRating WHERE client_id = " + id;
        return (List<ClientRating>) databaseService.getListOfObjectsByQuery(hql);
    }

    @Override
    public double getRatingByReservationId(Long id) {
        String hql = "FROM ClientRating WHERE reservation_id = " + id;
        ClientRating clientRating = (ClientRating) databaseService.getObjectByQuery(hql);
        return clientRating.getRating();
    }
}
