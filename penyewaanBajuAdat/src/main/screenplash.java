/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Th3-TW1N5
 */
public class screenplash {
    
    public static void main(String[] args) {
        Splash splash = new Splash();
        splash.setVisible(true);
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(50);
                splash.jLabel1.setText(Integer.toString(i) + "%");
                splash.jProgressBar1.setValue(i);
                if (i == 100) {
                    
                    splash.setVisible(false);
                    new auth.AuthForm().setVisible(true);
                    
                }                
            }
        } catch (Exception e) {
        }
    }
}
