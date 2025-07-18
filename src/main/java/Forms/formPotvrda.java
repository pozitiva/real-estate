package Forms;

import Controller.Controller;
import domain.object.entities.Adresa;
import domain.object.entities.Grad;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class formPotvrda extends javax.swing.JFrame {

    List<Potvrda> potvrde = new LinkedList<>();
    List<Potvrda> pronadjenaPotvrda = new LinkedList<>();
    List<JavniBeleznik> javniBeleznici = new LinkedList<>();
    List<JavniBeleznik> pronadjeniJavniBeleznici = new LinkedList<>();
    List<Adresa> adrese = new LinkedList<>();
    List<Adresa> pronadjeneAdrese = new LinkedList<>();
    List<Grad> pronadjeniGradovi = new LinkedList<>();
    private final HashMap<Integer, String[]> originalneVrednostiBeleznika = new HashMap<>();

    public formPotvrda() throws Exception {
        initComponents();
        setTitle("Potvrde");
        setLocationRelativeTo(this);
        //ucitajPodatkeUFormu();
        ucitajPotvrde();
        ucitajBeleznike();
        setUpTableListenerPotvrda();
        setUpTableListenerBeleznik();
        sacuvajOriginalneVrednosti(tblPotvrde);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void ucitajBeleznike() throws Exception {
       javniBeleznici = Controller.getInstance().loadSveJavneBeleznike();
       DefaultTableModel modelBeleznici = (DefaultTableModel) tblBeleznik.getModel();

       modelBeleznici.setRowCount(0); 
        for (JavniBeleznik j: javniBeleznici) {
            modelBeleznici.addRow(new Object[]{j.getBeleznikID(), j.getImePrezime(), j.getAdresaID()});
        }
        
        adrese = Controller.getInstance().loadSveAdrese();
        if (cmbAdresa != null) {
            cmbAdresa.removeAllItems();
            for (Adresa a : adrese) {
                cmbAdresa.addItem(String.valueOf(a.getUlicaIBroj()));
            }
        } else {
            System.err.println("ComboBox (cmbAdresa) je null.");
        }
          
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
    

    private void popuniFormuIzabranimBeleznikom(JavniBeleznik j) throws Exception {
        if (j != null) {
            txtBeleznikID.setText(String.valueOf(j.getBeleznikID()));
            txtImePrezime.setText(String.valueOf(j.getImePrezime()));
            
                if(j.getAdresaID() != 0){
                    List<Adresa> adresa = Controller.getInstance().searchAdrese("ADRESAID= '"+ j.getAdresaID()+ "'");
                    if(!adresa.isEmpty()){
                        cmbAdresa.setSelectedItem(adresa.get(0).toString());
                    }
                }
                
            pronadjeneAdrese= Controller.getInstance().searchAdrese("ADRESAID = '"+ String.valueOf(j.getAdresaID()+ "'"));
        }
    }
    
    private void popuniFormuIzabranomPotvrdom(Potvrda p) {
        if (p != null) {
            txtPotvrdaID.setText(String.valueOf(p.getPotvrdaID()));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            txtDatumPotvrde.setText(dateFormat.format(p.getDatumPotvrde()));
            txtOpu.setText(String.valueOf(p.getOPU()));
            txtUgovorOKupoprodajiID.setText(String.valueOf(p.getUgovorOKupoprodajiID()));
            txtBeleznikIDPotvrda.setText(String.valueOf(p.getBeleznikID()));
            txtImePrezimePotvrde.setText(String.valueOf(p.getImePrezime()));
        }
    }

    private void setUpTableListenerBeleznik() {
        tblBeleznik.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                try {
                    JavniBeleznik b = jeIzabranBeleznik();
                    pronadjeniJavniBeleznici = Controller.getInstance().searchJavniBeleznici("BELEZNIKID='" + String.valueOf(b.getBeleznikID()) + "'");
                    if (pronadjeniJavniBeleznici != null && !pronadjeniJavniBeleznici.isEmpty()) {
                        b = pronadjeniJavniBeleznici.get(0);
                    }
                    popuniFormuIzabranimBeleznikom(b);
                    originalneVrednostiBeleznika.clear();
                } catch (Exception ex) {
                    Logger.getLogger(formNekretnina.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    private JavniBeleznik jeIzabranBeleznik() {
        int beleznikId = 0;
        int izabranBeleznikIndex = tblBeleznik.getSelectedRow();
        if (izabranBeleznikIndex >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblBeleznik.getModel();
            Object izabraniID = model.getValueAt(izabranBeleznikIndex, 0);

            beleznikId = (Integer) izabraniID;
        }
        JavniBeleznik b = new JavniBeleznik();
        b.setBeleznikID(beleznikId);
        return b;            
  }
    private void setUpTableListenerPotvrda() {
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

    private JavniBeleznik preuzmiPodatkeZaBeleznika() throws Exception {
        int idBeleznika = Integer.parseInt(txtBeleznikID.getText());
        String imeIPrezime = txtImePrezime.getText();
        
        Object izabranaAdresa = cmbAdresa.getSelectedItem();
        pronadjeneAdrese = Controller.getInstance().searchAdrese("ULICABROJ='" + izabranaAdresa.toString() + "'");
        int adresaID =  pronadjeneAdrese.get(0).getAdresaID();
       
        pronadjeniGradovi=Controller.getInstance().searchGradovi("GRADID= '" + pronadjeneAdrese.get(0).getGradID()+ "'");
        int gradID = pronadjeniGradovi.get(0).getGradID();
        
        JavniBeleznik b = new JavniBeleznik(idBeleznika, imeIPrezime, adresaID, gradID);
        return b;
    }
    
    
    private Potvrda preuzmiPodatkeZaPotvrdu() {
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
        int beleznikID= Integer.parseInt(txtBeleznikIDPotvrda.getText());
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

     private void resetujPoljaZaBeleznika() {
        txtBeleznikID.setText("");
        txtImePrezime.setText("");
        cmbAdresa.setSelectedIndex(0); 
    }
    public String generisiSetKlauzuPotvrda(JTable table, int selectedRow) {
        //DefaultTableModel model = (DefaultTableModel) tblPotvrde.getModel();
        StringBuilder setClause = new StringBuilder(" ");

        Integer beleznikID = Integer.valueOf(txtBeleznikIDPotvrda.getText());
        String imePrezime = String.valueOf(txtImePrezimePotvrde.getText());

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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPotvrde1 = new javax.swing.JTable();
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
        txtImePrezimePotvrde = new javax.swing.JTextField();
        txtDatumPotvrde = new javax.swing.JTextField();
        txtUgovorOKupoprodajiID = new javax.swing.JTextField();
        btnSacuvajPotvrdu = new javax.swing.JButton();
        btnIzmeniPotvrdu = new javax.swing.JButton();
        txtBeleznikIDPotvrda = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBeleznik = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtBeleznikID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtImePrezime = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        cmbAdresa = new javax.swing.JComboBox<>();
        btnSacuvajBeleznika = new javax.swing.JButton();
        btnIzmeniBeleznika = new javax.swing.JButton();

        tblPotvrde1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblPotvrde1);
        if (tblPotvrde1.getColumnModel().getColumnCount() > 0) {
            tblPotvrde1.getColumnModel().getColumn(2).setResizable(false);
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPotvrde);
        if (tblPotvrde.getColumnModel().getColumnCount() > 0) {
            tblPotvrde.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel2.setText("Datum potvrde");

        jLabel4.setText("Potvrda ID");

        jLabel7.setText("Beleznik ID");

        jLabel8.setText("OPU");

        jLabel9.setText("Ime i prezime");

        jLabel10.setText("Ugovor o kupoprodaji ID");

        btnSacuvajPotvrdu.setText("Sacuvaj");
        btnSacuvajPotvrdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajPotvrduActionPerformed(evt);
            }
        });

        btnIzmeniPotvrdu.setText("Izmeni");
        btnIzmeniPotvrdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniPotvrduActionPerformed(evt);
            }
        });

        tblBeleznik.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Beleznik ID", "Ime i prezime "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class
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
        jScrollPane3.setViewportView(tblBeleznik);

        jLabel5.setText("Beleznik ID");

        jLabel3.setText("Ime i prezime");

        txtImePrezime.setText("dd-MM-yyyy");
        txtImePrezime.setToolTipText("");

        jLabel20.setText("Adresa ");

        cmbAdresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSacuvajBeleznika.setText("Sacuvaj");
        btnSacuvajBeleznika.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajBeleznikaActionPerformed(evt);
            }
        });

        btnIzmeniBeleznika.setText("Izmeni");
        btnIzmeniBeleznika.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniBeleznikaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(77, 77, 77)
                                        .addComponent(txtBeleznikID, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtImePrezime, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(117, 117, 117)
                                .addComponent(jLabel20)
                                .addGap(48, 48, 48)
                                .addComponent(cmbAdresa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
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
                                            .addComponent(txtBeleznikIDPotvrda, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUgovorOKupoprodajiID, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtImePrezimePotvrde, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIzmeniPotvrdu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSacuvajPotvrdu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIzmeniBeleznika, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSacuvajBeleznika, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(txtUgovorOKupoprodajiID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSacuvajPotvrdu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIzmeniPotvrdu)
                                .addGap(1, 1, 1)
                                .addComponent(txtBeleznikIDPotvrda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtImePrezimePotvrde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(31, 31, 31)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(txtBeleznikID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(cmbAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtImePrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSacuvajBeleznika)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIzmeniBeleznika)
                        .addGap(29, 29, 29))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSacuvajPotvrduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajPotvrduActionPerformed
        try {
            Potvrda potvrda = preuzmiPodatkeZaPotvrdu();

            Controller.getInstance().insertPotvrda(potvrda);

            ucitajPotvrde();
        } catch (Exception ex) {
            Logger.getLogger(formPotvrda.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnSacuvajPotvrduActionPerformed

    private void btnIzmeniPotvrduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniPotvrduActionPerformed
        try {
            Potvrda p = preuzmiPodatkeZaPotvrdu();
            String setClause = generisiSetKlauzuPotvrda(tblPotvrde, tblPotvrde.getSelectedRow());
            Controller.getInstance().updatePotvrda(p, setClause);
            ucitajPotvrde();
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
    }//GEN-LAST:event_btnIzmeniPotvrduActionPerformed

    private void btnSacuvajBeleznikaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajBeleznikaActionPerformed
        try {
            JavniBeleznik beleznik = preuzmiPodatkeZaBeleznika();
            Controller.getInstance().insertJavniBeleznik(beleznik);
            ucitajBeleznike();
            resetujPoljaZaBeleznika();
        } catch (Exception ex) {
            Logger.getLogger(formNekretnina.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }    
    }//GEN-LAST:event_btnSacuvajBeleznikaActionPerformed

    private void btnIzmeniBeleznikaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniBeleznikaActionPerformed
         try {
            JavniBeleznik b = preuzmiPodatkeZaBeleznika();
            Controller.getInstance().updateJavniBeleznik(b);
            ucitajBeleznike();
            ucitajPotvrde();
            resetujPoljaZaBeleznika();
        } catch (Exception ex) {
            Logger.getLogger(formNekretnina.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Doslo je do greske: " + ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }  
    }//GEN-LAST:event_btnIzmeniBeleznikaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeniBeleznika;
    private javax.swing.JButton btnIzmeniPotvrdu;
    private javax.swing.JButton btnSacuvajBeleznika;
    private javax.swing.JButton btnSacuvajPotvrdu;
    private javax.swing.JComboBox<String> cmbAdresa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.ButtonGroup rbGroupDoznake;
    private javax.swing.JTable tblBeleznik;
    private javax.swing.JTable tblPotvrde;
    private javax.swing.JTable tblPotvrde1;
    private javax.swing.JTextField txtBeleznikID;
    private javax.swing.JTextField txtBeleznikIDPotvrda;
    private javax.swing.JTextField txtDatumPotvrde;
    private javax.swing.JTextField txtImePrezime;
    private javax.swing.JTextField txtImePrezimePotvrde;
    private javax.swing.JTextField txtOpu;
    private javax.swing.JTextField txtPotvrdaID;
    private javax.swing.JTextField txtUgovorOKupoprodajiID;
    // End of variables declaration//GEN-END:variables
}
