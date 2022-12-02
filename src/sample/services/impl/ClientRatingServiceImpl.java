package sample.services.impl;

import sample.models.people.Person;
import sample.models.ratings.ClientRating;
import sample.repository.ClientRatingRepository;
import sample.repository.ClientRatingRepositoryImpl;
import sample.repository.RatingRepository;
import sample.repository.RatingRepositoryImpl;
import sample.services.ClientRatingService;
import sample.services.UserService;

import java.util.List;

public class ClientRatingServiceImpl implements ClientRatingService {
 private UserService userService = new UserServiceImpl();
 private ClientRatingRepository ratingRepository = new ClientRatingRepositoryImpl();

    @Override
    public void createRatingForPerson(String username, double rating) {
        Person person = userService.getPersonByUsername(username);
        ClientRating clientRating = new ClientRating(person, rating);
        ratingRepository.save(clientRating);
        List<ClientRating> ratings = ratingRepository.getRatingsByPersonUsername(person.getId());
        userService.updatePersonRating(person, ratings);
    }
}
