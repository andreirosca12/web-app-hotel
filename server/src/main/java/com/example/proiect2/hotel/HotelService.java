package com.example.proiect2.hotel;

import com.example.proiect2.room.Room;
import com.example.proiect2.room.RoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }
    public List<Hotel> getHotels(){
        return hotelRepository.findAll();
    }
    public void addHotels() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        hotelRepository.saveAll(Arrays.asList(objectMapper.readValue(new File("C:\\Users\\Andrei Rosca\\Desktop\\proiect2\\src\\main\\java\\hotels.json"), Hotel[].class)));
    }
    double deg2rad(double deg) {
        return deg * Math.PI / 180;
    }
    public List<Hotel> findHotelsWithinRadius(double userLatitude, double userLongitude, double radius){
        List<Hotel> hotels = hotelRepository.findAll();
        Iterator<Hotel> iterator = hotels.iterator();
        while (iterator.hasNext()) {
            Hotel hotel = iterator.next();
            double R = 6371;
            double dLat = deg2rad(userLatitude-hotel.getLatitude());
            double dLon = deg2rad(userLongitude-hotel.getLongitude());
            var a =
                    Math.sin(dLat/2) * Math.sin(dLat/2) +
                            Math.cos(deg2rad(userLatitude)) * Math.cos(deg2rad(hotel.getLatitude())) *
                                    Math.sin(dLon/2) * Math.sin(dLon/2)
                    ;
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            double distance = R * c;
            if (distance > radius) {
                iterator.remove();
            }
            System.out.println(distance + " " + radius);
        }
        return hotels;
    }

    public List<Room> getRoomsForHotel(int hotelId) {
            Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
            if (hotelOptional.isPresent()) {
                return hotelOptional.get().getRooms();
            } else {
                return new ArrayList<>();
            }
    }
}
