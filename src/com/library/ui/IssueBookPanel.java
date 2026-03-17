package com.library.ui;

import com.library.model.Transaction;
import com.library.service.TransactionService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class IssueBookPanel extends JPanel {

    private JTextField studentIdField;
    private JTextField bookIdField;

    private TransactionService transactionService = new TransactionService();

    public IssueBookPanel() {

        setLayout(new GridLayout(4,2,10,10));

        studentIdField = new JTextField();
        bookIdField = new JTextField();

        add(new JLabel("Student ID"));
        add(studentIdField);

        add(new JLabel("Book ID"));
        add(bookIdField);

        JButton issueBtn = new JButton("Issue Book");

        add(new JLabel());
        add(issueBtn);

        issueBtn.addActionListener(e -> issueBook());
    }

    private void issueBook(){

        try {

            int studentId = Integer.parseInt(studentIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());

            Transaction t = new Transaction();

            t.setStudentId(studentId);
            t.setBookId(bookId);
            t.setIssueDate(LocalDate.now());

            transactionService.issueBook(t);

            JOptionPane.showMessageDialog(this,"Book Issued Successfully");

        } catch (Exception e){

            JOptionPane.showMessageDialog(this,"Invalid input");
        }
    }
}
