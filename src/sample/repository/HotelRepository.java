package sample.repository;

import sample.models.hotels.Hotel;

public interface HotelRepository{
    Hotel getById(Long id);
}
