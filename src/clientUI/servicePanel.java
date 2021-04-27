package clientUI;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class servicePanel extends javax.swing.JFrame {

    String h1;
    String h2;
    String t;

    public servicePanel() {
        initComponents();
        horas.getTableHeader().setReorderingAllowed(false);
        horas.getTableHeader().setResizingAllowed(false);
        initData(horas);
    }

    private ArrayList<String[]> LeerDatos() {
        String linea;
        String[] div = {};
        ArrayList datos = new ArrayList<String[]>();
        int c;
        try {
            File h = new File("Citas.txt");
            FileReader fr = new FileReader(h);
            BufferedReader br = new BufferedReader(fr);
            linea = br.readLine();
            while (linea != null) {
                c = 0;
                div = linea.split("/");
                datos.add(div);
                linea = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return datos;
    }
    private void initData(javax.swing.JTable horas) {
                ArrayList<String[]> filas = LeerDatos();
        int d = 0;

        for (int i = 0; i <= filas.size() - 1; i++) {
            String[] fila = filas.get(i);
            if (!fila[3].equals("Baño") && !fila[3].equals("Guarderia")) {
                for (int j = 0; j <= 5; j++) {
                    if (horas.getColumnName(j).equals(fila[0])) {
                        d = j;
                    }
                }
                for (int k = 0; k <= 19; k++) {
                    if (horas.getValueAt(k, 0).equals(fila[1])) {
                        horas.setValueAt("<html><div bgcolor='red' color='white'>RESERVADO</div></html>", k, d);
                        //horas.setBackground( Color.RED );
                    }
                    if (horas.getValueAt(k, 0).equals(fila[2])) {
                        horas.setValueAt("<html><div bgcolor='red' color='white'>RESERVADO</div></html>", k, d);
                        //horas.setBackground( Color.RED );
                    }
                }
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boton = new javax.swing.JButton();
        tipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        horas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(300, 100));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(580, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(580, 500));

        boton.setText("Guardar");
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActionPerformed(evt);
            }
        });

        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Consulta", "Control", "Desparasitación", "Vacunación", "Guarderia", "Radiologia", "Baño" }));
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo de Cita");

        horas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"8:00-8:30", null, null, null, null, null},
                {"8:30-9:00", null, null, null, null, null},
                {"9:00-9:30", null, null, null, null, null},
                {"9:30-10:00", null, null, null, null, null},
                {"10:00-10:30", null, null, null, null, null},
                {"10:30-11:00", null, null, null, null, null},
                {"11:00-11:30", null, null, null, null, null},
                {"11:30-12:00", null, null, null, null, null},
                {"12:00-12:30", null, null, null, null, null},
                {"12:30-13:00", null, null, null, null, null},
                {"13:00-13:30", null, null, null, null, null},
                {"13:30-14:00", null, null, null, null, null},
                {"14:00-14:30", null, null, null, null, null},
                {"14:30-15:00", null, null, null, null, null},
                {"15:00-15:30", null, null, null, null, null},
                {"15:30-16:00", null, null, null, null, null},
                {"16:00-16:30", null, null, null, null, null},
                {"16:30-17:00", null, null, null, null, null},
                {"17:00-17:30", null, null, null, null, null},
                {"17:30-18:00", null, null, null, null, null}
            },
            new String [] {
                "Horas", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(horas);

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(boton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActionPerformed
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
                t = (String) tipo.getSelectedItem();
                int x = horas.getSelectedRow();
                int y = horas.getSelectedColumn();
                String d = horas.getColumnName(y);
                String selectedValue = (String) horas.getValueAt(x, y);
                if(selectedValue != "<html><div bgcolor='red' color='white'>RESERVADO</div></html>"){
                    switch (t) {
                        case "Desparasitación":
                        case "Control":
                        case "Vacunación":
                            h1 = (String) horas.getValueAt(x, 0);
                            h2 = (String) horas.getValueAt(x, 0);
                            break;
                        case "Guarderia":
                            h1 = (String) horas.getValueAt(0, 0);
                            h2 = (String) horas.getValueAt(19, 0);
                            break;
                        default:
                            System.out.println("cagaste");
                            break;
                    }
                    if (h1 != null && h2 != null && d != null && t != null) {
                        copiar.print(d + "/" + h1 + "/" + h2 + "/" + t);
                        copiar.println();
                        JOptionPane.showMessageDialog(null, "Petición de cita exitosa");
                    } else {
                        JOptionPane.showMessageDialog(null, "El horario que seleccionó no está disponible");
                    }} else {JOptionPane.showMessageDialog(null, "El horario que seleccionó no está disponible");}
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
        
        initData(horas);
    }//GEN-LAST:event_botonActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(servicePanel.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(servicePanel.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(servicePanel.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(servicePanel.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new servicePanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton;
    private javax.swing.JTable horas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> tipo;
    // End of variables declaration//GEN-END:variables
}
