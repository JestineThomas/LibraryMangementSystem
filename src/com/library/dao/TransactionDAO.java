package com.library.dao;

import com.library.config.DBConnection;
import com.library.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // ISSUE BOOK
    public boolean issueBook(Transaction t) {

        String sql = "INSERT INTO transactions (student_id, book_id, issue_date, status) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, t.getStudentId());
            stmt.setInt(2, t.getBookId());
            stmt.setDate(3, t.getIssueDate());
            stmt.setString(4, "ISSUED");

            int rows = stmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // RETURN BOOK
    public boolean returnBook(int transactionId, java.sql.Date returnDate) {

        String sql = "UPDATE transactions SET return_date = ?, status = 'RETURNED' WHERE id = ?";

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDate(1, returnDate);
            stmt.setInt(2, transactionId);

            int rows = stmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // GET SINGLE TRANSACTION
    public Transaction getTransactionById(int id) {

        String sql = "SELECT * FROM transactions WHERE id = ?";

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Transaction t = new Transaction();

                t.setId(rs.getInt("id"));
                t.setStudentId(rs.getInt("student_id"));
                t.setBookId(rs.getInt("book_id"));
                t.setIssueDate(rs.getDate("issue_date"));
                t.setReturnDate(rs.getDate("return_date"));
                t.setStatus(rs.getString("status"));

                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // GET ONLY ISSUED TRANSACTIONS (FOR RETURN PANEL)
    public List<Transaction> getIssuedTransactions() {

        List<Transaction> list = new ArrayList<>();

        String sql = "SELECT * FROM transactions WHERE status = 'ISSUED'";

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

    // GET ALL TRANSACTIONS (OPTIONAL - FOR FUTURE HISTORY VIEW)
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
}