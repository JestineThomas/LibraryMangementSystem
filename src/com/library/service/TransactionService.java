package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.TransactionDAO;
import com.library.model.Book;
import com.library.model.Transaction;

import java.sql.Date;
import java.util.List;

/**
 * TransactionService
 * Handles issue/return logic
 */
public class TransactionService {

    private TransactionDAO transactionDAO = new TransactionDAO();
    private BookDAO bookDAO = new BookDAO();

    /**
     * Issue book
     */
    public boolean issueBook(Transaction transaction, Book book) {

        if (book.getAvailableQuantity() <= 0)
            return false;

        boolean issued = transactionDAO.issueBook(transaction);

        if (issued) {

            book.setAvailableQuantity(book.getAvailableQuantity() - 1);
            bookDAO.updateBook(book);
        }

        return issued;
    }

    /**
     * Return book
     */
    public boolean returnBook(int transactionId, Book book) {

        boolean returned = transactionDAO.returnBook(transactionId, new Date(System.currentTimeMillis()));

        if (returned) {

            book.setAvailableQuantity(book.getAvailableQuantity() + 1);
            bookDAO.updateBook(book);
        }

        return returned;
    }

    /**
     * Get all transactions
     */
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    /**
     * Get issued books
     */
    public List<Transaction> getIssuedBooks() {
        return transactionDAO.getIssuedBooks();
    }
}
