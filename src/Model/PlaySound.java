/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author SULTAN
 */
public class PlaySound {
    public static void main(String[] args) {
        playSound("D:\\Akademik\\semester 4\\PBO\\Alarm&Memo\\Sound\\alarm.wav");
    }
    
    public static void playSound(String filepath) {
        InputStream sound;
        try {
            sound = new FileInputStream(new File(filepath));
            AudioStream audio = new AudioStream(sound);
            AudioPlayer.player.start(audio);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}
