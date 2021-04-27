/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_archivos_;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author graba
 */
public class MedicalRecord {

    String date;
    int ownersID;
    String petName;
    String diagnose;
    String prescription;

    public MedicalRecord(String date, int ownersID, String petName, String diagnose,String prescription) {
        this.date = date;
        this.ownersID = ownersID;
        this.petName = petName;
        this.diagnose = diagnose;
        this.prescription = prescription;
    }

    public void createMedicalRecord() {
        try {
            File dir = new File(".");
            String loc = dir.getCanonicalPath() + File.separator + "Historias.txt";
            FileWriter fstream = new FileWriter(loc, true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(this.date + "," + this.ownersID + "," + this.petName + "," + this.diagnose + "," + this.prescription);
            out.newLine();
            out.close();
            JOptionPane.showMessageDialog(null, "Historia añadida!");
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                    "No se pudo añadir a el historial",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
