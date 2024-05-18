package com.example.proiect2.hotel;

import com.example.proiect2.room.Room;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/hotels")
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService, HotelRoomInsertionService hotelRoomInsertionService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> getHotels() {
        try {
            hotelService.addHotels();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hotelService.getHotels();
    }
    @GetMapping("/nearby")
    public List<Hotel> getNearbyHotels(@RequestParam double userLatitude,
                                                       @RequestParam double userLongitude,
                                                       @RequestParam double radius) {
        List<Hotel> nearbyHotels = hotelService.findHotelsWithinRadius(userLatitude, userLongitude, radius);
        return nearbyHotels;
    }
    @GetMapping("/{hotelId}/rooms")
    public List<Room> getRoomsForHotel(@PathVariable int hotelId) {
        return hotelService.getRoomsForHotel(hotelId);
    }
}
