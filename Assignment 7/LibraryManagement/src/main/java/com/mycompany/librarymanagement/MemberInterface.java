/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.librarymanagement;

/**
 *
 * @author phiquangdat
 */
public interface MemberInterface {
    public String getId();
    public String getInfo();
    public void borrowItem(LibraryItem item);
    public void returnItem(LibraryItem item);
}
