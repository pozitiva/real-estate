/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Forms;

import Controller.Controller;
import domain.object.entities.Rezervacija;
import domain.object.entities.UgovorOKupoprodaji;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author korisnik
 */
public class formUgovorKupoprodaja extends javax.swing.JFrame {

    List<UgovorOKupoprodaji> ugovori = new LinkedList<>();
    List<UgovorOKupoprodaji> pronadjeniUgovori = new LinkedList<>();
    List<Rezervacija> rezervacije = new LinkedList<>();
    private Date originalDatum;
    private final HashMap<Integer, String[]> originalneVrednostiUgovora = new HashMap<>();
    public formUgovorKupoprodaja() throws Exception {
        initComponents();
        setTitle("Ugovor o kupoprodaji");
        setLocationRelativeTo(this);
        ucitajPodatkeUFormu();
        setUpTableListener();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        rbDP2 = new javax.swing.JRadioButton();
        rbDP3 = new javax.swing.JRadioButton();
        rbDP1 = new javax.swing.JRadioButton();
        rbDP4 = new javax.swing.JRadioButton();
        btnUcitajUgovore = new javax.swing.JButton();
        txtBrZahteva = new javax.swing.JTextField();
        txtDatum = new javax.swing.JTextField();
        btnSacuvaj = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        rbAll = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUgovor = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cmbRezervacija = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("BR Zahteva");

        rbDP2.setText("2022-2023");

        rbDP3.setText("2023-2024");
        rbDP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDP3ActionPerformed(evt);
            }
        });

        rbDP1.setText("pre 2022");
        rbDP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDP1ActionPerformed(evt);
            }
        });

        rbDP4.setText("2024-2025");

        btnUcitajUgovore.setText("Ucitaj");
        btnUcitajUgovore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUcitajUgovoreActionPerformed(evt);
            }
        });

        txtDatum.setText("dd-MM-yyyy");

        btnSacuvaj.setText("Sacuvaj");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
            }
        });

        btnIzmeni.setText("Izmeni");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        jLabel11.setText("Rezervacija");

        rbAll.setText("Svi zahtevi");
        rbAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAllActionPerformed(evt);
            }
        });

        jLabel1.setText("Ugovor o kupoprodaji");

        tblUgovor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ugovor o kupoprodaji ID", "Datum potpisivanja", "Rezervacija ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblUgovor);

        jLabel2.setText("Datum");

        cmbRezervacija.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbRezervacija.setSelectedIndex(-1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbAll)
                            .addComponent(rbDP2)
                            .addComponent(rbDP3)
                            .addComponent(rbDP1)
                            .addComponent(rbDP4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnUcitajUgovore)))
                        .addContainerGap(39, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtBrZahteva, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDatum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addGap(41, 41, 41)))
                        .addComponent(cmbRezervacija, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIzmeni)
                            .addComponent(btnSacuvaj))
                        .addGap(125, 125, 125))))
            .addGroup(layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbAll)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbDP1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(rbDP2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbDP3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbDP4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUcitajUgovore)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtBrZahteva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(cmbRezervacija, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnIzmeni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSacuvaj)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     private void ucitajPodatkeUFormu() throws Exception {
        rezervacije = Controller.getInstance().loadSveRezervacije();

        if (cmbRezervacija != null) {
            cmbRezervacija.removeAllItems();
            for (Rezervacija r : rezervacije) {
                cmbRezervacija.addItem(String.valueOf(r.getRezervacijaID()));
            }
        } else {
            System.err.println("ComboBox (cmbRezervacija) je null.");
        }
        ucitajUgovore(false);
        rbAll.setSelected(true);
    }

    private void ucitajUgovore(Boolean isPartition) throws Exception {
        DefaultTableModel modelUgovori = (DefaultTableModel) tblUgovor.getModel();
        modelUgovori.setRowCount(0);
        
        if(!isPartition){
        ugovori = Controller.getInstance().loadSveUgovoreOKupoprodaji();
        }
        
        for (UgovorOKupoprodaji u : ugovori) {
            modelUgovori.addRow(new Object[]{u.getUgovorOKupoprodajiID(), u.getDatumPotpisivanja(), u.getRezervacijaID()});
        }
        
        if(isPartition == false) {
            ugovori = Controller.getInstance().loadSveUgovoreOKupoprodaji();
        }
        
        sacuvajOriginalneVrednosti(tblUgovor);
    }
    
    private UgovorOKupoprodaji preuzmiPodatkeZaUgovor() throws Exception {
        int ugovorId = Integer.parseInt(txtBrZahteva.getText());

        String rawDatum = txtDatum.getText();

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date datum = null;

        try {

            if (!rawDatum.isEmpty()) {
                datum = inputFormat.parse(rawDatum);
            }
        } catch (ParseException e) {
            System.err.println("Neispravan format datuma: " + e.getMessage());

        }
        
        String rezervacija = (String) cmbRezervacija.getSelectedItem();
        List<Rezervacija> pronadjenaRezervacija = Controller.getInstance().searchRezervacije("REZERVACIJAID='" + rezervacija + "'");

        if (pronadjenaRezervacija.isEmpty()) {
            throw new Exception("Rezervacija nije pronadjena.");
        }
        int rezervacijaId = pronadjenaRezervacija.get(0).getRezervacijaID();

        UgovorOKupoprodaji u = new UgovorOKupoprodaji(ugovorId, datum, rezervacijaId);
        return u;
    }

    
   private UgovorOKupoprodaji jeIzabranUgovor() {
    int ugovorId = 0;
    int izabranUgovorIndex = tblUgovor.getSelectedRow();
    
    if (izabranUgovorIndex >= 0) {
        DefaultTableModel model = (DefaultTableModel) tblUgovor.getModel();
        Object izabraniId = model.getValueAt(izabranUgovorIndex, 0);
        
        try {
            ugovorId = Integer.parseInt(izabraniId.toString());
        } catch (NumberFormatException e) {
            System.err.println("Greska pri parsiranju ugovora id: " + e.getMessage());
        }
    }
    
    UgovorOKupoprodaji u = new UgovorOKupoprodaji();
    u.setUgovorOKupoprodajiID(ugovorId);
    return u;
}

