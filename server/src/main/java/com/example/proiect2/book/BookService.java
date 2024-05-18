package com.example.proiect2.book;

import com.example.proiect2.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public void insertBook(Date date, int roomNumber){
        Book book = new Book(date,roomNumber);
        bookRepository.save(book);
    }
    public void deleteBook(int bookId){
        bookRepository.deleteById(bookId);
    }

}
