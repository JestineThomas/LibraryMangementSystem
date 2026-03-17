package com.library.service;

import com.library.dao.StudentDAO;
import com.library.model.Student;

import java.util.List;

/**
 * StudentService
 * Handles student operations
 */
public class StudentService {

    private StudentDAO studentDAO = new StudentDAO();

    /**
     * Register student
     */
    public boolean registerStudent(Student student) {

        if (student.getName() == null || student.getName().isEmpty())
            return false;

        return studentDAO.addStudent(student);
    }

    /**
     * Update student
     */
    public boolean updateStudent(Student student) {

        if (student.getId() <= 0)
            return false;

        return studentDAO.updateStudent(student);
    }

    /**
     * Delete student
     */
    public boolean deleteStudent(int id) {
        return studentDAO.deleteStudent(id);
    }

    /**
     * Get all students
     */
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    /**
     * Get student by ID
     */
    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }
}
