/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_archivos_;

import com.formdev.flatlaf.FlatLightLaf;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author graba
 */
public class Lab_Archivos_ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("Component.focusWidth", 0);
            UIManager.put("Component.arc", 10);
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arrowType", "chevron");
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        Login loginScreen = new Login();
        loginScreen.setVisible(true);
    }

}
