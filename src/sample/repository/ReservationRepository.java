package sample.repository;

import sample.models.reservations.Reservation;

import java.util.List;

public interface ReservationRepository extends Repository {
    List<Reservation> getReservationsByClientId(Long id);
    void updateReservation(Reservation reservation);
    Reservation getReservationById(Long id);
    List<Reservation> getReservationsByHotelIds(List<Long> ids);
}
