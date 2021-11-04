/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author SULTAN
 */
import Model.Alarm;
import Model.Memo;
import Model.Reminder;
import java.sql.*;
import java.util.*;
public class Database {
    private Connection con;
    public void connect(){
        try{
            String url = "jdbc:mysql://localhost:3306/dbalarmmemo";
            String username = "root";
            String pass = "";
            con = DriverManager.getConnection(url, username, pass);
            System.out.println("Database connected");
        }catch(SQLException ex){
            System.out.println("Connection error");
            ex.printStackTrace();
        }
    }
    public void saveAlarm(Alarm a){
        try{
            String query1 = "insert into reminder values('" +a.getReminder().getId()+"','"+ a.getReminder().getTitle()+"','"+ a.getReminder().getContent()+"')";
            Statement s1 = con.createStatement();
            s1.execute(query1);
        }catch(SQLException ex){
            System.out.println("saving reminder : error ");
        }
        System.out.println("saving reminder : succeed");
        try{
            String query = "insert into alarm values('" +a.getId()+"','"+ a.getReminder().getId()+"','"+a.getSecond()+"','"+a.getMinute()+"','"+a.getHour()+"','"+a.getDay()+"','"+a.getMonth()+"','"+a.getYear()+"')";
            Statement s = con.createStatement();
            s.execute(query);
        }catch(SQLException ex){
            System.out.println("saving alarm : error ");
        }
        System.out.println("saving alarm : succeed");
    }
    public void updateAlarm(Alarm a, Alarm b){
        try{
            String query1 = "update alarm set second = "+b.getSecond()+", minute = "+b.getMinute()+", hour = "+b.getHour()+", day = "+b.getDay()+", month = "+b.getMonth()+", year = "+b.getYear()+" where id_alarm = "+b.getId()+";";
            Statement s1 = con.createStatement();
            s1.execute(query1);
        }catch(SQLException ex){
            System.out.println("saving alarm : error ");
        }
        System.out.println("saving alarm : succeed");
        try{
            String query = "update reminder set title = '"+b.getReminder().getTitle()+"', content = '"+b.getReminder().getContent()+"' where id_reminder = "+b.getId()+";";
            Statement s = con.createStatement();
            s.execute(query);
        }catch(SQLException ex){
            System.out.println("saving reminder : error ");
        }
        System.out.println("saving reminder : succeed");
    }
    
