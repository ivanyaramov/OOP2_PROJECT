package sample.services;

import sample.models.DTOs.EntertainmentDTO;
import sample.models.entertainment.Entertainment;
import sample.models.viemModels.EntertainmentViewModel;

import java.util.List;

public interface EntertainmentService {
    void createEntertainment(EntertainmentDTO entertainmentDTO);
    List<EntertainmentViewModel> getEntertainmentsByReservationId(Long reservationId);
    List<EntertainmentViewModel> getAllEntertainments();
    Entertainment getEntertainmentById(Long id);

}
