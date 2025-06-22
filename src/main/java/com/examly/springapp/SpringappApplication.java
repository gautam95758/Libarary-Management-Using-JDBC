package com.examly.springapp;

import com.examly.springapp.exception.LowPriceException;
import com.examly.springapp.model.Book;
import com.examly.springapp.service.BookService;
import com.examly.springapp.service.impl.BookServiceImpl;

import java.util.List;
import java.util.Scanner;

public class SpringappApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookServiceImpl();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Library Management System Menu ===");
            System.out.println("1. Add Book Details");
            System.out.println("2. Display All Books Ordered by Title");
            System.out.println("3. Update Book By ID");
            System.out.println("4. Delete a Book by Author");
            System.out.println("5. Exit the Application");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Book ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter Price: ");
                        float price = Float.parseFloat(scanner.nextLine());
                        System.out.print("Is Available (true/false): ");
                        boolean available = Boolean.parseBoolean(scanner.nextLine());

                        Book book = new Book(id, title, author, price, available);
                        bookService.createBook(book);
                        System.out.println("Book added successfully.");
                    } catch (LowPriceException lpe) {
                        System.out.println("Error: " + lpe.getMessage());
                    }
                    break;
                case 2:
                    List<Book> books = bookService.getAllBooksByTitle();
                    for (Book b : books) {
                        System.out.println("ID: " + b.getBookId() + ", Title: " + b.getTitle() + ", Author: " + b.getAuthor() + ", Price: " + b.getPrice() + ", Available: " + b.isAvailable());
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Enter Book ID to update: ");
                        int updateId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String newAuthor = scanner.nextLine();
                        System.out.print("Enter Price: ");
                        float newPrice = Float.parseFloat(scanner.nextLine());
                        System.out.print("Is Available (true/false): ");
                        boolean newAvailable = Boolean.parseBoolean(scanner.nextLine());

                        Book updatedBook = new Book(updateId, newTitle, newAuthor, newPrice, newAvailable);
                        bookService.updateBook(updatedBook);
                        System.out.println("Book updated successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter Author name to delete books: ");
                    String delAuthor = scanner.nextLine();
                    System.out.print("Enter limit: ");
                    int limit = Integer.parseInt(scanner.nextLine());
                    bookService.deleteBooksByAuthor(delAuthor, limit);
                    System.out.println("Books deleted.");
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