    public ArrayList<Alarm> loadAllAlarm(){
        try{
            ArrayList<Alarm> listAlarm = new ArrayList();
            Statement stmt = con.createStatement();
            String query = "select * from alarm inner join reminder on alarm.id_reminder = reminder.id_reminder";
            ResultSet rs = stmt.executeQuery(query);
            Alarm a = null;
            Reminder r = null;
            while(rs.next()){
                int idA = Integer.parseInt(rs.getString(1));
                int idR = Integer.parseInt(rs.getString(2));
                int s = Integer.parseInt(rs.getString(3));
                int m = Integer.parseInt(rs.getString(4));
                int h = Integer.parseInt(rs.getString(5));
                int d = Integer.parseInt(rs.getString(6));
                int mt = Integer.parseInt(rs.getString(7));
                int y = Integer.parseInt(rs.getString(8));
                int idR1 = Integer.parseInt(rs.getString(9));
                String t = rs.getString(10);
                String c = rs.getString(11);
                r = new Reminder(idR, t, c);
                a = new Alarm(idA, d, mt, y, h, m, s, r);
                listAlarm.add(a);
            }
            return listAlarm;
        }catch(Exception e){
            System.out.println("error loading list of alarm");
            return null;
        }
    }
    public void saveMemo(Memo m){
        try{
            String query = "insert into memo values('" + m.getId()+"','"+ m.getTitle()+"','"+ m.getContent()+"','"+m.getSecond()+"','"+m.getMinute()+"','"+m.getHour()+"','"+m.getDay()+"','"+m.getMonth()+"','"+m.getYear()+"')";
            Statement s = con.createStatement();
            s.execute(query);
        }catch(SQLException ex){
            System.out.println("saving memo : error");
        }
        System.out.println("saving memo : suceeded");
    }
    public void updateMemo(Memo m, Memo m1){
        try{
            String query = "update memo set title = '"+m1.getTitle()+"', content = '"+m1.getContent()+"', second = '"+m1.getSecond()+"', minute = '"+m1.getMinute()+"', hour = '"+m1.getHour()+"', day = '"+m1.getDay()+"', month = '"+m1.getMonth()+"', year = '"+m1.getYear()+"' where id_memo = '"+m.getId()+"'";
            Statement s = con.createStatement();
            s.execute(query);
        }catch(SQLException ex){
            System.out.println("update memo : error");
        }
        System.out.println("update memo : succeded");
    }
    public ArrayList<Memo> loadAllMemo(){
        try{
            ArrayList<Memo> listMemo = new ArrayList();
            Statement stmt = con.createStatement();
            String query = "select * from memo";
            ResultSet rs = stmt.executeQuery(query);
            Memo me = null;
            while(rs.next()){
                int idM = Integer.parseInt(rs.getString(1));
                String t = rs.getString(2);
                String c = rs.getString(3);
                int s = Integer.parseInt(rs.getString(4));
                int m = Integer.parseInt(rs.getString(5));
                int h = Integer.parseInt(rs.getString(6));
                int d = Integer.parseInt(rs.getString(7));
                int mt = Integer.parseInt(rs.getString(8));
                int y = Integer.parseInt(rs.getString(9));
                me = new Memo(idM, t, c, d, mt, y, h, m , s);
                listMemo.add(me);
            }
            return listMemo;
        }catch(Exception e){
            System.out.println("error loading list of alarm");
            return null;
        }
    }
    public Memo loadMemoById(int id){
        try{
            Statement stmt = con.createStatement();
            String query = "select * from memo where id_memo='" + id + "'";
            ResultSet rs = stmt.executeQuery(query);
            Memo me = null;
            while (rs.next()){
                int idM = Integer.parseInt(rs.getString(1));
                String t = rs.getString(2);
                String c = rs.getString(3);
                int s = Integer.parseInt(rs.getString(4));
                int m = Integer.parseInt(rs.getString(5));
                int h = Integer.parseInt(rs.getString(6));
                int d = Integer.parseInt(rs.getString(7));
                int mt = Integer.parseInt(rs.getString(8));
                int y = Integer.parseInt(rs.getString(9));
                me = new Memo(idM, t, c, d, mt, y, h, m , s);
            }
            return me;
        }catch(Exception e){
            System.out.println("error loading a memo");
            return null;
        }
    }
    public Alarm loadAlarmById(int id){
        try{
            Statement stmt = con.createStatement();
            String query = "select * from alarm inner join reminder on alarm.id_reminder = reminder.id_reminder where id_alarm='" + id + "'";
            ResultSet rs = stmt.executeQuery(query);
            Alarm a = null;
            Reminder r = null;
            while(rs.next()){
                int idA = Integer.parseInt(rs.getString(1));
                int idR = Integer.parseInt(rs.getString(2));
                int s = Integer.parseInt(rs.getString(3));
                int m = Integer.parseInt(rs.getString(4));
                int h = Integer.parseInt(rs.getString(5));
                int d = Integer.parseInt(rs.getString(6));
                int mt = Integer.parseInt(rs.getString(7));
                int y = Integer.parseInt(rs.getString(8));
                int idR1 = Integer.parseInt(rs.getString(9));
                String t = rs.getString(10);
                String c = rs.getString(11);
                r = new Reminder(idR, t, c);
                a = new Alarm(idA, d, mt, y, h, m, s, r);
            }
            return a;
        }catch(Exception e){
            System.out.println("error loading a alarm");
            return null;
        }
    }
            
    public void deleteAAlarm(Alarm a){
        try{
            String query = "delete from alarm where id_alarm="+a.getId()+";";
            Statement s = con.createStatement();
            s.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("alarm delettion error");
            ex.printStackTrace();
        }
        System.out.println("alarm deletion suceed");
        try{
            String query = "delete from reminder where id_reminder="+a.getId()+";";
            Statement s = con.createStatement();
            s.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("reminder delettion error");
            ex.printStackTrace();
        }
        System.out.println("reminder deletion suceed");
    }
    public void deleteAMemo(Memo m){
        try{
            String query = "delete from memo where id_memo = '"+m.getId()+"'";
            Statement s = con.createStatement();
            s.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("memo delettion error");
            ex.printStackTrace();
        }
        System.out.println("memo deletion suceed");
    }
    
}

