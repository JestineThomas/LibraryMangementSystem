package com.library.dao;

import com.library.config.DBConnection;
import com.library.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * AdminDAO
 * Handles admin authentication
 */
public class AdminDAO {

    /**
     * Validate admin login
     */
    public Admin login(String username, String password) {

        String sql = "SELECT * FROM admin WHERE username=? AND password=?";

        try {

            Connection conn = DBConnection.getInstance().getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));

                return admin;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
