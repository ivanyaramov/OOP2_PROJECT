package sample.repository;

import sample.models.entertainment.Entertainment;

import java.util.List;

public interface EntertainmentRepository extends Repository{
    List<Entertainment> getEntertainmentsByReservationId(Long reservationId);
    List<Entertainment> getAllEntertainments();
    Entertainment getById(Long id);
}
