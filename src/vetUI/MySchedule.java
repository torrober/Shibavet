/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vetUI;

import clientUI.*;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import lab_archivos_.JTabbedPaneWithCloseButton;
import lab_archivos_.User;

/**
 *
 * @author graba
 */
public class MySchedule extends javax.swing.JFrame {

    /**
     * Creates new form MyPets
     */
    int mouseX;
    int mouseY;
    int id;
    private String currentDay;

    public MySchedule(int id) {
        initComponents();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
            }
        });
        jLabel4.setText("Shibavet");
        this.id = id;
        servicesTable.setFocusable(false);
        servicesTable.setRowSelectionAllowed(true);
        //jPanel4.setLayout(new BorderLayout());
        //jPanel4.add(new addPetPane(this.id));
        this.currentDay = getDay();
        loadData("Citas.txt", servicesTable, this.currentDay);
    }

    public String getDay() {
        Calendar now = Calendar.getInstance();
        // Array con los dias de la semana
        String[] strDays = new String[]{
            "Domingo",
            "Lunes",
            "Martes",
            "Miercoles",
            "Jueves",
            "Viernes",
            "Sabado"};
        String day = strDays[now.get(Calendar.DAY_OF_WEEK) - 1];
        return day;
    }

    public static void deleteRow(String archivo, String cadena) throws IOException {
        Path path = Paths.get(archivo);
        List<String> lineas = Files.readAllLines(path);
        lineas = lineas.stream()
                .filter(linea -> !linea.contains(cadena))
                .collect(Collectors.toList());
        Files.write(path, lineas);
    }

    private static void loadData(String filePath, javax.swing.JTable table, String filter) {
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] tableLines = br.lines().toArray();
            model.setRowCount(0);
            for (int i = 0; i < tableLines.length; i++) {
                String line = tableLines[i].toString().trim();
                if (!line.contains("Baño") && line.contains(filter)) {
                    String[] dataRow = line.split(",");
                    dataRow[1] = dataRow[1].split("-")[0];
                    dataRow[2] = dataRow[2].split("-")[1];
                    model.addRow(dataRow);
                } else if (line.contains("Baño")){
                    addToCashier(line);
                    deleteRow("Citas.txt", line);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static ArrayList<Integer> getLinesContainingFilter(String filePath, String filter) {
        File file = new File(filePath);
        ArrayList<Integer> linesThatContainFilter = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Object[] lines = br.lines().toArray();
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i].toString().trim();
                if (line.contains(filter) && !line.contains("Baño")) {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        servicesTable = new javax.swing.JTable();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
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
        jLabel2.setText("Mi horario");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lab_archivos_/img/baseline_assignment_turned_in_black_24dp.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.setEnabled(false);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        servicesTable.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        servicesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Dia", "Inicio", "Fin", "Servicio", "Ced. del dueño", "Mascota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        servicesTable.setShowHorizontalLines(true);
        servicesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                servicesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(servicesTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
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

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        if (this.isEnabled()) {
            System.out.println("enabled");
            try {
                int row = servicesTable.getSelectedRow();
                int line = getLinesContainingFilter("Citas.txt", this.currentDay).get(row);
                String s = Files.readAllLines(Paths.get("Citas.txt")).get(line);
                int opt = JOptionPane.showConfirmDialog(null, "Ya acabó la cita a "+ servicesTable.getValueAt(row, 5)+"?");
                if (opt == 0) {
                    addToCashier(s);
                    deleteRow("Citas.txt", s);
                }
            } catch (IOException ex) {
                Logger.getLogger(MyPets.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(MySchedule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLabel3MouseClicked
    public static void addToCashier(String s) throws NoSuchAlgorithmException {
        try {
            File dir = new File(".");
            String loc = dir.getCanonicalPath() + File.separator + "Caja.txt";
            FileWriter fstream = new FileWriter(loc, true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(s);
            out.newLine();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        loadData("Citas.txt", servicesTable, this.currentDay);
    }//GEN-LAST:event_formWindowGainedFocus

    private void servicesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_servicesTableMouseClicked
        // TODO add your handling code here:
        jLabel3.setEnabled(true);
    }//GEN-LAST:event_servicesTableMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable servicesTable;
    // End of variables declaration//GEN-END:variables
}