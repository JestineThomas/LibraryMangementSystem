package com.library;

import com.library.ui.LoginFrame;

import javax.swing.*;

/**
 * Main Class
 * Entry point of the Library Management System
 */
public class Main {

    public static void main(String[] args) {

        // Set system look and feel (optional but recommended)
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Launch UI safely using EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });
    }
}
