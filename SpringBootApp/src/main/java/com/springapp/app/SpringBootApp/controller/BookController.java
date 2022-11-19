package com.springapp.app.SpringBootApp.controller;

import com.springapp.app.SpringBootApp.entity.Book;
import com.springapp.app.SpringBootApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> book =  this.bookService.getAllBooks();
        if(book.size()<=0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book book = this.bookService.getBookById(id);
        if(book == null) {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return this.bookService.addBook(book);
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBooks(@PathVariable("bookId") int bookId) {
        this.bookService.deleteBookById(bookId);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBooks(@RequestBody Book book, @PathVariable("bookId") int bookId) {
        this.bookService.updateBooks(book, bookId);
        return book;
    }
}
