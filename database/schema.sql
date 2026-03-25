CREATE DATABASE library_db;

USE library_db;

-- =========================
-- Admin Table
-- =========================
CREATE TABLE admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

INSERT INTO admin(username,password)
VALUES ('admin','admin123');


-- =========================
-- Books Table
-- =========================
CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(150) NOT NULL,
    isbn VARCHAR(50),
    category VARCHAR(100),
    quantity INT NOT NULL,
    available_quantity INT NOT NULL
);


-- =========================
-- Students Table
-- =========================
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150),
    phone VARCHAR(20),
    department VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- =========================
-- Transactions Table
-- =========================
CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,

    student_id INT,
    book_id INT,

    issue_date DATE,
    return_date DATE,

    status VARCHAR(20) DEFAULT 'ISSUED',

    FOREIGN KEY (student_id) REFERENCES students(id)
        ON DELETE CASCADE,

    FOREIGN KEY (book_id) REFERENCES books(id)
        ON DELETE CASCADE
);
