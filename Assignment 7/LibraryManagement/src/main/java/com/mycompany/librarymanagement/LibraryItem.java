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
public abstract class LibraryItem {
    private String id;
    protected boolean isAvailable;
    protected LocalDate dueToDate;
    
    public LibraryItem(String id){
        this.id = id;
        this.isAvailable = true;
        this.dueToDate = null;
    }
    
    public String getId(){
        return this.id;
    }
    
    public boolean isAvailable(){
        return this.isAvailable;
    }
    
    public LocalDate getDueToDate(){
        return this.dueToDate;
    }
    
    public void returnItem(){
       this.isAvailable = true;
       this.dueToDate = null;
    }
    
    public abstract void borrowItem();
}
