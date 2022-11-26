package sample.services;

import sample.models.DTOs.HotelDTO;
import sample.models.hotels.Hotel;

public interface HotelService {
    void createHotel(HotelDTO hotelDTO);
    Hotel getHotelById(Long id);
}
