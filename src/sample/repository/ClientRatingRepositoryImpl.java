package sample.repository;

import sample.DBService.DatabaseService;
import sample.models.ratings.ClientRating;

import java.util.List;

public class ClientRatingRepositoryImpl extends RepositoryImpl implements ClientRatingRepository{
    private DatabaseService databaseService = new DatabaseService();
    @Override
    public List<ClientRating> getRatingsByPersonUsername(Long id) {
        String hql = "FROM clientrating c WHERE c.client_id = " + id;
        return (List<ClientRating>) databaseService.getListOfObjectsByQuery(hql);
    }
}
