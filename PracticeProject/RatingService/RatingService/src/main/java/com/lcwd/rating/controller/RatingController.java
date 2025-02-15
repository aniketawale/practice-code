package com.lcwd.rating.controller;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings") // Base URL for all endpoints
public class RatingController {

    @Autowired
    private RatingService ratingService;

    // Create Rating
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating savedRating = ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRating);
    }

    // Get Single Rating
    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRating(@PathVariable String ratingId) {
        Rating rating = ratingService.getRating(ratingId);
        return ResponseEntity.ok(rating);
    }

    // Get All Ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> allRatings = ratingService.getAllRating();
        return ResponseEntity.ok(allRatings);
    }

    // Get Ratings by User ID
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUser(@PathVariable String userId) {
        List<Rating> userRatings = ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok(userRatings);
    }

    // Get Ratings by Hotel ID
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotel(@PathVariable String hotelId) {
        List<Rating> hotelRatings = ratingService.getRatingByHotel(hotelId);
        return ResponseEntity.ok(hotelRatings);
    }

    // Update Rating
    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, @RequestBody Rating updatedRating) {
        Rating rating = ratingService.updateRating(ratingId, updatedRating);
        return ResponseEntity.ok(rating);
    }

    // Delete Rating
    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable String ratingId) {
        ratingService.deleteRating(ratingId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
