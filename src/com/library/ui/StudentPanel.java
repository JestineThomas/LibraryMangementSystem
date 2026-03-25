package com.library.ui;

import com.library.model.Student;
import com.library.service.StudentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * StudentPanel
 * Handles student management UI
 */
public class StudentPanel extends JPanel {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField deptField;

    private JTable table;
    private DefaultTableModel tableModel;

    private StudentService studentService;

    public StudentPanel() {

        studentService = new StudentService();

        setLayout(new BorderLayout());

        createTopPanel();
        createTable();

        loadStudents();
    }

    /**
     * Input Panel
     */
    private void createTopPanel() {

        JPanel panel = new JPanel(new GridLayout(3,4,5,5));

        nameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        deptField = new JTextField();

        JButton addBtn = new JButton("Register");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        panel.add(new JLabel("Name"));
        panel.add(new JLabel("Email"));
        panel.add(new JLabel("Phone"));
        panel.add(new JLabel("Department"));

        panel.add(nameField);
        panel.add(emailField);
        panel.add(phoneField);
        panel.add(deptField);

        panel.add(addBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);

        add(panel, BorderLayout.NORTH);

        addBtn.addActionListener(e -> addStudent());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
    }

    /**
     * JTable
     */
    private void createTable() {

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new String[]{
                "ID","Name","Email","Phone","Department"
        });

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Load students
     */
    private void loadStudents() {

        tableModel.setRowCount(0);

        List<Student> students = studentService.getAllStudents();

        for(Student s : students){

            tableModel.addRow(new Object[]{
                    s.getId(),
                    s.getName(),
                    s.getEmail(),
                    s.getPhone(),
                    s.getDepartment()
            });
        }
    }

    /**
     * Register student (FIXED)
     */
    private void addStudent(){

        try {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String dept = deptField.getText().trim();

            // ✅ VALIDATION
            if(name.isEmpty()){
                JOptionPane.showMessageDialog(this,"Name is required");
                return;
            }

            Student s = new Student();
            s.setName(name);
            s.setEmail(email);
            s.setPhone(phone);
            s.setDepartment(dept);

            boolean success = studentService.registerStudent(s);

            if(success){
                JOptionPane.showMessageDialog(this,"Student Registered Successfully");

                clearFields();   // 🔥 UX improvement
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this,"Error Adding Student");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Unexpected Error");
        }
    }

    /**
     * Update student
     */
    private void updateStudent(){

        try {
            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(this,"Select student");
                return;
            }

            String name = nameField.getText().trim();

            if(name.isEmpty()){
                JOptionPane.showMessageDialog(this,"Name is required");
                return;
            }

            int id = (int) tableModel.getValueAt(row,0);

            Student s = new Student();

            s.setId(id);
            s.setName(name);
            s.setEmail(emailField.getText().trim());
            s.setPhone(phoneField.getText().trim());
            s.setDepartment(deptField.getText().trim());

            boolean success = studentService.updateStudent(s);

            if(success){
                JOptionPane.showMessageDialog(this,"Student Updated");
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this,"Update Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error Updating Student");
        }
    }

    /**
     * Delete student
     */
    private void deleteStudent(){

        try {
            int row = table.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(this,"Select student");
                return;
            }

            int id = (int) tableModel.getValueAt(row,0);

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if(confirm != JOptionPane.YES_OPTION){
                return;
            }

            boolean success = studentService.deleteStudent(id);

            if(success){
                JOptionPane.showMessageDialog(this,"Student Deleted");
                loadStudents();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this,"Delete Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Error Deleting Student");
        }
    }

    /**
     * Clear input fields (NEW)
     */
    private void clearFields(){
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        deptField.setText("");
    }
}