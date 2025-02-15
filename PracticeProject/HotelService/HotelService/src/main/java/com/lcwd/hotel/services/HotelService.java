package com.lcwd.hotel.services;

import com.lcwd.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {


    //Create
    Hotel create(Hotel hotel);
    //Read
    Hotel get(String Id);
    //ReadAll

    List<Hotel> getAll();

    //Delete
    Hotel deleteHotel(String Id);

    //Update
    Hotel updateHotel(String Id, Hotel updateHotelData);

}
