package sample.repository;

import sample.models.hotels.Hotel;

import java.util.List;

public interface HotelRepository extends Repository{
    Hotel getById(Long id);
    List<Hotel> getAllHotels();
    List<Long> getIdsOfHotelsForOwner(Long id);
    Long getIdOfHotelsForManager(Long id);
    Long getIdOfHotelsForReceptionist(Long id);
}
