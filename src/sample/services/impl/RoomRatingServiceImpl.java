package sample.services.impl;

import sample.currentLogin.CurrentLoggedUser;
import sample.models.hotels.Room;
import sample.models.people.Person;
import sample.models.ratings.ClientRating;
import sample.models.ratings.RoomRating;
import sample.models.reservations.Reservation;
import sample.repository.RoomRatingRepository;
import sample.repository.RoomRatingRepositoryImpl;
import sample.services.ReservationService;
import sample.services.RoomRatingService;
import sample.services.RoomService;

import javax.persistence.NoResultException;
import java.util.List;

public class RoomRatingServiceImpl implements RoomRatingService {
   private RoomService roomService = new RoomServiceImpl();
   private RoomRatingRepository roomRatingRepository = new RoomRatingRepositoryImpl();
   private ReservationService reservationService = new ReservationServiceImpl();
    @Override
    public void createRating(Long id, double rating, Long reservationId) {
        Room room = roomService.getRoomByRoomId(id);
        Reservation reservation = reservationService.getById(reservationId);
        RoomRating roomRating = new RoomRating(CurrentLoggedUser.getLoggedUser(), room, rating, reservation);
        roomRatingRepository.save(roomRating);
        List<RoomRating> ratings = roomRatingRepository.getRatingsByRoomId(id);
        roomService.updateRoomRating(room, ratings);
    }

    @Override
    public String getRatingByReservationId(Long id) {
        double rating = 0;
        try {
            rating = roomRatingRepository.getRatingByReservationId(id);
        }
        catch(NoResultException ex){
            return "NOT RATED";
        }
        return String.valueOf(rating);
    }
}
