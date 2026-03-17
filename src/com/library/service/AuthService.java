package com.library.service;

import com.library.dao.AdminDAO;
import com.library.model.Admin;

/**
 * AuthService
 * Handles authentication logic
 */
public class AuthService {

    private AdminDAO adminDAO = new AdminDAO();

    /**
     * Validate admin login
     */
    public Admin login(String username, String password) {

        if (username == null || username.isEmpty()) {
            return null;
        }

        if (password == null || password.isEmpty()) {
            return null;
        }

        return adminDAO.login(username, password);
    }
}
