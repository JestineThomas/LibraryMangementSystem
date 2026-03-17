package com.library.dao;

import com.library.config.DBConnection;
import com.library.model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TransactionDAO
 * Handles book issue and return operations
 */
public class TransactionDAO {

    /**
     * Issue book
     */
    public boolean issueBook(Transaction transaction) {

        String sql = "INSERT INTO transactions(student_id, book_id, issue_date, status) VALUES(?,?,?,?)";

        try {

            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, transaction.getStudentId());
            stmt.setInt(2, transaction.getBookId());
            stmt.setDate(3, transaction.getIssueDate());
            stmt.setString(4, "ISSUED");

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Return book
     */
    public boolean returnBook(int transactionId, Date returnDate) {

        String sql = "UPDATE transactions SET return_date=?, status='RETURNED' WHERE id=?";

        try {

            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDate(1, returnDate);
            stmt.setInt(2, transactionId);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get all transactions
     */
    public List<Transaction> getAllTransactions() {

        List<Transaction> list = new ArrayList<>();

        String sql = "SELECT * FROM transactions";

        try {

            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Transaction t = new Transaction();

                t.setId(rs.getInt("id"));
                t.setStudentId(rs.getInt("student_id"));
                t.setBookId(rs.getInt("book_id"));
                t.setIssueDate(rs.getDate("issue_date"));
                t.setReturnDate(rs.getDate("return_date"));
                t.setStatus(rs.getString("status"));

                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Get issued books
     */
    public List<Transaction> getIssuedBooks() {

        List<Transaction> list = new ArrayList<>();

        String sql = "SELECT * FROM transactions WHERE status='ISSUED'";

        try {

            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Transaction t = new Transaction();

                t.setId(rs.getInt("id"));
                t.setStudentId(rs.getInt("student_id"));
                t.setBookId(rs.getInt("book_id"));
                t.setIssueDate(rs.getDate("issue_date"));
                t.setReturnDate(rs.getDate("return_date"));
                t.setStatus(rs.getString("status"));

                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
