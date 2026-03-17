package com.library.ui;

import com.library.model.Transaction;
import com.library.service.TransactionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReturnBookPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JTextField transactionIdField;

    private TransactionService transactionService;
    private BookPanel bookPanel;

    public ReturnBookPanel(BookPanel bookPanel) {

        this.bookPanel = bookPanel;
        this.transactionService = new TransactionService();

        setLayout(new BorderLayout());

        // TOP PANEL
        JPanel topPanel = new JPanel(new FlowLayout());

        transactionIdField = new JTextField(10);
        JButton returnBtn = new JButton("Return Book");

        topPanel.add(new JLabel("Transaction ID:"));
        topPanel.add(transactionIdField);
        topPanel.add(returnBtn);

        add(topPanel, BorderLayout.NORTH);

        // TABLE
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "ID", "Student ID", "Book ID", "Issue Date"
        });

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 🔥 LOAD DATA
        refreshTable();

        // CLICK ROW → AUTO FILL
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int id = (int) model.getValueAt(row, 0);
                    transactionIdField.setText(String.valueOf(id));
                }
            }
        });

        returnBtn.addActionListener(e -> returnBook());
    }

    // 🔥 NEW METHOD
    public void refreshTable() {

        model.setRowCount(0);

        List<Transaction> list = transactionService.getIssuedTransactions();

        for (Transaction t : list) {
            model.addRow(new Object[]{
                    t.getId(),
                    t.getStudentId(),
                    t.getBookId(),
                    t.getIssueDate()
            });
        }

        model.fireTableDataChanged();
    }

    private void returnBook() {

        try {

            int transactionId = Integer.parseInt(transactionIdField.getText().trim());

            String result = transactionService.returnBook(transactionId);

            if ("SUCCESS".equals(result)) {

                JOptionPane.showMessageDialog(this, "Book Returned Successfully");

                // 🔥 FORCE REFRESH
                refreshTable();
                bookPanel.refreshTable();

                transactionIdField.setText("");

            } else {
                JOptionPane.showMessageDialog(this, result);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid transaction ID");
        }
    }
}