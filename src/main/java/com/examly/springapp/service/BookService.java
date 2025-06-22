package com.examly.springapp.service;

import com.examly.springapp.model.Book;
import com.examly.springapp.exception.LowPriceException;
import java.util.List;

public interface BookService {
    void createBook(Book book) throws LowPriceException;
    void updateBook(Book book);
    void deleteBooksByAuthor(String author, int limit);
    List<Book> getAllBooksByTitle();
}
