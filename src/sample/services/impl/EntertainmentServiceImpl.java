package sample.services.impl;

import org.modelmapper.ModelMapper;
import sample.models.DTOs.EntertainmentDTO;
import sample.models.entertainment.Entertainment;
import sample.models.viemModels.EntertainmentViewModel;
import sample.repository.EntertainmentRepository;
import sample.repository.EntertainmentRepositoryImpl;
import sample.services.EntertainmentService;

import java.util.List;
import java.util.stream.Collectors;

public class EntertainmentServiceImpl implements EntertainmentService {
    private ModelMapper modelMapper = new ModelMapper();
    private EntertainmentRepository entertainmentRepository = new EntertainmentRepositoryImpl();
    @Override
    public void createEntertainment(EntertainmentDTO entertainmentDTO) {
        Entertainment entertainment = modelMapper.map(entertainmentDTO, Entertainment.class);
        entertainmentRepository.save(entertainment);
    }

    public List<EntertainmentViewModel> getEntertainmentsByReservationId(Long reservationId){
       return entertainmentRepository.getEntertainmentsByReservationId(reservationId).stream().map(e->modelMapper.map(e, EntertainmentViewModel.class))
                .collect(Collectors.toList());
    }

    public List<EntertainmentViewModel> getAllEntertainments(){
        return entertainmentRepository.getAllEntertainments().stream().map(e->modelMapper.map(e, EntertainmentViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Entertainment getEntertainmentById(Long id) {
        return entertainmentRepository.getById(id);
    }
}
