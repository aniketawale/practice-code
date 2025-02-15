package com.lcwd.user.service.external.services;

import com.lcwd.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="RATINGSERVICVE")
public interface RatingService {
    //get
    //post
 @PostMapping("/ratings")
    public Rating createRating(Rating values);

 @PutMapping("/ratings/{ratingId}")
    public Rating updaterating(@PathVariable("ratingId") String ratingId, Rating rating);
    //update
   @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);
}
