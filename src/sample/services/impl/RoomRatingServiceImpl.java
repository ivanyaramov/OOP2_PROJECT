package sample.services.impl;

import sample.currentLogin.CurrentLoggedUser;
import sample.models.hotels.Room;
import sample.models.people.Person;
import sample.models.ratings.ClientRating;
import sample.models.ratings.RoomRating;
import sample.repository.RoomRatingRepository;
import sample.repository.RoomRatingRepositoryImpl;
import sample.services.RoomRatingService;
import sample.services.RoomService;

import java.util.List;

public class RoomRatingServiceImpl implements RoomRatingService {
   private RoomService roomService = new RoomServiceImpl();
   private RoomRatingRepository roomRatingRepository = new RoomRatingRepositoryImpl();
    @Override
    public void createRating(Long id, double rating) {
        Room room = roomService.getRoomByRoomId(id);
        RoomRating roomRating = new RoomRating(CurrentLoggedUser.getLoggedUser(), room, rating);
        roomRatingRepository.save(roomRating);
        List<RoomRating> ratings = roomRatingRepository.getRatingsByRoomId(id);
        roomService.updateRoomRating(room, ratings);
    }
}
