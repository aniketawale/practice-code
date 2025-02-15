package com.lcwd.rating;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RatingServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating(){
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("thisis").build();
		Rating savedrating= ratingService.createRating(rating);
		System.out.println("new Rating Created");
	}

}
