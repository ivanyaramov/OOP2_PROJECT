package sample.services;

import sample.models.viemModels.RoomViewModel;

import java.util.List;

public interface RoomRatingService {
    void createRating(Long id, double rating);
}
