package com.example.proiect2.hotel;

import com.example.proiect2.room.Room;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Hotel {
    @Id
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    @JsonManagedReference
    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms=new ArrayList<>();
    public Hotel() {
    }
    public Hotel(int id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public List<Room> getRooms() {
        return rooms;
    }
}
