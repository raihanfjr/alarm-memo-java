/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.Database;
import Model.Alarm;
import Model.Memo;
import View.AnR_UI;
import View.Choise_UI;
import View.Memo_UI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;



/**
 *
 * @author USER
 */
public class ControllerMemo extends MouseAdapter implements ActionListener{
    private ArrayList<Memo> daftarMemo;
    private Memo_UI viewMemo;
    private Choise_UI viewChoice;
    private Database db;
    
    public ControllerMemo(){
        db = new Database();
        db.connect();
        daftarMemo = new ArrayList();
        viewMemo = new Memo_UI();
        viewChoice = new Choise_UI();
        viewMemo.addActionListener(this);
        viewMemo.addMouseAdapter(this);
        viewMemo.setVisible(true);
        loadMemoList();
        viewMemo.setDaftarMemo(getDaftarMemo());
        
    }
    public void setVisibleOn(){
        viewMemo.setVisible(true);
    }
    public void setVisibleOff(){
        viewMemo.setVisible(false);
    }
    private String[] getDaftarMemo(){
       String[] daftarTitle = new String[daftarMemo.size()];
        for (int i = 0; i < daftarTitle.length; i++) {
            daftarTitle[i] = daftarMemo.get(i).getTitle();
        }
        return daftarTitle;
    }
    public int generateIdM(){
        if(daftarMemo.size()==0){
            return daftarMemo.size()+1;
        }else{
            int id=daftarMemo.get(daftarMemo.size()-1).getId();
            return id+1;
        }
    }
    public String getAMemo(int id){
        Memo b = db.loadMemoById(id);
        return b.toString();
    }
    public void deleteMemo(int id){
        Memo m=db.loadMemoById(id);
        db.deleteAMemo(m);
    }
    public void loadMemoList(){
        daftarMemo = db.loadAllMemo();
    }
    public void actionPerformed(ActionEvent ae){
        Object source = ae.getSource();
        if(source.equals(viewMemo.getBtnAddMemo())){
            String title = "Insert Title";
            String content = "Insert Content";
            Memo m = new Memo(generateIdM(), title, content, java.time.LocalDate.now().getDayOfMonth(),java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getYear(), java.time.LocalTime.now().getHour(), java.time.LocalTime.now().getMinute(), java.time.LocalTime.now().getSecond());
            daftarMemo.add(m);
            viewMemo.resetView();   
            viewMemo.setDaftarMemo(getDaftarMemo());
        }else if(source.equals(viewMemo.getBtnDeleteMemo())){
            try{
                int i = viewMemo.getSelectedMemo();
                deleteMemo(daftarMemo.get(i).getId());
                loadMemoList();
                viewMemo.resetView();
                viewMemo.setDaftarMemo(getDaftarMemo());
            }catch(Exception ex){
                System.out.println("deletion exception :"+ex);
            }
            
        }else if(source.equals(viewMemo.getBtnConfirmMemo())){
            int i = viewMemo.getSelectedMemo();
            if (db.loadMemoById(daftarMemo.get(i).getId())==null){
                daftarMemo.remove(i);
                String title = viewMemo.getTitle();
                String content = viewMemo.getContent();
                Memo m = new Memo(generateIdM(), title, content, java.time.LocalDate.now().getDayOfMonth(),java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getYear(), java.time.LocalTime.now().getHour(), java.time.LocalTime.now().getMinute(), java.time.LocalTime.now().getSecond());
                db.saveMemo(m);
            }else{
                String title = viewMemo.getTitle();
                String content = viewMemo.getContent();
                Memo m = new Memo(db.loadMemoById(daftarMemo.get(i).getId()).getId(), title, content, java.time.LocalDate.now().getDayOfMonth(),java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getYear(), java.time.LocalTime.now().getHour(), java.time.LocalTime.now().getMinute(), java.time.LocalTime.now().getSecond());
                db.updateMemo(db.loadMemoById(daftarMemo.get(i).getId()), m);
            }
            loadMemoList();
            viewMemo.resetView();   
            viewMemo.setDaftarMemo(getDaftarMemo());
        }else if(source.equals(viewMemo.getBtnResetMemo())){
            viewMemo.resetView();
            viewMemo.setTitle("Insert Title");
            viewMemo.setContent("Insert Content");
        }else if(source.equals(viewMemo.getBtnReturnFromMemo())){
            viewMemo.setVisible(false);
            viewChoice.setVisible(true);
        }
    }
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if(source.equals(viewMemo.getListMemo())){
            int i = viewMemo.getSelectedMemo();
            Memo m = daftarMemo.get(i);
            viewMemo.setTitle(m.toStringTitle());
            viewMemo.setContent(m.toStringContent());
            viewMemo.setLastUpdate(m.toStringLU());
        }
    }
    
}
