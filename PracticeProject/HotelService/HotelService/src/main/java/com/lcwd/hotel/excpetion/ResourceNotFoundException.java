package com.lcwd.hotel.excpetion;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource Not Found on Server");
    }

    public ResourceNotFoundException (String message){
        super(message);
    }
}
