package Forms;

import Controller.Controller;
import domain.object.entities.Adresa;
import domain.object.entities.Grad;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class formGrad extends javax.swing.JFrame {

    List<Grad> gradovi = new LinkedList<>();
    List<Grad> pronadjeniGradovi = new LinkedList<>();
    List<Adresa> adrese = new LinkedList<>();
    List<Adresa> pronadjeneAdrese = new LinkedList<>();
    private final HashMap<Integer, String[]> originalneVrednostiAdrese = new HashMap<>();

    
    public formGrad() throws Exception {
        initComponents();
        setTitle("Grad");
        setLocationRelativeTo(this);
        ucitajPodatkeUFormu();
        setUpTableListenerGrad();
        setUpTableListenerAdresa();
    }

    private void ucitajPodatkeUFormu() throws Exception {
        ucitajGradove();
    }

    private void sacuvajOriginalneVrednosti(JTable table) {
        DefaultTableModel model = (DefaultTableModel) tblAdrese.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            int adresaID = (Integer) model.getValueAt(i, 0);
            int gradID = (Integer) model.getValueAt(i, 1);
            String ulicaBroj = (String) model.getValueAt(i, 2);
            String nazivGrada = (String) model.getValueAt(i, 3);

            originalneVrednostiAdrese.put(i, new String[]{String.valueOf(adresaID), String.valueOf(gradID), ulicaBroj, nazivGrada});
        }
    }

    private void ucitajGradove() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblGrad.getModel();

        gradovi = Controller.getInstance().loadSveGradove();

        for (Grad g : gradovi) {
            model.addRow(new Object[]{g.getGradID(), g.getNazivGrada()});
        }
    }

    private void ucitajAdrese() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblAdrese.getModel();

        adrese = Controller.getInstance().loadSveAdrese();

        for (Adresa a : adrese) {
            model.addRow(new Object[]{a.getAdresaID(), a.getGradID(), a.getUlicaIBroj(), a.getNazivGrada()});
        }
    }

    private Grad jeIzabranGrad() {
        int gradID = 0;
        String nazivGrada = null;

        int izabranGradIndex = tblGrad.getSelectedRow();
        if (izabranGradIndex >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblGrad.getModel();

            gradID = (Integer) model.getValueAt(izabranGradIndex, 0);
            nazivGrada = (String) model.getValueAt(izabranGradIndex, 1);
        }

        Grad g = new Grad();
        g.setGradID(gradID);
        g.setNazivGrada(nazivGrada);
        return g;
    }

    private Adresa jeIzabranaAdresa() {
        int adresaID = 0;
        int gradID = 0;
        String ulicaBroj = null;
        String nazivGrada = null;

        int izabranaAdresaIndex = tblAdrese.getSelectedRow();
        if (izabranaAdresaIndex >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblAdrese.getModel();
            adresaID = (Integer) model.getValueAt(izabranaAdresaIndex, 0);
            gradID = (Integer) model.getValueAt(izabranaAdresaIndex, 1);
            ulicaBroj = (String) model.getValueAt(izabranaAdresaIndex, 2);
            nazivGrada = (String) model.getValueAt(izabranaAdresaIndex, 3);
        }

        Adresa a = new Adresa();
        a.setAdresaID(adresaID);
        a.setGradID(gradID);
        a.setUlicaIBroj(ulicaBroj);
        a.setNazivGrada(nazivGrada);
        return a;
    }

    private void popuniTabeluAdresama(int gradID) throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblAdrese.getModel();
        model.setRowCount(0);

        adrese = Controller.getInstance().searchAdrese("GRADID='" + String.valueOf(gradID) + "'");

        for (Adresa a : adrese) {
            model.addRow(new Object[]{a.getAdresaID(), a.getGradID(), a.getUlicaIBroj(), a.getNazivGrada()});
        }
    }

