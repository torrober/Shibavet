/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminUI;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author graba
 */
public class checkoutPane extends javax.swing.JPanel {

    private final int id;

    /**
     * Creates new form checkoutPane
     */
    public checkoutPane(int id) {
        initComponents();
        loadData("Caja.txt", servicesTable, Integer.toString(id));
        DecimalFormat formato = new DecimalFormat("#,###");
        String valorFormateado = formato.format(getTotal(servicesTable));
        jLabel2.setText("$" + valorFormateado + " COP");
        this.id = id;
    }

    public void deleteRow(String archivo, String cadena) throws IOException {
        Path path = Paths.get(archivo);
        List<String> lineas = Files.readAllLines(path);
        lineas = lineas.stream()
                .filter(linea -> !linea.contains(cadena))
                .collect(Collectors.toList());
        Files.write(path, lineas);
    }

    private static void loadData(String filePath, javax.swing.JTable table, String filter) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] tableLines = br.lines().toArray();
            model.setRowCount(0);
            for (int i = 0; i < tableLines.length; i++) {
                String line = tableLines[i].toString().trim();
                if (line.contains(filter)) {
                    String[] dataRow = line.split(",");
                    if (dataRow[4].equals(filter)) {
                        dataRow[1] = dataRow[1].split("-")[0];
                        dataRow[2] = dataRow[2].split("-")[1];
                        model.addRow(dataRow);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private int getTotal(JTable table) {
        int total = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            String service = (String) table.getValueAt(i, 3);
            switch (service) {
                case "Consulta":
                    total += 60000;
                    break;
                case "Control":
                    total += 10000;
                    break;
                case "Desparasitación":
                    total += 40000;
                    break;
                case "Vacunación":
                    total += 35000;
                    break;
                case "Guarderia":
                    total += 35000;
                    break;
                case "Radiologia":
                    total += 100000;
                    break;
                case "Baño":
                    total += 25000;
                    break;
            }
        }
        return total;
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
                    String[] dataRow = line.split(",");
                    if (dataRow[4].equals(filter)) {
                    linesThatContainFilter.add(i);}
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return linesThatContainFilter;
    }
    private int getValueOfService(JTable table, int row) {
        int value = 0;
        String service = (String) table.getValueAt(row, 3);
        switch (service) {
            case "Consulta":
                value = 60000;
                break;
            case "Control":
                value = 10000;
                break;
            case "Desparasitación":
                value = 40000;
                break;
            case "Vacunación":
                value = 35000;
                break;
            case "Guarderia":
                value = 35000;
                break;
            case "Radiologia":
                value = 100000;
                break;
            case "Baño":
                value = 25000;
                break;
        }
        return value;
    }

    private void writeCurrentTicket(String file, JTable table) {
        try {
            File dir = new File(".");
            String loc = dir.getCanonicalPath() + File.separator;
            File fold = new File(loc + File.separator + file);
            if (fold.exists()) {
                fold.delete();
            }
            File fnew = new File(loc + File.separator + file);
            DecimalFormat formato = new DecimalFormat("#,###");
            String total = formato.format(getTotal(table));
            try {
                FileWriter f2 = new FileWriter(fnew, false);
                String styles = "*{font-size:12px;font-family:Arial}table,td,th,tr{border-top:1px solid #000;border-collapse:collapse}td.producto,th.producto{width:75px;max-width:75px}td.cantidad,th.cantidad{width:40px;max-width:40px}td.precio,th.precio{width:40px;max-width:40px}.centrado{text-align:center;align-content:center}.ticket{width:155px;max-width:155px}img{max-width:inherit;width:inherit}";
                f2.write("<!DOCTYPE html><html><head><title>Factura - Shibavet</title><style>" + styles + "</style><script>window.onload = window.print();</script></head><body> <div class='ticket'> <img src='https://i.imgur.com/zoyttEX.jpg' alt='Logotipo'> <p class='centrado'>FACTURA</p><table> <thead> <tr> <th class='cantidad'>CANT</th> <th class='producto'>PRODUCTO</th> <th class='precio'>$$</th> </tr></thead><tbody id='elements'>");
                for (int i = 0; i < table.getRowCount(); i++) {
                    String day = (String) table.getValueAt(i, 0);
                    String service = (String) table.getValueAt(i, 3);
                    String ownerID = (String) table.getValueAt(i, 4);
                    f2.write("<tr> <td class='cantidad'></td><td class='producto'>" + service + "</td><td class='precio'>$" + formato.format(getValueOfService(table, i)) + "</td></tr>");
                }
                f2.write("<tr> <td class='cantidad'></td><td class='producto'>TOTAL</td><td class='precio'>$" + total + "</td></tr></tbody> </table> <p class='centrado'>¡GRACIAS POR SU PREFERENCIA!</p><br>Shibavet </p></div></body></html>");
                f2.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            Logger.getLogger(checkoutPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        servicesTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        servicesTable.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        servicesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Dia", "Inicio", "Fin", "Servicio", "Cedula", "Mascota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel1.setText("Total a pagar");

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 48)); // NOI18N
        jLabel2.setText("$0,00 COP");

        jButton1.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jButton1.setText("Generar factura");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void servicesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_servicesTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_servicesTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        writeCurrentTicket("index.html", servicesTable);
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                File dir = new File(".");
                String loc = dir.getCanonicalPath() + File.separator;
                URI locURI = new File(loc + "index.html").toURI();
                Desktop.getDesktop().browse(locURI);

            } catch (IOException ex) {
                Logger.getLogger(checkoutPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (Integer line: getLinesContainingFilter("Caja.txt", Integer.toString(this.id))) {
            try {
                String s = Files.readAllLines(Paths.get("Caja.txt")).get(line);
                deleteRow("Caja.txt", s);
            } catch (IOException ex) {
                Logger.getLogger(checkoutPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable servicesTable;
    // End of variables declaration//GEN-END:variables
}
