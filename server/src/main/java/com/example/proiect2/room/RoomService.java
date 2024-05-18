package com.example.proiect2.room;

import com.example.proiect2.hotel.Hotel;
import com.example.proiect2.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }
    public List<Room> getRooms(){
        return roomRepository.findAll();
    }
//    public void addRooms() throws IOException{
//        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(Arrays.asList(objectMapper.readValue(new File("C:\\Users\\Andrei Rosca\\Desktop\\proiect2\\src\\main\\java\\hotels.json"), Room[].class)));
//    }
}
