package com.example.proiect2.hotel;

import com.example.proiect2.room.Room;
import com.example.proiect2.room.RoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class HotelRoomInsertionService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public HotelRoomInsertionService(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    public void insertHotelsAndRoomsFromJson(String jsonFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Hotel> hotels = Arrays.asList(objectMapper.readValue(new File(jsonFilePath), Hotel[].class));
            for (Hotel hotel : hotels) {
                for (Room room : hotel.getRooms()) {
                    room.setHotel(hotel);
                    System.out.println(room);
                }
            }
            hotelRepository.saveAll(hotels);
        } catch (IOException e) {
            e.printStackTrace();
            // Log the error and handle it appropriately
        }
    }
}
