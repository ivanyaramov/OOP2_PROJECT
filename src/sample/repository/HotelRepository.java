package sample.repository;

import sample.models.hotels.Hotel;

public interface HotelRepository extends Repository{
    Hotel getById(Long id);
}
