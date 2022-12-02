package sample.services.impl;

import org.modelmapper.ModelMapper;
import sample.currentLogin.CurrentLoggedUser;
import sample.models.DTOs.ReservationDTO;
import sample.models.reservations.Reservation;
import sample.repository.ReservationRepository;
import sample.repository.ReservationRepositoryImpl;
import sample.services.ReservationService;

public class ReservationServiceImpl implements ReservationService {
    private ModelMapper modelMapper = new ModelMapper();
    private ReservationRepository reservationRepository = new ReservationRepositoryImpl();
    @Override
    public void createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        reservation.setClient(CurrentLoggedUser.getLoggedUser());
        reservationRepository.save(reservation);
    }
}
