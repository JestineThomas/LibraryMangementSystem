package com.library.ui;

import javax.swing.*;

/**
 * DashboardFrame
 * Main application window
 */
public class DashboardFrame extends JFrame {

    public DashboardFrame(){

        setTitle("Library Management System - Dashboard");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Books", new BookPanel());
        tabbedPane.addTab("Students", new StudentPanel());
        tabbedPane.addTab("Issue Book", new IssueBookPanel());
        tabbedPane.addTab("Return Book", new ReturnBookPanel());

        add(tabbedPane);
    }
}
