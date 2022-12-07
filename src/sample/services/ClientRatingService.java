package sample.services;

public interface ClientRatingService {
    void createRatingForPerson(String username, double rating, Long reservationId);
    String getRatingByReservationId(Long id);
}
