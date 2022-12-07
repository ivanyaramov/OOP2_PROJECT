package sample.repository;

import sample.models.entertainment.Entertainment;

import java.util.List;

public interface EntertainmentRepository extends Repository{
    List<Entertainment> getEntertainmentsByReservationId(Long reservationId);
    List<Entertainment> getAllEntertainmentsByHotelId(Long hotelId);
    Entertainment getById(Long id);
}
