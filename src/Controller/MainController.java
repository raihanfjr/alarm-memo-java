/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.AnR_UI;
import View.Choise_UI;
import View.Memo_UI;
import java.awt.event.*;

/**
 *
 * @author user
 */
public class MainController implements ActionListener {
    private ControllerAlarm ca;
    private ControllerMemo cm;
    private Choise_UI viewChoice;
    public MainController(){
        cm = new ControllerMemo();
        ca = new ControllerAlarm();
        
        viewChoice = new Choise_UI();
        viewChoice.addActionListener(this);
        viewChoice.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        Object source = ae.getSource();
        if(source.equals(viewChoice.getBtnAlarm())){
            ca.setVisibleOn();
        }else if(source.equals(viewChoice.getBtnMemo())){
            cm.setVisibleOn();
        }
    }        
    
}
