package sample.services.impl;

import org.modelmapper.ModelMapper;
import sample.currentLogin.CurrentLoggedUser;
import sample.logger.Logger;
import sample.models.DTOs.ReservationDTO;
import sample.models.entertainment.Entertainment;
import sample.models.hotels.Hotel;
import sample.models.hotels.Room;
import sample.models.people.Person;
import sample.models.reservations.Reservation;
import sample.models.viemModels.ReservationViewModel;
import sample.repository.HotelRepository;
import sample.repository.HotelRepositoryImpl;
import sample.repository.ReservationRepository;
import sample.repository.ReservationRepositoryImpl;
import sample.services.EntertainmentService;
import sample.services.ReservationService;
import sample.services.RoomService;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationServiceImpl implements ReservationService {
    private ModelMapper modelMapper = new ModelMapper();
    private ReservationRepository reservationRepository = new ReservationRepositoryImpl();
    private HotelRepository hotelRepository = new HotelRepositoryImpl();
    private RoomService roomService = new RoomServiceImpl();
    private EntertainmentService entertainmentService = new EntertainmentServiceImpl();

    @Override
    public void createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
        reservation.setClient(CurrentLoggedUser.getLoggedUser());
        Hotel hotel = hotelRepository.getById(reservationDTO.getHotel());
        Room room = roomService.getRoomByRoomId(reservationDTO.getRoom());
        reservation.setRoom(room);
        reservation.setHotel(hotel);
        for(Long id: reservationDTO.getEntertainmentIds()){
            Entertainment entertainment = entertainmentService.getEntertainmentById(id);
            reservation.getEntertainments().add(entertainment);
        }
        reservationRepository.save(reservation);
        Logger.log("Reservation created");
        roomService.updateRoomIsTaken(room, true);
    }

    @Override
    public List<ReservationViewModel> getReservationsForUser() {
        Person currentUser = CurrentLoggedUser.getLoggedUser();
        List<Reservation> reservations;
        try {
            switch (currentUser.getRole()) {
                case CLIENT:
                    reservations = reservationRepository.getReservationsByClientId(currentUser.getId());
                    break;
                case MANAGER:
                    reservations = reservationRepository.getReservationsByHotelIds(Arrays.asList(hotelRepository.getIdOfHotelsForManager(currentUser.getId())));
                    break;
                case RECEPTIONIST:
                    reservations = reservationRepository.getReservationsByHotelIds(Arrays.asList(hotelRepository.getIdOfHotelsForReceptionist(currentUser.getId())));
                    break;
                case OWNER:
                    reservations = reservationRepository.getReservationsByHotelIds(hotelRepository.getIdsOfHotelsForOwner(currentUser.getId()));
                    break;
                default:
                    return null;
            }
        }
        catch (Exception e){
            return new ArrayList<>();
        }
        return reservations
                .stream().map(this::setReservationViewModel).collect(Collectors.toList());

    }

    private ReservationViewModel setReservationViewModel(Reservation r){
        ReservationViewModel model = modelMapper.map(r, ReservationViewModel.class);
        model.setHotelName(r.getHotel().getName());
        model.setRoomCategory(r.getRoomCategory());
        model.setRoomId(r.getRoom().getId());
        updateEnded(r);
        model.setEnded(r.isEnded());
        model.setNearlyEnded(isNearlyEnded(r));
        double price = r.getDays() * r.getRoom().getPricePerNight();
        for(Entertainment e : r.getEntertainments()){
            price += e.getPrice();
        }
        model.setPrice(price);
        model.setRisky(r.getClient().getRating() <= 5);
        return model;
    }

    @Override
    public void endReservationPrematurely(Long reservationId) {
        Reservation reservation = reservationRepository.getReservationById(reservationId);
        reservation.setEnded(true);
        reservationRepository.updateReservation(reservation);
        roomService.updateRoomIsTaken(reservation.getRoom(), false);
    }

    @Override
    public Reservation getById(Long id) {
       return reservationRepository.getReservationById(id);
    }

    private void updateEnded(Reservation reservation){
        if(!reservation.isEnded()) {
            Calendar c = Calendar.getInstance();
            c.setTime(reservation.getDateOfArrival());
            c.add(Calendar.DATE, reservation.getDays());
            reservation.setEnded(new Date().after(c.getTime()));
            if(reservation.isEnded()) {
                reservationRepository.updateReservation(reservation);
                roomService.updateRoomIsTaken(reservation.getRoom(), false);
            }
        }
    }
    private boolean isNearlyEnded(Reservation reservation){
        Calendar c = Calendar.getInstance();
        c.setTime(reservation.getDateOfArrival());
        c.add(Calendar.DATE, reservation.getDays() - 1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        c2.get(Calendar.DAY_OF_MONTH);
        return c2.get(Calendar.DAY_OF_MONTH) ==  c.get(Calendar.DAY_OF_MONTH);
    }



}
