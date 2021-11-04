/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.swing.JOptionPane;

/**
 *
 * @author SULTAN
 */
public class Reminder extends Note{
    private int id;

    public Reminder(int id, String title, String content) {
        super(title, content);
        this.id=id;
    }

    @Override
    public void setContent(String content) {
        super.setContent(content); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getContent() {
        return super.getContent(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTitle() {
        return super.getTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    @Override
    public String toString() {
        String s;
        s = "Title: " + getTitle() + "\n"
            + "Content: " + getContent() + "\n";
        return s;
    }
    
    public String showReminder() {
        String rTitle = getTitle();
        String rContent = getContent();
        return JOptionPane.showInputDialog(rTitle, rContent);
    }
  
}
