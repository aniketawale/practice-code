package com.lcwd.hotel.controller;


import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels") // Base URL for all endpoints
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // Create Hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel createHotel = hotelService.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createHotel);
    }

    // Get Single Hotel
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId) {
        Hotel hotel = hotelService.get(hotelId);
        return ResponseEntity.ok(hotel);
    }

    // Get All Hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel() {
        List<Hotel> allHotels = hotelService.getAll();
        return ResponseEntity.ok(allHotels);
    }

    @PutMapping("/updatehotel/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel updatedHotel) {
        Hotel hotel = hotelService.updateHotel(hotelId, updatedHotel);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/deletehotel/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable String hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
