package com.library.ui;

import com.library.model.Book;
import com.library.service.BookService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * BookPanel
 * Handles book management UI
 */
public class BookPanel extends JPanel {

    private JTextField titleField;
    private JTextField authorField;
    private JTextField isbnField;
    private JTextField categoryField;
    private JTextField quantityField;
    private JTextField searchField;

    private JTable table;
    private DefaultTableModel tableModel;

    private BookService bookService;

    public BookPanel() {

        bookService = new BookService();

        setLayout(new BorderLayout());

        createTopPanel();
        createTable();

        loadBooks();
    }

    /**
     * Top input panel
     */
    private void createTopPanel() {

        JPanel panel = new JPanel(new GridLayout(3,6,5,5));

        titleField = new JTextField();
        authorField = new JTextField();
        isbnField = new JTextField();
        categoryField = new JTextField();
        quantityField = new JTextField();
        searchField = new JTextField();

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");

        panel.add(new JLabel("Title"));
        panel.add(new JLabel("Author"));
        panel.add(new JLabel("ISBN"));
        panel.add(new JLabel("Category"));
        panel.add(new JLabel("Quantity"));
        panel.add(new JLabel(""));

        panel.add(titleField);
        panel.add(authorField);
        panel.add(isbnField);
        panel.add(categoryField);
        panel.add(quantityField);
        panel.add(addBtn);

        panel.add(updateBtn);
        panel.add(deleteBtn);
        panel.add(searchField);
        panel.add(searchBtn);

        add(panel, BorderLayout.NORTH);

        // Button events

        addBtn.addActionListener(e -> addBook());

        updateBtn.addActionListener(e -> updateBook());

        deleteBtn.addActionListener(e -> deleteBook());

        searchBtn.addActionListener(e -> searchBooks());
    }

    /**
     * JTable
     */
    private void createTable() {

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new String[]{
                "ID","Title","Author","ISBN","Category","Quantity","Available"
        });

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Load books from database
     */
    private void loadBooks() {

        tableModel.setRowCount(0);

        List<Book> books = bookService.getAllBooks();

        for(Book b : books){

            tableModel.addRow(new Object[]{
                    b.getId(),
                    b.getTitle(),
                    b.getAuthor(),
                    b.getIsbn(),
                    b.getCategory(),
                    b.getQuantity(),
                    b.getAvailableQuantity()
            });
        }
    }

    /**
     * Add book
     */
    private void addBook(){

        Book book = new Book();

        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setIsbn(isbnField.getText());
        book.setCategory(categoryField.getText());
        book.setQuantity(Integer.parseInt(quantityField.getText()));

        boolean success = bookService.addBook(book);

        if(success){
            JOptionPane.showMessageDialog(this,"Book Added");
            loadBooks();
        } else {
            JOptionPane.showMessageDialog(this,"Error Adding Book");
        }
    }

    /**
     * Update book
     */
    private void updateBook(){

        int row = table.getSelectedRow();

        if(row == -1){
            JOptionPane.showMessageDialog(this,"Select a book");
            return;
        }

        int id = (int) tableModel.getValueAt(row,0);

        Book book = new Book();

        book.setId(id);
        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setIsbn(isbnField.getText());
        book.setCategory(categoryField.getText());
        book.setQuantity(Integer.parseInt(quantityField.getText()));
        book.setAvailableQuantity(Integer.parseInt(quantityField.getText()));

        boolean success = bookService.updateBook(book);

        if(success){
            JOptionPane.showMessageDialog(this,"Book Updated");
            loadBooks();
        }
    }

    /**
     * Delete book
     */
    private void deleteBook(){

        int row = table.getSelectedRow();

        if(row == -1){
            JOptionPane.showMessageDialog(this,"Select a book");
            return;
        }

        int id = (int) tableModel.getValueAt(row,0);

        boolean success = bookService.deleteBook(id);

        if(success){
            JOptionPane.showMessageDialog(this,"Book Deleted");
            loadBooks();
        }
    }

    /**
     * Search books
     */
    private void searchBooks(){

        String keyword = searchField.getText();

        tableModel.setRowCount(0);

        List<Book> books = bookService.searchBooks(keyword);

        for(Book b : books){

            tableModel.addRow(new Object[]{
                    b.getId(),
                    b.getTitle(),
                    b.getAuthor(),
                    b.getIsbn(),
                    b.getCategory(),
                    b.getQuantity(),
                    b.getAvailableQuantity()
            });
        }
    }
    public void refreshTable() {
        loadBooks();
    }
}
