package com.library.ui;

import com.library.model.Transaction;
import com.library.service.TransactionService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class IssueBookPanel extends JPanel {

    private JTextField studentIdField;
    private JTextField bookIdField;

    private TransactionService transactionService;
    private BookPanel bookPanel;

    public IssueBookPanel(BookPanel bookPanel) {

        this.bookPanel = bookPanel;
        this.transactionService = new TransactionService();

        setLayout(new GridLayout(4, 2, 10, 10));

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

    private void issueBook() {

        try {

            int studentId = Integer.parseInt(studentIdField.getText().trim());
            int bookId = Integer.parseInt(bookIdField.getText().trim());

            Transaction t = new Transaction();
            t.setStudentId(studentId);
            t.setBookId(bookId);
            t.setIssueDate(java.sql.Date.valueOf(LocalDate.now()));

            String result = transactionService.issueBook(t);

            if ("SUCCESS".equals(result)) {
                JOptionPane.showMessageDialog(this, "Book Issued Successfully");

                // 🔥 REAL-TIME UPDATE
                bookPanel.refreshTable();

            } else {
                JOptionPane.showMessageDialog(this, result);
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this, "Enter valid numeric IDs");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Error occurred");
        }
    }
}