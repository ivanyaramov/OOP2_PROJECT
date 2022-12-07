package sample.repository;

import sample.models.ratings.ClientRating;

import java.util.List;

public interface ClientRatingRepository extends Repository{
    List<ClientRating> getRatingsByPersonUsername(Long id);
    double getRatingByReservationId(Long id);


}
