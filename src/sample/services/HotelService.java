package sample.services;

import sample.models.DTOs.HotelDTO;
import sample.models.hotels.Hotel;
import sample.models.viemModels.HotelView;

import java.util.List;

public interface HotelService {
    void createHotel(HotelDTO hotelDTO);
    Hotel getHotelById(Long id);
    List<HotelView> getAllHotels();
}
