/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.librarymanagement;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author phiquangdat
 */
public class LibraryManagement {
    private List<LibraryItem> libraryItems;

    public LibraryManagement() {
        libraryItems = new ArrayList<>();
        addLibraryItem(new Book("book001", "Introduction to Java"));
        addLibraryItem(new Magazine("magazine001", "New York Times"));

        //
        this.GUI();
    }

    public void addLibraryItem(LibraryItem item) {
        libraryItems.add(item);
    }

    public void removeLibraryItem(LibraryItem item) {
        libraryItems.remove(item);
    }

    private void GUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> itemList = new JList<>(listModel);
        refreshList(listModel);
        
        JLabel label = new JLabel("List of Library Items: ");
        label.setBounds(50,50, 150,20);  
        
        JButton borrowButton = new JButton("Borrow Item");
        borrowButton.addActionListener(e -> handleBorrowAction(frame, itemList, listModel));

        JButton returnButton = new JButton("Return Item");
        returnButton.addActionListener(e -> handleReturnAction(frame, itemList, listModel));
        
        JButton addButton = new JButton("Add New Item");
        addButton.addActionListener(e -> handleAddAction(frame, listModel));
        
        JButton deleteButton = new JButton("Delete Item");
        deleteButton.addActionListener(e -> handleDeleteAction(frame, itemList,listModel));
        
        //A section for buttons
        JPanel panel = new JPanel(new GridLayout(1, 4));
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(borrowButton);
        panel.add(returnButton);
        
        //A label on top, A list of items in the middle, and buttons section at bottom
        frame.add(label, BorderLayout.NORTH);
        frame.add(new JScrollPane(itemList), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void refreshList(DefaultListModel<String> listModel) {
    listModel.clear();
    for (LibraryItem item : libraryItems) {
        String type = (item instanceof Book) ? "Book" : "Magazine";
        String details = (item instanceof Book) 
                            ? ((Book) item).getTitle() 
                            : ((Magazine) item).getIssue();
        String status = item.isAvailable() ? " (Available)" : " (Borrowed)";
        
        listModel.addElement(item.getId() + ": " + type + " - " + details + status);
    }


    }
    private void handleAddAction(JFrame frame, DefaultListModel<String> listModel){
        JDialog dialog = new JDialog(frame, "Add New Item", true);
        dialog.setSize(400, 200);
        dialog.setLayout(null);
         
        JButton button1 = new JButton("Book");
        button1.setBounds(50,150, 95, 30);
        JButton button2 = new JButton("Magazine");   
        button2.setBounds(150,150, 95, 30);
        
        JLabel labelId = new JLabel("Insert ID:");
        labelId.setBounds(50, 50, 100, 20);
        JTextField insertId = new JTextField();
        insertId.setBounds(150, 50, 200, 20);

        JLabel labelTitle = new JLabel("Insert Title/Issue:");
        labelTitle.setBounds(50, 100, 100, 20);
        JTextField insertTitle = new JTextField();
        insertTitle.setBounds(150, 100, 200, 20);

        button1.addActionListener(e -> {
            addLibraryItem(new Book(insertId.getText(), insertTitle.getText()));
            refreshList(listModel);
            dialog.dispose();
        });
        button2.addActionListener(e -> {
            addLibraryItem(new Magazine(insertId.getText(), insertTitle.getText()));
            refreshList(listModel);
            dialog.dispose();
        }); 
        dialog.add(labelId);
        dialog.add(insertId);
        dialog.add(labelTitle);
        dialog.add(insertTitle);
        dialog.add(button1);
        dialog.add(button2);
        dialog.setVisible(true);
    }
    
    private void handleDeleteAction(JFrame frame, JList<String> itemList,DefaultListModel<String> listModel){
        int selectedIndex = itemList.getSelectedIndex();
        if(selectedIndex >= 0){
            removeLibraryItem(libraryItems.get(selectedIndex));
            refreshList(listModel);
        }
        else
            JOptionPane.showMessageDialog(frame, "Select an item to delete.");
    }
    
    private void handleBorrowAction(JFrame frame, JList<String> itemList, DefaultListModel<String> listModel) {
        int selectedIndex = itemList.getSelectedIndex();
        if (selectedIndex >= 0) {
            LibraryItem item = libraryItems.get(selectedIndex);
            if (item.isAvailable()) {
                item.borrowItem();
                JOptionPane.showMessageDialog(frame, "Borrowed! Due date: " + item.getDueToDate());
            } else {
                JOptionPane.showMessageDialog(frame, "Item is already borrowed!");
            }
            refreshList(listModel);
        } else {
            JOptionPane.showMessageDialog(frame, "Select an item to borrow.");
        }
    }

    private void handleReturnAction(JFrame frame, JList<String> itemList, DefaultListModel<String> listModel) {
        int selectedIndex = itemList.getSelectedIndex();
        if (selectedIndex >= 0) {
            LibraryItem item = libraryItems.get(selectedIndex);
            if (!item.isAvailable()) {
                item.returnItem();
                JOptionPane.showMessageDialog(frame, "Item returned!");
            } else {
                JOptionPane.showMessageDialog(frame, "Item is already available!");
            }
            refreshList(listModel);
        } else {
            JOptionPane.showMessageDialog(frame, "Select an item to return.");
        }
    }

    public static void main(String[] args) {
        new LibraryManagement();
    }
}
