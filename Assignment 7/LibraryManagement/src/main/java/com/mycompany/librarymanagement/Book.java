/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagement;
import java.time.LocalDate;
/**
 *
 * @author phiquangdat
 */
public class Book extends LibraryItem{
    private String title;
    public Book(String id, String title){
        super(id);
        this.title = title;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    
    @Override
    public void borrowItem(){
        this.isAvailable = false;
        this.dueToDate = LocalDate.now().plusWeeks(4);
    }
}
