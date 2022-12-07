package sample.repository;

import sample.models.ratings.RoomRating;

import java.util.List;

public interface RoomRatingRepository extends Repository {
    List<RoomRating> getRatingsByRoomId(Long roomId);
    double getRatingByReservationId(Long id);

}
