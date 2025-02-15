package com.lcwd.hotel.services.impl;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.excpetion.ResourceNotFoundException;
import com.lcwd.hotel.repositories.HotelRepository;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel get(String Id) {
        return hotelRepository.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id is not found on server" + Id));
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel deleteHotel(String Id) {

        Optional<Hotel> hotel = hotelRepository.findById(Id);
        if (hotel.isPresent()) {
            hotelRepository.deleteById(Id);
            return hotel.get(); // Return the deleted user
        } else {
            throw new ResourceNotFoundException("Hotel with ID " + Id + " not found");
        }
    }

    @Override
    public Hotel updateHotel(String Id, Hotel updateHotelData) {
        Optional<Hotel> existingHotel = hotelRepository.findById(Id);

        if (existingHotel.isPresent()) {
            Hotel hotel = existingHotel.get();

            hotel.setName(updateHotelData.getName());
            hotel.setLocation(updateHotelData.getLocation());
            hotel.setAbout(updateHotelData.getAbout());

            return hotelRepository.save(hotel); // Save updated Hotel
        } else {
            throw new ResourceNotFoundException("Hotel with ID " + Id + " not found");
        }
    }
}

