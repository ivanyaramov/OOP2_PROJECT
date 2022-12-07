package sample.repository;

import sample.models.hotels.Room;
import sample.models.ratings.ClientRating;
import sample.models.ratings.RoomRating;

import java.util.List;

public class RoomRatingRepositoryImpl extends RepositoryImpl implements RoomRatingRepository{
    @Override
    public List<RoomRating> getRatingsByRoomId(Long roomId) {
        String hql = "FROM roomrating WHERE room_id = " + roomId;
        return (List<RoomRating>) databaseService.getListOfObjectsByQuery(hql);
    }

    @Override
    public double getRatingByReservationId(Long id) {
        String hql = "FROM roomrating WHERE reservation_id = " + id;
        RoomRating roomRating = (RoomRating) databaseService.getObjectByQuery(hql);
        return roomRating.getRating();
    }
}
