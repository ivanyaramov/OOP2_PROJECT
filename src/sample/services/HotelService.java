package sample.services;

import sample.models.DTOs.HotelDTO;
import sample.models.DTOs.RoomDTO;
import sample.models.hotels.Hotel;
import sample.models.hotels.Room;
import sample.models.viemModels.HotelViewModel;

import java.util.List;

public interface HotelService {
    void createHotel(HotelDTO hotelDTO, List<RoomDTO> rooms);
    Hotel getHotelById(Long id);
    List<HotelViewModel> getAllHotels();
    void addReceptionistToHotel(Long hotelId, List<String> receptionistUsernames);
}
