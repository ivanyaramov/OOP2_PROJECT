package sample.services;

import sample.models.DTOs.ReservationDTO;
import sample.models.reservations.Reservation;
import sample.models.viemModels.ReservationViewModel;

import java.util.List;

public interface ReservationService {
    void createReservation(ReservationDTO reservationDTO);
    List<ReservationViewModel> getReservationsForUser();
    void endReservationPrematurely(Long reservationId);
}
