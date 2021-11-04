/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.PlaySound.playSound;

/**
 *
 * @author SULTAN
 */
public class Alarm {
    private int id;
//    private int currentDay;
//    private int currentMonth;
//    private int currentYear;
//    private int currentHour;
//    private int currentMinute;
//    private int currentSecond;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private int second;
    private String current;
    private boolean stateOn = false;
    private Reminder reminder;
    

    /**
     *
     * @param currentDay
     * @param currentMonth
     * @param currentYear
     * @param currentHour
     * @param currentMinute
     * @param currentSecond
     * @param day
     * @param month
     * @param year
     * @param hour
     * @param minute
     * @param second
     */
    public Alarm(int id, int day, int month, int year, int hour, int minute, int second, Reminder reminder) {
        this.id = id;
        this.stateOn = false;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.reminder = reminder;
    }
    public Alarm(Reminder reminder,int day,int month,int year,int hour,int minute,int second){
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.reminder = reminder;
    }

    public Alarm(String current) {
        this.current = current;
    }

    public int getCurrentHour() {
        return java.time.LocalDate.now().getDayOfMonth();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
//    public void setCurrentDay(int currentDay) {
//        this.currentDay = java.time.LocalDate.now().getDayOfMonth();
//    }
//
//    public void setCurrentMonth(int currentMonth) {
//        this.currentMonth = java.time.LocalDate.now().getMonthValue();
//    }
//
//    public void setCurrentYear(int currentYear) {
//        this.currentYear = java.time.LocalDate.now().getYear();
//    }
//
//    public void setCurrentHour(int currentHour) {
//        this.currentHour = java.time.LocalTime.now().getHour();
//    }
//
//    public void setCurrentMinute(int currentMinute) {
//        this.currentMinute = java.time.LocalTime.now().getMinute();
//    }
//
//    public void setCurrentSecond(int currentSecond) {
//        this.currentSecond = java.time.LocalTime.now().getSecond();
//    }

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

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
    
    public boolean isStateOn() {
        return stateOn;
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

    public Reminder getReminder() {
        return reminder;
    }
    
    @Override
    public String toString() {
        String s = "Second: " + second + "\n"
                + "Minute: " + minute + "\n"
                + "Hour: " + hour + "\n"
                + "Day: " + day + "\n"
                + "Month: " + month + "\n"
                + "Year: " + year + "\n";
        return s;
    }
    public String toStringTitle() {
        String s = reminder.getTitle();
        return s;
    }
    public String toStringContent() {
        String s = reminder.getContent();
        return s;
    }
    public String toStringDay() {
        String s = String.valueOf(getDay());
        return s;
    }
    public String toStringMonth() {
        String s = String.valueOf(getMonth());
        return s;
    }
    public String toStringYear() {
        String s = String.valueOf(getYear());
        return s;
    }
    public String toStringHour() {
        String s = String.valueOf(getHour());
        return s;
    }
    public String toStringMinute() {
        String s = String.valueOf(getMinute());
        return s;
    }
    public String toStringSecond() {
        String s = String.valueOf(getSecond());
        return s;
    }
    public void alarmOn() {
        if (/*java.time.LocalDate.now().getDayOfMonth() == this.getDay() 
                && java.time.LocalDate.now().getMonthValue() == this.getMonth()
                && (java.time.LocalDate.now().getYear()-2000) == this.getYear
                && */
                //java.time.LocalTime.now().getHour() == this.getHour()&&
                //java.time.LocalTime.now().getMinute() == this.getMinute()&&
                java.time.LocalTime.now().getSecond() == this.getSecond()
                //&& java.time.LocalTime.now().getMinute() == this.getMinute()
                //&& java.time.LocalTime.now().getHour() == this.getHour()) 
                ){
            this.stateOn = true;
            reminder.showReminder();
            playSound("C:\\Users\\user\\Downloads\\Alarm&MemoYUDA\\Alarm&Memo\\Sound\\alarm.wav");
            
        }
    }
}

