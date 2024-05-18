package com.example.proiect2.book;

import com.example.proiect2.hotel.Hotel;
import com.example.proiect2.hotel.HotelRoomInsertionService;
import com.example.proiect2.hotel.HotelService;
import com.example.proiect2.room.Room;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path="book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("/insert") // You can specify a more descriptive endpoint path
    public ResponseEntity<String> insertBook(@RequestParam Date date,
                                             @RequestParam int roomNumber) {
        bookService.insertBook(date, roomNumber);
        return ResponseEntity.ok("Book inserted successfully");
    }
    @DeleteMapping("/{hotelId}/delete")
    public void deleteBook(@PathVariable int hotelId) {
       bookService.deleteBook(hotelId);
    }
}
