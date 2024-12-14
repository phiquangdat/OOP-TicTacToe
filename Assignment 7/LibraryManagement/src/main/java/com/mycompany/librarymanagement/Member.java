/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagement;
import java.util.*;
/**
 *
 * @author phiquangdat
 */
public abstract class Member {
    private String id;
    private String name;
    private int borrowLimit;
    private List<LibraryItem> borrowedItems;

    public Member(String id, String name, int borrowLimit) {
        this.id = id;
        this.name = name;
        this.borrowLimit = borrowLimit;
        this.borrowedItems = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String getInfo() {
        return this.name + " (Limit: " + this.borrowLimit + ")";
    }

    public boolean borrowItem(LibraryItem item) {
        if (borrowedItems.size() >= borrowLimit) {
            System.out.println("Borrowing limit reached for " + name);
            return false;
        }
        if (item.isAvailable()) {
            borrowedItems.add(item);
            item.borrowItem();
            System.out.println(name + " borrowed: " + item.getId());
            return true;
        }
        System.out.println("Item " + item.getId() + " is not available.");
        return false;
    }

    public void returnItem(LibraryItem item) {
        if (borrowedItems.remove(item)) {
            item.returnItem();
            System.out.println(name + " returned: " + item.getId());
        } else {
            System.out.println(name + " has not borrowed this item.");
        }
    }
}
