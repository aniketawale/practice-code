package com.lcwd.rating.services.impl;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.excpetion.ResourceNotFoundException;
import com.lcwd.rating.repositories.RatingRepository;
import com.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        /*String randomRatingID = UUID.randomUUID().toString();
        rating.setRatingId(randomRatingID);*/
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRating(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("Rating with given id is not found on server" + ratingId));
    }

    @Override
    public Rating deleteRating(String ratingId) {
        Optional<Rating> rating = ratingRepository.findById(ratingId);
        if (rating.isPresent()) {
            ratingRepository.deleteById(ratingId);
            return rating.get(); // Return the deleted rating
        } else {
            throw new ResourceNotFoundException("Rating with ID " + ratingId + " not found");
        }
    }


    @Override
    public Rating updateRating(String ratingId, Rating updatedRatingData) {
        Optional<Rating> existingRating = ratingRepository.findById(ratingId);

        if (existingRating.isPresent()) {
            Rating rating = existingRating.get();

            rating.setRatingId(updatedRatingData.getRatingId());
            rating.setHotelId(updatedRatingData.getHotelId());
            rating.setUserId(updatedRatingData.getUserId());
            rating.setFeedback(updatedRatingData.getFeedback());


            return ratingRepository.save(rating); // Save updated rating
        } else {
            throw new ResourceNotFoundException("Rating with ID " + ratingId + " not found");
        }
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return  ratingRepository.findByUserId(userId);

    }

    @Override
    public List<Rating> getRatingByHotel(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

}
