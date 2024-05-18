package com.example.proiect2.room;

import com.example.proiect2.hotel.Hotel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table
public class Room {
    @Id
    private int roomNumber;
    private int type;
    private int price;
    private String isAvailable;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    public Room() {
    }
    public Room(int roomNumber, int type, int price, String isAvailable,Hotel hotel) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
        this.hotel = hotel;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public int getType() {
        return type;
    }
    public int getPrice() {
        return price;
    }
    public String getIsAvailable() {
        return isAvailable;
    }
    public Hotel getHotel() {
        return hotel;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