//    private void popuniFormuAdresom(Adresa a) {
//        txtAdresaID.setText(String.valueOf(a.getAdresaID()));
//        txtUlicaBroj.setText(a.getUlicaIBroj());
//        //txtGrad.setText(a.getNazivGrada());
//    }

    private String generisiSetKlauzuAdrese(JTable table, int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        StringBuilder setClause = new StringBuilder(" ");

        Integer gradID = (Integer) model.getValueAt(selectedRow, 1);
        String ulicaBroj = (String) model.getValueAt(selectedRow, 2);
        String nazivGrada = (String) model.getValueAt(selectedRow, 3);
        
        String[] original = originalneVrednostiAdrese.get(selectedRow);
        
        String originalGradID = original[1];
        String gradIDString = gradID.toString();
        
        String originalUlicaBroj = original[2];
        String originalNazivGrada = original[3];
               
        boolean needComma = false;

        if(!gradIDString.equals(originalGradID)) {
            setClause.append("GRADID = '").append(gradIDString).append("'");
            needComma = true;
        }
        if(!ulicaBroj.equals(originalUlicaBroj)){
            setClause.append("ULICABROJ = '").append(ulicaBroj).append("'");
            needComma=true;
        }
        if(!nazivGrada.equals(originalNazivGrada)){
            if(needComma){
                setClause.append(", ");
            }
            setClause.append("NAZIVGRADA = '").append(nazivGrada).append("'");
        }

        return setClause.toString();
    }
    
    private void setUpTableListenerGrad() {
        tblGrad.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                try {
                    Grad izabranGrad = jeIzabranGrad();
                    pronadjeniGradovi = Controller.getInstance().searchGradovi("GRADID='" + String.valueOf(izabranGrad.getGradID()) + "'");
                    
                    if (pronadjeniGradovi != null && !pronadjeniGradovi.isEmpty()) {
                        izabranGrad = pronadjeniGradovi.get(0);
                    }
                    
                    popuniTabeluAdresama(izabranGrad.getGradID());
                    originalneVrednostiAdrese.clear();
                    sacuvajOriginalneVrednosti(tblAdrese);
                } catch (Exception ex) {
                    Logger.getLogger(formGrad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setUpTableListenerAdresa() {
        tblAdrese.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                try {
                    Adresa izabranaAdresa = jeIzabranaAdresa();
                    pronadjeneAdrese = Controller.getInstance().searchAdrese("ADRESAID='" + String.valueOf(izabranaAdresa.getAdresaID()) + "' AND GRADID='" + String.valueOf(izabranaAdresa.getGradID()) + "'");
                    
                    if (pronadjeneAdrese != null && !pronadjeneAdrese.isEmpty()) {
                        izabranaAdresa = pronadjeneAdrese.get(0);
                    }
                    
                    //popuniFormuAdresom(izabranaAdresa);
                } catch (Exception ex) {
                    Logger.getLogger(formGrad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private Adresa preuzmiPodatkeZaAdresu() {
        int adresaID = Integer.parseInt(txtAdresaID.getText());
        String ulicaBroj = txtUlicaBroj.getText();
        String nazivgRada = "";
        int gradID = jeIzabranGrad().getGradID();

        Adresa a = new Adresa(adresaID, gradID, ulicaBroj, nazivgRada);

        return a;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrad = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtAdresaID = new javax.swing.JTextField();
        txtUlicaBroj = new javax.swing.JTextField();
        btnIzmeniGrad = new javax.swing.JButton();
        btnSacuvaj = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAdrese = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Grad");

        tblGrad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GradID", "Naziv Grad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblGrad);

        jLabel2.setText("Adresa ID");

        jLabel3.setText("Ulica i broj");

        txtAdresaID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdresaIDActionPerformed(evt);
            }
        });

        btnIzmeniGrad.setText("Izmeni");
        btnIzmeniGrad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniGradActionPerformed(evt);
            }
        });

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

        btnObrisi.setText("Obrisi");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        tblAdrese.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Adresa ID", "Grad ID", "Ulica i broj", "Naziv grada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAdrese);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAdresaID, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUlicaBroj, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(141, 141, 141)
                        .addComponent(btnSacuvaj))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIzmeniGrad)
                            .addComponent(btnIzmeni)
                            .addComponent(btnObrisi))))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(327, 327, 327))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnIzmeniGrad)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAdresaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUlicaBroj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(btnSacuvaj)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addComponent(btnObrisi)
                        .addGap(36, 36, 36)
                        .addComponent(btnIzmeni)
                        .addGap(101, 101, 101))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
        try {
            Adresa a = preuzmiPodatkeZaAdresu();

            Controller.getInstance().insertAdresa(a);

            //Adresa adresa = jeIzabranaAdresa();

            popuniTabeluAdresama(a.getGradID());
        } catch (Exception ex) {
            Logger.getLogger(formGrad.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSacuvajActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        try {
            Adresa a = jeIzabranaAdresa();

            Controller.getInstance().deleteAdresa(a);

            popuniTabeluAdresama(tblGrad.getSelectedRow());
        } catch (Exception ex) {
            Logger.getLogger(formGrad.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
        try {
            Adresa a = jeIzabranaAdresa();
            String setClause = generisiSetKlauzuAdrese(tblAdrese, tblAdrese.getSelectedRow());
            
            Controller.getInstance().updateAdresa(a, setClause);
            //ucitajAdrese();
            popuniTabeluAdresama(a.getGradID());
        } catch (Exception ex) {
            Logger.getLogger(formGrad.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnIzmeniGradActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniGradActionPerformed
        try {
            Grad g = jeIzabranGrad();

            Controller.getInstance().updateGrad(g);

            popuniTabeluAdresama(g.getGradID());
        } catch (Exception ex) {
            Logger.getLogger(formGrad.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIzmeniGradActionPerformed

    private void txtAdresaIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdresaIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdresaIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnIzmeniGrad;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAdrese;
    private javax.swing.JTable tblGrad;
    private javax.swing.JTextField txtAdresaID;
    private javax.swing.JTextField txtUlicaBroj;
    // End of variables declaration//GEN-END:variables
}
