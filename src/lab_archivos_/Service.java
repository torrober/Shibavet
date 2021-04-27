/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_archivos_;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author graba
 */
public class Service {

    String day;
    String h1;
    String h2;
    String serviceType;
    int ownersID;
    String petName;

    public Service(String day, String h1, String h2, String serviceType, int ownersID, String petName) {
        this.day = day;
        this.h1 = h1;
        this.h2 = h2;
        this.serviceType = serviceType;
        this.ownersID = ownersID;
        this.petName = petName;
    }

    // hecho en socialismo
    public void bookService(javax.swing.JTable table) {
        File citas = new File("Citas.txt");
        FileWriter fw = null;
        FileReader leer;
        BufferedReader br;
        PrintWriter copiar;
        int f;
        try {
            fw = new FileWriter(citas, true);
            copiar = new PrintWriter(fw);
            leer = new FileReader(citas);
            br = new BufferedReader(leer);
            try {
                int x = table.getSelectedRow();
                int y = table.getSelectedColumn();
                String selectedValue = (String) table.getValueAt(x, y);
                if (selectedValue != "RESERVADO") {
                    if (this.h1 != null && this.h2 != null && this.day != null && this.serviceType != null) {
                        copiar.print(this.day + "," + this.h1 + "," + this.h2 + "," + this.serviceType + "," + this.ownersID + "," + this.petName);
                        copiar.println();
                        JOptionPane.showMessageDialog(null, "Petición de cita exitosa");
                    } else {
                        JOptionPane.showMessageDialog(null, "El horario que seleccionó no está disponible");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El horario que seleccionó no está disponible");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "El horario que seleccionó no está disponible");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Seleccione una hora");
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    public static void getServiceInfo(ArrayList<String> array, javax.swing.JTable table){
    }
    public static ArrayList<String> getTimeLapse(javax.swing.JComboBox serviceType, javax.swing.JTable table) {
        int x = table.getSelectedRow();
        int y = table.getSelectedColumn();
        String h1 = null;
        String h2 = null;
        ArrayList<String> timeLapse = new ArrayList<String>();
        switch (serviceType.getSelectedItem().toString()) {
            case "Consulta":
                h1 = (String) table.getValueAt(x, 0);
                h2 = (String) table.getValueAt(x + 1, 0);
                System.out.println(h2);
                break;
            case "Control":
                h1 = (String) table.getValueAt(x, 0);
                h2 = (String) table.getValueAt(x, 0);
                break;
            case "Desparasitación":
                h1 = (String) table.getValueAt(x, 0);
                h2 = (String) table.getValueAt(x, 0);
                break;
            case "Vacunación":
                h1 = (String) table.getValueAt(x, 0);
                h2 = (String) table.getValueAt(x, 0);
                break;
            case "Guarderia":
                h1 = (String) table.getValueAt(0, 0);
                h2 = (String) table.getValueAt(19, 0);
                break;
            case "Radiologia":
                h1 = (String) table.getValueAt(x, 0);
                h2 = (String) table.getValueAt(x + 1, 0);
                break;
            case "Baño":
                h1 = (String) table.getValueAt(x, 0);
                h2 = (String) table.getValueAt(x + 3, 0);
                break;
        }
        timeLapse.add(h1);
        timeLapse.add(h2);
        return timeLapse;
    }

}
