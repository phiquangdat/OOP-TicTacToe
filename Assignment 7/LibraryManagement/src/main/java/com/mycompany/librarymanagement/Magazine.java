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
public class Magazine extends LibraryItem {
    private String issue;
    
    public Magazine(String id, String issue){
        super(id);
        this.issue = issue;
    }
    
    public String getIssue(){
        return this.issue;
    }
    
    @Override
    public void borrowItem(){
         this.isAvailable = false;
        this.dueToDate = LocalDate.now().plusWeeks(2);
    }
}
