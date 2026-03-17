package com.library.model;

import java.sql.Date;

/**
 * Transaction Model
 * Represents transactions table
 */
public class Transaction {

    private int id;
    private int studentId;
    private int bookId;

    private Date issueDate;
    private Date returnDate;

    private String status;

    public Transaction() {
    }

    public Transaction(int id, int studentId, int bookId,
                       Date issueDate, Date returnDate, String status) {

        this.id = id;
        this.studentId = studentId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }


    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
