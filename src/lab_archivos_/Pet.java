/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_archivos_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Pet {

    String name = "";
    String race = "";
    int age = 0;
    String sex = "";
    int ownersId = 0;

    public Pet() {
        name = "";
        race = "";
        age = 0;
        sex = "";
        ownersId = 0;
    }

    public Pet(String name, String race, int age, String sex, int ownersId) {
        this.name = name;
        this.race = race;
        this.age = age;
        this.sex = sex;
        this.ownersId = ownersId;
    }

    public void addPet() throws NoSuchAlgorithmException {
        try {
            File dir = new File(".");
            String loc = dir.getCanonicalPath() + File.separator + "Mascotas.txt";
            FileWriter fstream = new FileWriter(loc, true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(this.name + "," + this.race + "," + this.age + "," + this.sex + "," + this.ownersId);
            out.newLine();
            out.close();
            JOptionPane.showMessageDialog(null, "Mascota añadida!");
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                    "No se pudo añadir a la mascota",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ArrayList<String> getUserPets(int id) throws IOException {
        File file = new File(".");
        String loc = file.getCanonicalPath() + File.separator + "Mascotas.txt";
        ArrayList<String> pets = new ArrayList<String>();
        String[] petsFromUser = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(loc));
            Object[] lines = br.lines().toArray();
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].toString().trim();
                if (line.contains(Integer.toString(id))) {
                    String[] data = line.split(",");
                    String petName = data[0];
                    pets.add(petName);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return pets;
    }
}
