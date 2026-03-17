package com.library.service;

import com.library.dao.BookDAO;
import com.library.model.Book;

import java.util.List;

/**
 * BookService
 * Handles business logic for books
 */
public class BookService {

    private BookDAO bookDAO = new BookDAO();

    /**
     * Add new book
     */
    public boolean addBook(Book book) {

        if (book.getTitle() == null || book.getTitle().isEmpty())
            return false;

        if (book.getQuantity() <= 0)
            return false;

        book.setAvailableQuantity(book.getQuantity());

        return bookDAO.addBook(book);
    }

    /**
     * Update book
     */
    public boolean updateBook(Book book) {

        if (book.getId() <= 0)
            return false;

        return bookDAO.updateBook(book);
    }

    /**
     * Delete book
     */
    public boolean deleteBook(int id) {
        return bookDAO.deleteBook(id);
    }

    /**
     * Get all books
     */
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    /**
     * Search books
     */
    public List<Book> searchBooks(String keyword) {
        return bookDAO.searchBooks(keyword);
    }
}
