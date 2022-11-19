package com.springapp.app.SpringBootApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.springapp.app.SpringBootApp.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private static List<Book> list = new ArrayList<>();

    static {
//        list.add(new Book(12,"Java1","Prateek1"));
//        list.add(new Book(13,"Java2","Prateek2"));
//        list.add(new Book(14,"Java3","Prateek3"));
    }

    public List<Book> getAllBooks() {
        return list;
    }

    public Book getBookById(int id) {
        Book book = null;
        //return a single book by filtering based on ids and using lambda function.
        book = list.stream().filter(e -> e.getId() == id).findFirst().get();
        return book;
    }

    public Book addBook(Book book) {
        list.add(book);
        return book;
    }

    public void deleteBookById(int bookId) {
        list = list.stream().filter(book -> {
            if(book.getId()!=bookId) {
                return true;
            }
            else {
                return false;
            }
        }).collect(Collectors.toList());

    }

    public void updateBooks(Book book, int bookId) {
       list.stream().map(b-> {
           if(b.getId()==bookId) {
               b.setName(book.getName());
               b.setAuthor(book.getAuthor());
           }
           return b;
       }).collect(Collectors.toList());
    }
}
