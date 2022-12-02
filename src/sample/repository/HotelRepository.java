package sample.repository;

import sample.models.hotels.Hotel;

import java.util.List;

public interface HotelRepository extends Repository{
    Hotel getById(Long id);
    List<Hotel> getAllHotels();
}
