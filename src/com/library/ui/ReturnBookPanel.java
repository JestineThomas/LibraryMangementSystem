package com.library.ui;

import com.library.service.TransactionService;

import javax.swing.*;
import java.awt.*;

public class ReturnBookPanel extends JPanel {

    private JTextField transactionIdField;

    private TransactionService transactionService = new TransactionService();

    public ReturnBookPanel() {

        setLayout(new GridLayout(3,2,10,10));

        transactionIdField = new JTextField();

        add(new JLabel("Transaction ID"));
        add(transactionIdField);

        JButton returnBtn = new JButton("Return Book");

        add(new JLabel());
        add(returnBtn);

        returnBtn.addActionListener(e -> returnBook());
    }

    private void returnBook(){

        try {

            int transactionId = Integer.parseInt(transactionIdField.getText());

            transactionService.returnBook(transactionId);

            JOptionPane.showMessageDialog(this,"Book Returned Successfully");

        } catch (Exception e){

            JOptionPane.showMessageDialog(this,"Invalid transaction id");
        }
    }
}
