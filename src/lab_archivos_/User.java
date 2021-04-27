/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_archivos_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author graba
 */
public class User {

    private String username;
    private String password;
    private String birthdate;
    private int id;
    private int type;

    // 0 es cliente, 1 es veterinario, 2 es admin
    public User() {
        username = "";
        password = "";
        id = 0;
        type = 0;
    }

    public User(String username, String password, int id, int type, String birthdate) {
        this.username = username;
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] newpass = encoder.encode(password.getBytes());
        String encodedPassword = new String(newpass);
        this.password = encodedPassword;
        this.id = id;
        this.type = type;
        this.birthdate = birthdate;
    }

    public static Boolean checkUser(String u, String p) throws IOException {
        String userinfo, password;
        Boolean userExists = false;
        userinfo = "";
        password = "";
        String user = "";
        // reads file
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            {
                for (String line; (line = br.readLine()) != null;) {
                    if (line.contains(u)) {
                        userinfo = line;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.ArrayIndexOutOfBoundsException exc) {
            JOptionPane.showMessageDialog(null, "");
        }
        user = userinfo.split(",")[0];
        String encodedPassword = userinfo.split(",")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
        password = new String(decodedBytes);
        if (user.equals(u) && password.equals(p)) {
            userExists = true;
        }
        return userExists;

    }

    public static int getUserType(String u) throws IOException {
        String userinfo = "";
        int userType = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            {
                for (String line; (line = br.readLine()) != null;) {
                    if (line.contains(u)) {
                        userinfo = line;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        userType = Integer.parseInt(userinfo.split(",")[3]);
        return userType;
    }

    public static int getUserID(String u) throws IOException {
        String userinfo = "";
        int userID = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            {
                for (String line; (line = br.readLine()) != null;) {
                    if (line.contains(u)) {
                        userinfo = line;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        userID = Integer.parseInt(userinfo.split(",")[2]);
        return userID;
    }

    public static String getUserNamebyID(int id) throws IOException {
        String userinfo = "";
        String u = Integer.toString(id);
        int userID = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            {
                for (String line; (line = br.readLine()) != null;) {
                    if (line.contains(u)) {
                        userinfo = line;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        String username = userinfo.split(",")[0];
        return username;
    }

    public void createUser() throws NoSuchAlgorithmException {
        try {
            File dir = new File(".");
            String loc = dir.getCanonicalPath() + File.separator + "Usuarios.txt";
            FileWriter fstream = new FileWriter(loc, true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(this.username + "," + this.password + "," + this.id + "," + this.type + "," + this.birthdate);
            out.newLine();
            out.close();
            JOptionPane.showMessageDialog(null, "Usuario creado!");
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                    "No se pudo crear el usuario",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void encodeAllPasswords() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            {
                for (String line; (line = br.readLine()) != null;) {
                    String password = line.split(",")[1];
                    Base64.Encoder encoder = Base64.getEncoder();
                    byte[] newpass = encoder.encode(password.getBytes());
                    String encodedPassword = new String(newpass);
                    String newLine = line.split(",")[0] + "," + encodedPassword + "," + line.split(",")[2] + "," + line.split(",")[3] + "," + line.split(",")[4];
                    replaceLine("Usuarios.txt", newLine, line);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void replaceLine(String fileName, String newString, String originalString) {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while ((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
            }
            reader.close();
            String newtext = oldtext.replaceAll(originalString, newString);
            FileWriter writer = new FileWriter(fileName);
            writer.write(newtext);
            writer.close();
            JOptionPane.showMessageDialog(null, "Cambios guardados");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
