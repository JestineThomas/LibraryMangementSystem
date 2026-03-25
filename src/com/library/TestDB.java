package com.library;

import com.library.config.DBConnection;

public class TestDB {

    public static void main(String[] args) {

        try {
            DBConnection.getInstance().getConnection();
            System.out.println("Connection Test Passed ✅");
        } catch (Exception e) {
            System.out.println("Connection Failed ❌");
            e.printStackTrace();
        }
    }
}