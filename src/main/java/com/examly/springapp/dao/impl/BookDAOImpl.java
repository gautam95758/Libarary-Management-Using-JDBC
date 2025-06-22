package com.examly.springapp.dao.impl;

import com.examly.springapp.dao.BookDAO;
import com.examly.springapp.model.Book;
import com.examly.springapp.config.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    public BookDAOImpl() {
        createTableIfNotExists(); // Create the books table if it doesn't exist
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS books (" +
                "book_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "author TEXT NOT NULL, " +
                "price REAL NOT NULL, " +
                "available BOOLEAN DEFAULT TRUE" +
                ");";
        try (Connection conn = JDBCUtils.getConnection();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("✅ Table created or already exists.");
        } catch (Exception e) {
            System.err.println("❌ Failed to create table.");
            e.printStackTrace();

        }
    }

    @Override
    public void createBook(Book book) {
        String sql = "INSERT INTO books (book_id, title, author, price, available) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JDBCUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, book.getBookId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setFloat(4, book.getPrice());
            stmt.setBoolean(5, book.isAvailable());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, price = ?, available = ? WHERE book_id = ?";
        try (Connection conn = JDBCUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setFloat(3, book.getPrice());
            stmt.setBoolean(4, book.isAvailable());
            stmt.setInt(5, book.getBookId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBooksByAuthor(String author, int limit) {
        String sql = "DELETE FROM books WHERE author = ? LIMIT ?";
        try (Connection conn = JDBCUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, author);
            stmt.setInt(2, limit);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAllBooksByTitle() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY title";
        try (Connection conn = JDBCUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getFloat("price"),
                        rs.getBoolean("available")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
