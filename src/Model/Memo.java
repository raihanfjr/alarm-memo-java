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
public class Memo extends Note{
    private int id;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private int second;
    
    public Memo(int id, String title, String content, int day, int month, int year, int hour, int minute, int second) {
        super(title, content);
        this.id=id;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
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

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }
    
    @Override
    public String toString() {
        String s;
        s = "Title: " + getTitle() + "\n"
                + "Content: " + getContent() + "\n";
        return s;
    }
    
    public String showReminder() {
        String mTitle = getTitle();
        String mContent = getContent();
        return JOptionPane.showInputDialog(mTitle, mContent);
    }
    public String toStringTitle() {
        String s;
        s = getTitle(); 
        return s;
    }
    
    public String toStringContent() {
        String s;
        s = getContent();
        return s;
    }
    public String toStringLU() {
        String s;
        s = Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second)+"-"+Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);
        return s;
    }
}
