package com.library.ui;

import com.library.model.Admin;
import com.library.service.AuthService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LoginFrame
 * Admin login window
 */
public class LoginFrame extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private AuthService authService;

    public LoginFrame() {

        authService = new AuthService();

        setTitle("Library Management System - Login");
        setSize(400,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(3,2,10,10));

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        add(userLabel);
        add(usernameField);

        add(passLabel);
        add(passwordField);

        add(new JLabel());
        add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        Admin admin = authService.login(username, password);

        if(admin != null){

            JOptionPane.showMessageDialog(this,"Login Successful");

            DashboardFrame dashboard = new DashboardFrame();
            dashboard.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(this,"Invalid Credentials");
        }
    }
}
