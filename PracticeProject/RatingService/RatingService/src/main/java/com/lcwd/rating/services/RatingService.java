package com.lcwd.rating.services;



import com.lcwd.rating.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating (Rating rating);
    List<Rating> getAllRating();
    Rating getRating(String ratingId);
    Rating deleteRating(String ratingId);
    Rating updateRating(String ratingId, Rating updatedRatingData);

    //get all by user
    List<Rating> getRatingByUserId(String userId);    //get all by Hotel

    List<Rating> getRatingByHotel(String hotelId);    //get all by Hotel

}