private void popuniFormuIzabranimUgovorom(UgovorOKupoprodaji u) throws Exception {
    if (u != null) {
        txtBrZahteva.setText(String.valueOf(u.getUgovorOKupoprodajiID()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        if (u.getDatumPotpisivanja()!= null) {
            txtDatum.setText(dateFormat.format(u.getDatumPotpisivanja()));
            originalDatum = u.getDatumPotpisivanja();
        } else {
            txtDatum.setText(""); 
        }

        if (u.getRezervacijaID()!= 0) {
            List<Rezervacija> rezervacija = Controller.getInstance().searchRezervacije("REZERVACIJAID='" + u.getRezervacijaID()+ "'");
            if (!rezervacija.isEmpty()) {
                cmbRezervacija.setSelectedItem(String.valueOf(rezervacija.get(0).getRezervacijaID()));
            }
        }
    }
}
 private void sacuvajOriginalneVrednosti(JTable table) {
        DefaultTableModel model = (DefaultTableModel) tblUgovor.getModel();

         for (int i = 0; i < model.getRowCount(); i++) {
            int ugovorId = (Integer) model.getValueAt(i, 0);
            String datum = (String) model.getValueAt(i, 1).toString();
            int rezervacijaId = (Integer) model.getValueAt(i, 2);
            originalneVrednostiUgovora.put(i, new String[]{String.valueOf(ugovorId), datum, String.valueOf(rezervacijaId)});
        }
    }


     private void setUpTableListener() {
        tblUgovor.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                try {
                    UgovorOKupoprodaji izabraniUgovor= jeIzabranUgovor();
                    pronadjeniUgovori = Controller.getInstance().searchUgovoriOKupoprodaji("UGOVOROKUPOPRODAJIID='" + String.valueOf(izabraniUgovor.getUgovorOKupoprodajiID()) + "'");
                    
                    if (pronadjeniUgovori != null && !pronadjeniUgovori.isEmpty()) {
                        izabraniUgovor = pronadjeniUgovori.get(0);
                    }
                    popuniFormuIzabranimUgovorom(izabraniUgovor);
                } catch (Exception ex) {
                    Logger.getLogger(formUgovorKupoprodaja.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    private void rbDP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDP3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbDP3ActionPerformed

    private void btnUcitajUgovoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUcitajUgovoreActionPerformed
        try {
            if (rbAll.isSelected()) {
                ugovori = Controller.getInstance().loadSveUgovoreOKupoprodaji();
            }
            if (rbDP1.isSelected()) {
                ugovori = Controller.getInstance().getParticijeUgovori("PARTITION (DP1)");
            }
            if (rbDP2.isSelected()) {
                ugovori = Controller.getInstance().getParticijeUgovori("PARTITION (DP2)");
            }
            if (rbDP3.isSelected()) {
                ugovori = Controller.getInstance().getParticijeUgovori("PARTITION (DP3)");
            }
            if (rbDP4.isSelected()) {
                ugovori = Controller.getInstance().getParticijeUgovori("PARTITION (DP4)");
            }
            ucitajUgovore(true);
        } catch (Exception ex) {
            Logger.getLogger(formUgovorKupoprodaja.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUcitajUgovoreActionPerformed

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
       try {
            UgovorOKupoprodaji u = preuzmiPodatkeZaUgovor();
            Controller.getInstance().insertUgovorOKupoprodaji(u);
            ucitajUgovore(false);
        } catch (Exception ex) {
            Logger.getLogger(formUgovorKupoprodaja.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSacuvajActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
     try {
        UgovorOKupoprodaji u = preuzmiPodatkeZaUgovor();
        int izabraniZahtevIndex = tblUgovor.getSelectedRow();
        
        if (izabraniZahtevIndex >= 0) {
            Date noviDatum = new SimpleDateFormat("dd-MM-yyyy").parse(txtDatum.getText());  
            
            if (!noviDatum.equals(originalDatum)) {
                throw new Exception("Datum se ne moze promeniti.");
            }
        }

        Controller.getInstance().updateUgovorOKupoprodaji(u);
        ucitajUgovore(false);  
        
    } catch (Exception ex) {
        java.util.logging.Logger.getLogger(formUgovorKupoprodaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);

        int izabraniUgovorIndex = tblUgovor.getSelectedRow();
        if (izabraniUgovorIndex >= 0) {
            tblUgovor.setRowSelectionInterval(izabraniUgovorIndex, izabraniUgovorIndex);
        }
    }
    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void rbAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAllActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbAllActionPerformed

    private void rbDP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbDP1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(formUgovorKupoprodaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formUgovorKupoprodaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formUgovorKupoprodaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formUgovorKupoprodaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new formUgovorKupoprodaja().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(formUgovorKupoprodaja.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JButton btnUcitajUgovore;
    private javax.swing.JComboBox<String> cmbRezervacija;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbAll;
    private javax.swing.JRadioButton rbDP1;
    private javax.swing.JRadioButton rbDP2;
    private javax.swing.JRadioButton rbDP3;
    private javax.swing.JRadioButton rbDP4;
    private javax.swing.JTable tblUgovor;
    private javax.swing.JTextField txtBrZahteva;
    private javax.swing.JTextField txtDatum;
    // End of variables declaration//GEN-END:variables
}
