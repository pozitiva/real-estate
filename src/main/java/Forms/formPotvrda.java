package Forms;

import Controller.Controller;
import domain.object.entities.JavniBeleznik;
import domain.object.entities.Potvrda;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class formPotvrda extends javax.swing.JFrame {

    List<Potvrda> potvrde = new LinkedList<>();
    List<Potvrda> pronadjenaPotvrda = new LinkedList<>();
    List<JavniBeleznik> javniBeleznici = new LinkedList<>();
    List<JavniBeleznik> pronadjeniJavniBeleznici = new LinkedList<>();
    private final HashMap<Integer, String[]> originalneVrednostiBeleznika = new HashMap<>();

    public formPotvrda() throws Exception {
        initComponents();
        setTitle("Potvrde");
        setLocationRelativeTo(this);
        ucitajPodatkeUFormu();
        setUpTableListener();
        sacuvajOriginalneVrednosti(tblPotvrde);
    }

    private void ucitajPodatkeUFormu() throws Exception {
        
        javniBeleznici = Controller.getInstance().loadSveJavneBeleznike();

        ucitajPotvrde();
    }

    private void ucitajPotvrde() throws Exception {        
        DefaultTableModel modelPotvrde = (DefaultTableModel) tblPotvrde.getModel();
        modelPotvrde.setRowCount(0);
        
        potvrde = Controller.getInstance().loadSvePotvrde();

        for (Potvrda p : potvrde) {
            modelPotvrde.addRow(new Object[]{p.getPotvrdaID(), p.getBeleznikID(), p.getImePrezime()});
        }
    }

    private Potvrda jeIzabranaPotvrda() {
        int potvrdaID = 0;
        int beleznikID=0;
        String imePrezime= null;
        
        int izabranaPotvrdaIndex = tblPotvrde.getSelectedRow();
        if (izabranaPotvrdaIndex >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblPotvrde.getModel();

            potvrdaID = (Integer) model.getValueAt(izabranaPotvrdaIndex, 0);
            beleznikID= (Integer) model.getValueAt(izabranaPotvrdaIndex, 1);
            imePrezime = (String) model.getValueAt(izabranaPotvrdaIndex, 2);
        }
        Potvrda p = new Potvrda();
        p.setPotvrdaID(potvrdaID);
        p.setBeleznikID(beleznikID);
        p.setImePrezime(imePrezime);
        return p;
    }
    

    private void popuniFormuIzabranomPotvrdom(Potvrda p) {
        if (p != null) {
            txtPotvrdaID.setText(String.valueOf(p.getPotvrdaID()));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            txtDatumPotvrde.setText(dateFormat.format(p.getDatumPotvrde()));
            txtOpu.setText(String.valueOf(p.getOPU()));
            txtUgovorOKupoprodajiID.setText(String.valueOf(p.getUgovorOKupoprodajiID()));
            txtBeleznikID.setText(String.valueOf(p.getBeleznikID()));
            txtImePrezime.setText(String.valueOf(p.getImePrezime()));
        }
    }

    private void setUpTableListener() {
        tblPotvrde.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                try {
                    Potvrda izabranaPotvrda = jeIzabranaPotvrda();
                    pronadjenaPotvrda = Controller.getInstance().searchPotvrde("POTVRDAID='" + String.valueOf(izabranaPotvrda.getPotvrdaID()) + "'");
                    
                    if (pronadjenaPotvrda != null && !pronadjenaPotvrda.isEmpty()) {
                        izabranaPotvrda = pronadjenaPotvrda.get(0);
                    }
                    
                    popuniFormuIzabranomPotvrdom(izabranaPotvrda);
                    
                    //originalneVrednostiBeleznika.clear();
                    //sacuvajOriginalneVrednosti(tblPotvrde);
                } catch (Exception ex) {
                    Logger.getLogger(formPotvrda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private Potvrda preuzmiPodatkeSaForme() {
        int potvrdaID = Integer.parseInt(txtPotvrdaID.getText());

        String rawDatumPotvrde = txtDatumPotvrde.getText();

        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date datumPotvrde = null;

        try {
            if (!rawDatumPotvrde.isEmpty()) {
                datumPotvrde = inputFormat.parse(rawDatumPotvrde);
            }
        } catch (ParseException e) {
            System.err.println("Neispravan format datuma: " + e.getMessage());
        }

        String opu = txtOpu.getText();
        int ugovorOKupoprodajiID = Integer.parseInt(txtUgovorOKupoprodajiID.getText());
        int beleznikID= Integer.parseInt(txtBeleznikID.getText());
        String imePrezime = "";
        
        return new Potvrda(potvrdaID, datumPotvrde, opu, ugovorOKupoprodajiID, beleznikID, imePrezime);
    }

    private void sacuvajOriginalneVrednosti(JTable table) {
        DefaultTableModel model = (DefaultTableModel) tblPotvrde.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            int potvrdaID= (Integer) model.getValueAt(i, 0);
            int beleznikID = (Integer) model.getValueAt(i, 1);
            String imePrezime = (String) model.getValueAt(i, 2);

            originalneVrednostiBeleznika.put(i, new String[]{String.valueOf(potvrdaID), String.valueOf(beleznikID), imePrezime});
        }
    }

    public String generisiSetKlauzuPotvrda(JTable table, int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) tblPotvrde.getModel();
        StringBuilder setClause = new StringBuilder(" ");

        Integer beleznikID = (Integer) model.getValueAt(selectedRow, 1);
        String imePrezime = (String) model.getValueAt(selectedRow, 2);

        String[] original = originalneVrednostiBeleznika.get(selectedRow);
        
        String originalBeleznikID = original[1];
        String beleznikIDString = beleznikID.toString();
        
        String originalImePrezime = original[2];

        boolean needComma = false;

        if (!beleznikIDString.equals(originalBeleznikID)) {
            setClause.append("BELEZNIKID = '").append(beleznikIDString).append("'");
            needComma = true;
        }
        if (!imePrezime.equals(originalImePrezime)) {
            if (needComma) {
                setClause.append(", ");
            }
            setClause.append("IMEPREZIME = '").append(imePrezime).append("'");
        }

        return setClause.toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbGroupDoznake = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPotvrde = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPotvrdaID = new javax.swing.JTextField();
        txtOpu = new javax.swing.JTextField();
        txtImePrezime = new javax.swing.JTextField();
        txtDatumPotvrde = new javax.swing.JTextField();
        txtUgovorOKupoprodajiID = new javax.swing.JTextField();
        btnSacuvaj = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();
        txtBeleznikID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Potvrda");

        tblPotvrde.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Potvrda ID", "Beleznik ID", "Ime i prezime"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPotvrde);

        jLabel2.setText("Datum potvrde");

        jLabel4.setText("Potvrda ID");

        jLabel7.setText("Beleznik ID");

        jLabel8.setText("OPU");

        jLabel9.setText("Ime i prezime");

        jLabel10.setText("Ugovor o kupoprodaji ID");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(77, 77, 77)
                                        .addComponent(txtPotvrdaID, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDatumPotvrde, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtOpu, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBeleznikID, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUgovorOKupoprodajiID, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtImePrezime, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIzmeni)
                    .addComponent(btnSacuvaj))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(462, 462, 462)
                .addComponent(jLabel1)
                .addGap(0, 433, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(txtUgovorOKupoprodajiID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtBeleznikID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnSacuvaj))))
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtImePrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(txtOpu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtPotvrdaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtDatumPotvrde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(41, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(btnIzmeni)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
        try {
            Potvrda potvrda = preuzmiPodatkeSaForme();

            Controller.getInstance().insertPotvrda(potvrda);

            ucitajPotvrde();
        } catch (Exception ex) {
            Logger.getLogger(formPotvrda.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnSacuvajActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
        try {
            Potvrda p = jeIzabranaPotvrda();

            String setClause = generisiSetKlauzuPotvrda(tblPotvrde, tblPotvrde.getSelectedRow());

            Controller.getInstance().updatePotvrda(p, setClause);
        } catch (Exception ex) {
            Logger.getLogger(formPotvrda.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            try {
                ucitajPotvrde();
            } catch (Exception ex) {
                Logger.getLogger(formPotvrda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnIzmeniActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.ButtonGroup rbGroupDoznake;
    private javax.swing.JTable tblPotvrde;
    private javax.swing.JTextField txtBeleznikID;
    private javax.swing.JTextField txtDatumPotvrde;
    private javax.swing.JTextField txtImePrezime;
    private javax.swing.JTextField txtOpu;
    private javax.swing.JTextField txtPotvrdaID;
    private javax.swing.JTextField txtUgovorOKupoprodajiID;
    // End of variables declaration//GEN-END:variables
}
