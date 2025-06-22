package com.examly.springapp.dao;

import com.examly.springapp.model.Book;
import java.util.List;

public interface BookDAO {
    void createBook(Book book);
    void updateBook(Book book);
    void deleteBooksByAuthor(String author, int limit);
    List<Book> getAllBooksByTitle();
}
