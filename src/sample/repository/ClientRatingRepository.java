package sample.repository;

import sample.models.ratings.ClientRating;

import java.util.List;

public interface ClientRatingRepository extends Repository{
    List<ClientRating> getRatingsByPersonId(Long id);
    double getRatingByReservationId(Long id);


}
