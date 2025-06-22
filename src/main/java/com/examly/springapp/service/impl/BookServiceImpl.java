package com.examly.springapp.service.impl;

import com.examly.springapp.dao.BookDAO;
import com.examly.springapp.dao.impl.BookDAOImpl;
import com.examly.springapp.model.Book;
import com.examly.springapp.exception.LowPriceException;
import com.examly.springapp.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public void createBook(Book book) throws LowPriceException {
        if (book.getPrice() < 0) {
            throw new LowPriceException("Price cannot be negative");
        }
        bookDAO.createBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public void deleteBooksByAuthor(String author, int limit) {
        bookDAO.deleteBooksByAuthor(author, limit);
    }

    @Override
    public List<Book> getAllBooksByTitle() {
        return bookDAO.getAllBooksByTitle();
    }
}
