package sample.services.impl;

import org.modelmapper.ModelMapper;
import sample.logger.Logger;
import sample.models.DTOs.EntertainmentDTO;
import sample.models.entertainment.Entertainment;
import sample.models.hotels.Hotel;
import sample.models.viemModels.EntertainmentViewModel;
import sample.repository.EntertainmentRepository;
import sample.repository.EntertainmentRepositoryImpl;
import sample.services.EntertainmentService;
import sample.services.HotelService;

import java.util.List;
import java.util.stream.Collectors;

public class EntertainmentServiceImpl implements EntertainmentService {
    private ModelMapper modelMapper = new ModelMapper();
    private EntertainmentRepository entertainmentRepository = new EntertainmentRepositoryImpl();
    private HotelService hotelService = new HotelServiceImpl();
    @Override
    public void createEntertainment(EntertainmentDTO entertainmentDTO) {
        Entertainment entertainment = modelMapper.map(entertainmentDTO, Entertainment.class);
        Hotel hotel = hotelService.getHotelById(entertainmentDTO.getHotelId());
        entertainment.setHotel(hotel);
        entertainmentRepository.save(entertainment);
        Logger.log("Entertainment " + entertainment.getName() + " created");
    }

    public List<EntertainmentViewModel> getEntertainmentsByReservationId(Long reservationId){
       return entertainmentRepository.getEntertainmentsByReservationId(reservationId).stream().map(e->modelMapper.map(e, EntertainmentViewModel.class))
                .collect(Collectors.toList());
    }

    public List<EntertainmentViewModel> getAllEntertainmentsByHotelId(Long hotelId){
        return entertainmentRepository.getAllEntertainmentsByHotelId(hotelId).stream().map(e->modelMapper.map(e, EntertainmentViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Entertainment getEntertainmentById(Long id) {
        return entertainmentRepository.getById(id);
    }
}
