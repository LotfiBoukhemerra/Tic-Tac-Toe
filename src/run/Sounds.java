/*
 CopyRight © 2018 LOTFI BOUKHEMERRA - All Rights Reserved - developed by LOTFI BOUKHEMERRA
*/
package run;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;// you can Download this Lib From: www.javazoom.net

/**
 *
 * @author BOUKHEMERRA Lotfi.
 * repository https://github.com/LotfiBoukhemerra
 */
public class Sounds {
    
    private static Player player;
    
    public static void play(String s) {
        
        new Thread() {
            @Override
            public void run() {
                FileInputStream file;
                try {
                    file = new FileInputStream(s + ".mp3");
                    file.getClass().getResource("/tc/" + s + ".mp3");                   
                    player = new Player(file);
                    player.play();
                } catch (FileNotFoundException | JavaLayerException ex) {
                    Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
                }
            }// end Run
        }.start();

    }// end play

}// end Sound.
