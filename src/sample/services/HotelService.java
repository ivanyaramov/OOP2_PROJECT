package sample.services;

import sample.models.DTOs.HotelDTO;
import sample.models.hotels.Hotel;

import java.util.List;

public interface HotelService {
    void createHotel(HotelDTO hotelDTO);
    Hotel getHotelById(Long id);
    List<Hotel> getAllHotels();
}
