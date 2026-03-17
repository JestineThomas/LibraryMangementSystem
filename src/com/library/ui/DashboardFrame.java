package com.library.ui;

import javax.swing.*;

/**
 * DashboardFrame
 * Main application window
 */
public class DashboardFrame extends JFrame {
    private BookPanel bookPanel;
    public DashboardFrame(){

        setTitle("Library Management System - Dashboard");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        bookPanel = new BookPanel();

        tabbedPane.addTab("Books", bookPanel);
        tabbedPane.addTab("Students", new StudentPanel());
        tabbedPane.addTab("Issue Book", new IssueBookPanel(bookPanel));
        tabbedPane.addTab("Return Book", new ReturnBookPanel(bookPanel));
        add(tabbedPane);
    }
}
