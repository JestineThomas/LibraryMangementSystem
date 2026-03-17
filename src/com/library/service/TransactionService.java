package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.StudentDAO;
import com.library.dao.TransactionDAO;
import com.library.model.Book;
import com.library.model.Student;
import com.library.model.Transaction;

import java.sql.Date;
import java.util.List;

public class TransactionService {

    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final BookDAO bookDAO = new BookDAO();
    private final StudentDAO studentDAO = new StudentDAO();

    // ISSUE BOOK
    // Returns: "SUCCESS" or a clear message
    public String issueBook(Transaction transaction) {

        try {
            // 1) Validate student
            Student student = studentDAO.getStudentById(transaction.getStudentId());
            if (student == null) {
                return "Student not found";
            }

            // 2) Validate book
            Book book = bookDAO.getBookById(transaction.getBookId());
            if (book == null) {
                return "Book not found";
            }

            // 3) Validate stock
            if (book.getAvailableQuantity() <= 0) {
                return "No stock available";
            }

            // 4) Insert transaction (status should be 'ISSUED' by DAO)
            boolean issued = transactionDAO.issueBook(transaction);

            if (issued) {
                // 5) Decrease available (never below 0)
                int newAvail = Math.max(0, book.getAvailableQuantity() - 1);
                book.setAvailableQuantity(newAvail);
                bookDAO.updateBook(book);

                return "SUCCESS";
            }

            return "Issue failed";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred";
        }
    }

    // RETURN BOOK
    // Returns: "SUCCESS" or a clear message
    public String returnBook(int transactionId) {

        try {
            // 1) Fetch transaction
            Transaction t = transactionDAO.getTransactionById(transactionId);
            if (t == null) {
                return "Invalid transaction ID";
            }

            // 2) Prevent multiple returns
            if ("RETURNED".equalsIgnoreCase(t.getStatus())) {
                return "Already returned";
            }

            // 3) Mark returned in DB
            boolean returned = transactionDAO.returnBook(
                    transactionId,
                    new Date(System.currentTimeMillis())
            );

            if (returned) {
                // 4) Increase available but NEVER exceed total quantity
                Book book = bookDAO.getBookById(t.getBookId());
                if (book != null) {
                    int newAvail = Math.min(
                            book.getQuantity(),
                            book.getAvailableQuantity() + 1
                    );
                    book.setAvailableQuantity(newAvail);
                    bookDAO.updateBook(book);
                }
                return "SUCCESS";
            }

            return "Return failed";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred";
        }
    }

    // Only ISSUED (not returned) → used by Return UI to avoid confusion
    public List<Transaction> getIssuedTransactions() {
        return transactionDAO.getIssuedTransactions();
    }

    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }
}