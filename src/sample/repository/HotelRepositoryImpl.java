package sample.repository;

import sample.DBService.DatabaseService;
import sample.models.hotels.Hotel;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class HotelRepositoryImpl extends RepositoryImpl implements HotelRepository{
private DatabaseService databaseService = new DatabaseService();

    @Override
    public Hotel getById(Long id) {
        String hql = "FROM Hotel h WHERE h.id = " + id;
        return (Hotel) databaseService.getObjectByQuery(hql);
    }

    @Override
    public List<Hotel> getAllHotels() {
        String hql = "FROM Hotel h";
        return (List<Hotel>) databaseService.getListOfObjectsByQuery(hql);
    }

    @Override
    public List<Long> getIdsOfHotelsForOwner(Long id) {
        String hql = "FROM Hotel WHERE owner_id = " + id;
        List<Hotel> hotels = (List<Hotel>) databaseService.getListOfObjectsByQuery(hql);
        return hotels.stream().map(h->h.getId()).collect(Collectors.toList());
    }

    @Override
    public Long getIdOfHotelsForManager(Long id) {
        String hql = "FROM Hotel WHERE manager_id = " + id;
        Hotel hotel = (Hotel) databaseService.getObjectByQuery(hql);
        return hotel.getId();
    }

    @Override
    public Long getIdOfHotelsForReceptionist(Long id) {
        String hql = "SELECT h FROM Hotel h JOIN h.receptionists WHERE receptionists_id = " + id;
        Hotel hotel = (Hotel) databaseService.getObjectByQuery(hql);
        return hotel.getId();
    }

    @Override
    public boolean managerHasHotels(Long id) {
        String hql = "FROM Hotel WHERE manager_id = " + id;
        Hotel hotel = null;
        try {
            hotel = (Hotel) databaseService.getObjectByQuery(hql);
        }
        catch(NoResultException ex){
            return false;
        }
        return hotel != null;
    }

    @Override
    public boolean recptionistHasHotels(Long id) {
        String hql = "SELECT h FROM Hotel h JOIN h.receptionists WHERE receptionists_id = " + id;
        Hotel hotel = null;
        try {
            hotel = (Hotel) databaseService.getObjectByQuery(hql);
        }
        catch(NoResultException ex){
            return false;
        }
        return hotel != null;
    }

    @Override
    public Hotel getHotelByName(String name) {
        String hql = "FROM Hotel h WHERE h.name = '" + name + "'";
        return (Hotel) databaseService.getObjectByQuery(hql);
    }
}
