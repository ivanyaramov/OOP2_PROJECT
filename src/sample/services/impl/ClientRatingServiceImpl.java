package sample.services.impl;

import sample.models.people.Person;
import sample.models.ratings.ClientRating;
import sample.models.reservations.Reservation;
import sample.repository.ClientRatingRepository;
import sample.repository.ClientRatingRepositoryImpl;
import sample.services.ClientRatingService;
import sample.services.ReservationService;
import sample.services.UserService;

import javax.persistence.NoResultException;
import java.util.List;

public class ClientRatingServiceImpl implements ClientRatingService {
 private UserService userService = new UserServiceImpl();
 private ReservationService reservationService = new ReservationServiceImpl();
 private ClientRatingRepository ratingRepository = new ClientRatingRepositoryImpl();

    @Override
    public void createRatingForPerson(String username, double rating, Long reservationId) {
        Person person = userService.getPersonByUsername(username);
        Reservation reservation = reservationService.getById(reservationId);
        ClientRating clientRating = new ClientRating(person, rating, reservation);
        ratingRepository.save(clientRating);
        List<ClientRating> ratings = ratingRepository.getRatingsByPersonId(person.getId());
        userService.updatePersonRating(person, ratings);
    }

    @Override
    public String getRatingByReservationId(Long id) {
        double rating = 0;
        try {
            rating = ratingRepository.getRatingByReservationId(id);
        }
          catch(NoResultException ex){
            return "NOT RATED";
        }
        return String.valueOf(rating);
    }
}
