package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.excpetion.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.lcwd.user.service.config.*;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    HotelService hotelService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserID = UUID.randomUUID().toString();
        user.setUserID(randomUserID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    @Override
    public User getUser(String userId) {
        //get user from database with help of user repository
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server" + userId));
        //fetch ratings for above user from Rating Services
        //URL - http://localhost:8083/ratings/users/b8c63809-fe71-4cdb-aed0-52c04c8cff91
        String url = ("http://RATINGSERVICE/ratings/users/" + user.getUserID());
        Rating[] ratingsforUser= restTemplate.getForObject(url , Rating[].class);
        logger.info("{}",ratingsforUser);

        List<Rating> ratings = Arrays.stream(ratingsforUser).toList();


        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call for hotel service to get the hotel
            String urlH="http://HOTELSERVICE/hotels/";
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(urlH + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            // String urlH="http://localhost:8082/hotels/b6031139-b4fc-4a36-a5e4-d910eb7b4c41";
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(urlH + rating.getHotelId(), Hotel.class);

           // Hotel hotel = forEntity.getBody();
            logger.info("response status code:{}",forEntity.getStatusCode());

            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
            //return rating.getHotelId();
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }

    @Override
    public User deleteUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return user.get(); // Return the deleted user
        } else {
            throw new ResourceNotFoundException("User with ID " + userId + " not found");
        }
    }


    @Override
    public User updateUser(String userId, User updatedUserData) {
        Optional<User> existingUser = userRepository.findById(userId);

        if (existingUser.isPresent()) {
            User user = existingUser.get();

            user.setName(updatedUserData.getName());
            user.setEmail(updatedUserData.getEmail());
            user.setAbout(updatedUserData.getAbout());

            return userRepository.save(user); // Save updated user
        } else {
            throw new ResourceNotFoundException("User with ID " + userId + " not found");
        }
    }

}
