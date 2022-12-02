package sample.services;

import sample.models.DTOs.ReservationDTO;

public interface ReservationService {
    void createReservation(ReservationDTO reservationDTO);
}
