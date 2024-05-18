package com.example.proiect2.book;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table
public class Book {
    @Id
    private int bookId;
    private int roomNumber;
    private Date date;
    public Book() {
    }
    public Book(int bookId, int roomNumber, Date date) {
        this.bookId = bookId;
        this.roomNumber = roomNumber;
        this.date = date;
    }
    public Book( Date date,int roomNumber) {
        this.roomNumber = roomNumber;
        this.date = date;
    }
    public int getBookId() {
        return bookId;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public Date getDate() {
        return date;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
