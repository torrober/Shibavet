/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;
import lab_archivos_.User;

/**
 *
 * @author graba
 */
public class MyData extends javax.swing.JFrame {

    /**
     * Creates new form MyPets
     */
    int mouseX;
    int mouseY;
    int id;
    private String textField = "#216eed";
    String originalString;

    public MyData(int id) throws IOException {
        try {
            initComponents();
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
                }
            });
            jLabel4.setText("Shibavet");
            this.id = id;
            String userLine = loadData("Usuarios.txt", Integer.toString(this.id));
            this.originalString = userLine;
            cedulaField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode(textField)));
            usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode(textField)));
            passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode(textField)));
            jXDatePicker1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode(textField)));
            jXDatePicker1.getEditor().setBorder(null);
            JButton dateBtn = (JButton) jXDatePicker1.getComponent(1);
            dateBtn.setBorder(null);
            String[] userParams = userLine.split(",");
            usernameField.setText(userParams[0]);
            passwordField.setText(userParams[1]);
            cedulaField.setText(userParams[2]);
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Date bday = df.parse(userParams[4]);
            jXDatePicker1.setDate(bday);
        } catch (ParseException ex) {
            Logger.getLogger(MyData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String loadData(String filePath, String filter) {
        File file = new File(filePath);
        String userLine = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].toString().trim();
                if (line.contains(filter)) {
                    userLine = line;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return userLine;
    }

    private static ArrayList<Integer> getLinesContainingFilter(String filePath, String filter) {
        File file = new File(filePath);
        ArrayList<Integer> linesThatContainFilter = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].toString().trim();
                if (line.contains(filter)) {
                    linesThatContainFilter.add(i);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return linesThatContainFilter;
    }

    public void deleteRow(String archivo, String cadena) throws IOException {
        Path path = Paths.get(archivo);
        List<String> lineas = Files.readAllLines(path);
        lineas = lineas.stream()
                .filter(linea -> !linea.contains(cadena))
                .collect(Collectors.toList());
        Files.write(path, lineas);
    }

    private void replaceLine(String fileName, String u, String p) {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while ((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
            }
            reader.close();
            String newString = u + "," + p + "," + this.originalString.split(",")[2] + "," + this.originalString.split(",")[3] + "," + this.originalString.split(",")[4];
            String newtext = oldtext.replaceAll(this.originalString, newString);
            FileWriter writer = new FileWriter(fileName);
            writer.write(newtext);
            writer.close();
            JOptionPane.showMessageDialog(null, "Cambios guardados");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static ArrayList<Integer> getTableLine(String filePath, String filter) {
        File file = new File(filePath);
        ArrayList<Integer> linesThatContainFilter = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].toString().trim();
                if (line.contains(filter)) {
                    linesThatContainFilter.add(i);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return linesThatContainFilter;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        cedulaField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lab_archivos_/img/icon.png")));
        setUndecorated(true);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(79, 83, 255));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lab_archivos_/img/baseline_close_white_18dp.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        jLabel2.setText("Mis Datos");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lab_archivos_/img/baseline_person_black_18dp.png"))); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        usernameField.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        usernameField.setToolTipText("username");
        usernameField.setEnabled(false);
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFieldFocusLost(evt);
            }
        });
        usernameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameFieldMouseClicked(evt);
            }
        });
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lab_archivos_/img/baseline_lock_black_18dp.png"))); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        passwordField.setToolTipText("password");
        passwordField.setEnabled(false);
        passwordField.setPreferredSize(new java.awt.Dimension(73, 24));
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFieldFocusLost(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lab_archivos_/img/baseline_assignment_ind_black_18dp.png"))); // NOI18N
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cedulaField.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        cedulaField.setToolTipText("username");
        cedulaField.setEnabled(false);
        cedulaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cedulaFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cedulaFieldFocusLost(evt);
            }
        });
        cedulaField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cedulaFieldMouseClicked(evt);
            }
        });
        cedulaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaFieldActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lab_archivos_/img/baseline_cake_black_18dp.png"))); // NOI18N
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jXDatePicker1.setEnabled(false);
        jXDatePicker1.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N

        jButton1.setText("Cambiar contraseña");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cedulaField)
                            .addComponent(usernameField)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cedulaField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        this.setLocation(evt.getXOnScreen() - mouseX, evt.getYOnScreen() - mouseY);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        //loadData("Mascotas.txt", petsTable, Integer.toString(id));
    }//GEN-LAST:event_formWindowGainedFocus

    private void usernameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldFocusGained

    private void usernameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldFocusLost

    private void usernameFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameFieldMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldMouseClicked

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void passwordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusGained

    }//GEN-LAST:event_passwordFieldFocusGained

    private void passwordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusLost

    }//GEN-LAST:event_passwordFieldFocusLost

    private void cedulaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cedulaFieldFocusGained

    }//GEN-LAST:event_cedulaFieldFocusGained

    private void cedulaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cedulaFieldFocusLost

    }//GEN-LAST:event_cedulaFieldFocusLost

    private void cedulaFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cedulaFieldMouseClicked

    }//GEN-LAST:event_cedulaFieldMouseClicked

    private void cedulaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cedulaFieldActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        boolean passwordChanged = false;
        JPasswordField pwd_1 = new JPasswordField(10);
        JPasswordField pwd_2 = new JPasswordField(10);
        while (passwordChanged == false) {
            String cedula = JOptionPane.showInputDialog("Ingrese su cedula para continuar");
            if (Integer.parseInt(cedula) == this.id) {
                try {
                    int action = JOptionPane.showConfirmDialog(null, pwd_1, "Ingrese su contraseña actual", JOptionPane.OK_CANCEL_OPTION);
                    if (action != 0) {
                        break;
                    }
                    String oldp = new String(pwd_1.getPassword());
                    String user = this.originalString.split(",")[0];
                    if (User.checkUser(user, oldp)) {
                        int action2 = JOptionPane.showConfirmDialog(null, pwd_2, "Ingrese su nueva contraseña", JOptionPane.OK_CANCEL_OPTION);
                        if (action2 != 0) {
                            break;
                        }
                        String newp = new String(pwd_2.getPassword());
                        Base64.Encoder encoder = Base64.getEncoder();
                        byte[] newpass = encoder.encode(newp.getBytes());
                        newp = new String(newpass);
                        replaceLine("Usuarios.txt", this.originalString.split(",")[0], newp);
                        passwordChanged = true;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MyData.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cedula incorrecta");
                break;
            }
        }
    }//GEN-LAST:event_jButton1MouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cedulaField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
