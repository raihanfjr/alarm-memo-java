/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.Database;
import Model.Alarm;
import Model.Memo;
import Model.Reminder;
import View.AnR_UI;
import View.Choise_UI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ControllerAlarm extends MouseAdapter implements ActionListener{
    private AnR_UI viewAlarm;
    private Choise_UI viewChoice;
    private ArrayList<Alarm> daftarAlarm;
    private Database db;

    
    public ControllerAlarm(){
        db = new Database();
        db.connect();
        daftarAlarm = new ArrayList();
        viewAlarm = new AnR_UI();
        viewAlarm.addActionListener(this);
        viewAlarm.addMouseListener(this);
        viewAlarm.setVisible(true);
        loadAlarmList();
        viewAlarm.setDaftarAlarm(getDaftarAlarm());
        //alarm();
        viewAlarm.setCurrentDay();
        
        //String hour = String.valueOf(java.time.LocalTime.now().getHour());
        //Alarm jam = new Alarm(hour);
        //daftarJam.add(jam);
        //viewAlarm.setDaftarAlarm(getDaftarAlarm());
    }
    
    public void setVisibleOn(){
        viewAlarm.setVisible(true);
    }
    public void setVisibleOff(){
        viewAlarm.setVisible(false);
    }
    private String[] getDaftarAlarm(){
       String[] daftarTitle = new String[daftarAlarm.size()];
        for (int i = 0; i < daftarTitle.length; i++) {
            daftarTitle[i] = daftarAlarm.get(i).getReminder().getTitle();
        }
        return daftarTitle;
    }
    
    public void alarm(){
        while(true){
            int i = 0;
            while(i<daftarAlarm.size()){
                Alarm a = daftarAlarm.get(i);
                a.alarmOn();
//                if(a.isStateOn()==true){
//                    deleteAlarm(a.getId());
//                    loadAlarmList();
//                    viewAlarm.resetAlarm();
//                    viewAlarm.setDaftarAlarm(getDaftarAlarm());  
//                }
            }
        }
    }
    
    public int generateIdA(){
        if(daftarAlarm.size()==0){
            return daftarAlarm.size()+1;
        }else{
            int id=daftarAlarm.get(daftarAlarm.size()-1).getId();
            return id+1;
        }
    }
    public String getAAlarm(int id){
        Alarm a = db.loadAlarmById(id);
        return a.toString();
    }
    public void deleteAlarm(int id){
        Alarm a=db.loadAlarmById(id);
        db.deleteAAlarm(a);
    }
    public void loadAlarmList(){
        daftarAlarm = db.loadAllAlarm();
    }
    public void actionPerformed(ActionEvent ae){
        Object source = ae.getSource();
        if(source.equals(viewAlarm.getBtnAdd())){
            int id = generateIdA();
            Reminder reminder = new Reminder(id,"Insert Title","Insert Content");
            int day = 0;
            int month = 0;
            int year = 19;
            int hour = 0;
            int minute = 0;
            int second = 0;
            Alarm m = new Alarm(id,day,month,year,hour,minute,second,reminder);
            daftarAlarm.add(m);
            viewAlarm.resetAlarm();   
            viewAlarm.setDaftarAlarm(getDaftarAlarm());
        }else if(source.equals(viewAlarm.getBtnConfirm())){
            int i = viewAlarm.getSelectedAlarm();
            if (db.loadAlarmById(daftarAlarm.get(i).getId())==null){
                int id = generateIdA();
                daftarAlarm.remove(i);
                Reminder reminder = new Reminder(id, viewAlarm.getTitle(),viewAlarm.getContent());
                int day = Integer.parseInt(viewAlarm.getDay());
                int month = Integer.parseInt(viewAlarm.getMonth());
                int year = Integer.parseInt(viewAlarm.getYear());
                int hour = Integer.parseInt(viewAlarm.getHour());
                int minute = Integer.parseInt(viewAlarm.getMinute());
                int second = Integer.parseInt(viewAlarm.getSecond());
                Alarm m = new Alarm(id,day,month,year,hour,minute,second,reminder);
                db.saveAlarm(m);
            }else{
                Reminder reminder = new Reminder(db.loadAlarmById(daftarAlarm.get(i).getId()).getId(), viewAlarm.getTitle(),viewAlarm.getContent());
                int day = Integer.parseInt(viewAlarm.getDay());
                int month = Integer.parseInt(viewAlarm.getMonth());
                int year = Integer.parseInt(viewAlarm.getYear());
                int hour = Integer.parseInt(viewAlarm.getHour());
                int minute = Integer.parseInt(viewAlarm.getMinute());
                int second = Integer.parseInt(viewAlarm.getSecond());
                Alarm m = new Alarm(db.loadAlarmById(daftarAlarm.get(i).getId()).getId(),day,month,year,hour,minute,second,reminder);
                db.updateAlarm(db.loadAlarmById(daftarAlarm.get(i).getId()), m);
            }
            loadAlarmList();
            viewAlarm.resetAlarm();   
            viewAlarm.setDaftarAlarm(getDaftarAlarm());
        }else if(source.equals(viewAlarm.getBtnDelete())){
            try{
                int i = viewAlarm.getSelectedAlarm();
                deleteAlarm(daftarAlarm.get(i).getId());
                loadAlarmList();
                viewAlarm.resetAlarm();
                viewAlarm.setDaftarAlarm(getDaftarAlarm());
            }catch(Exception ex){
                System.out.println("deletion exception :"+ex);
            }
        }else if(source.equals(viewAlarm.getBtnReset())){
            viewAlarm.resetAlarm();
            viewAlarm.setTitle("Insert Title");
            viewAlarm.setContent("Insert Content");
            viewAlarm.setDay("0");
            viewAlarm.setMonth("0");
            viewAlarm.setYear("19");
            viewAlarm.setHour("0");
            viewAlarm.setMinute("0");
            viewAlarm.setSecond("0");
        }else if(source.equals(viewAlarm.getBtnReturn())){
            viewAlarm.setVisible(false);
            viewChoice.setVisible(true);
        }
}
     public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if(source.equals(viewAlarm.getListAlarm())){
            int i = viewAlarm.getSelectedAlarm();
            Alarm m = daftarAlarm.get(i);
            viewAlarm.setTitle(m.toStringTitle());
            viewAlarm.setContent(m.toStringContent());
            viewAlarm.setDay(m.toStringDay());
            viewAlarm.setMonth(m.toStringMonth());
            viewAlarm.setYear(m.toStringYear());
            viewAlarm.setHour(m.toStringHour());
            viewAlarm.setMinute(m.toStringMinute());
            viewAlarm.setSecond(m.toStringSecond());
        }
    }
}
