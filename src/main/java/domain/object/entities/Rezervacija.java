package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Rezervacija extends DomainObject{
    
    private int rezervacijaID;
    private Date datumRezervacije;
    private float kupoprodajnaCena;
    private int ugovorOPosredovanjuID;
    private float ukupnoZaIsplatu;

    public Rezervacija() {
    }

    public Rezervacija(int rezervacijaID, Date datumRezervacije, float kupoprodajnaCena, int ugovorOPosredovanjuID, float ukupnoZaIsplatu) {
        this.rezervacijaID = rezervacijaID;
        this.datumRezervacije = datumRezervacije;
        this.kupoprodajnaCena = kupoprodajnaCena;
        this.ugovorOPosredovanjuID = ugovorOPosredovanjuID;
        this.ukupnoZaIsplatu = ukupnoZaIsplatu;
    }

    public int getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public Date getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(Date datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public float getKupoprodajnaCena() {
        return kupoprodajnaCena;
    }

    public void setKupoprodajnaCena(float kupoprodajnaCena) {
        this.kupoprodajnaCena = kupoprodajnaCena;
    }

    public int getUgovorOPosredovanjuID() {
        return ugovorOPosredovanjuID;
    }

    public void setUgovorOPosredovanjuID(int ugovorOPosredovanjuID) {
        this.ugovorOPosredovanjuID = ugovorOPosredovanjuID;
    }

    public float getUkupnoZaIsplatu() {
        return ukupnoZaIsplatu;
    }

    public void setUkupnoZaIsplatu(float ukupnoZaIsplatu) {
        this.ukupnoZaIsplatu = ukupnoZaIsplatu;
    }

    @Override
    public String getTableName() {
        return "IVA.REZERVACIJA";
    }

    @Override
    public String getAllColumnNames() {
        return "REZERVACIJAID, DATUMREZERVACIJE, KUPOPRODAJNACENA, UGOVOROPOSREDOVANJUID, UKUPNOZAISPLATU";
    }

    @Override
    public String getInsertColumnNames() {
        return "REZERVACIJAID, DATUMREZERVACIJE, KUPOPRODAJNACENA, UGOVOROPOSREDOVANJUID, UKUPNOZAISPLATU";
    }

    @Override
    public String getColumnValues() {
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
      String datumRezervacijeStr = datumRezervacije != null ? "TO_DATE('" + sdf.format(datumRezervacije) + "' 'dd-MMM-yyy'" : "NULL";
      
      return String.format("%d, '%s', %.2f, %d, %.2f", 
              rezervacijaID,
              datumRezervacijeStr,
              kupoprodajnaCena,
              ugovorOPosredovanjuID,
              ukupnoZaIsplatu);
    }

    @Override
    public String getUpdateClause() {
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
      String datumRezervacijeStr = datumRezervacije != null ? "TO_DATE('" + sdf.format(datumRezervacije) + "' 'dd-MMM-yyy'" : "NULL";
      
     return String.format("REZERVACIJAID = %d, DATUMREZERVACIJE = '%s', KUPOPRODAJNACENA = %.2f, UGOVOROPOSREDOVANJUID = %d, UKUPNOZAISPLATU = %.2f", 
              rezervacijaID,
              datumRezervacijeStr,
              kupoprodajnaCena,
              ugovorOPosredovanjuID,
              ukupnoZaIsplatu);
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDeleteWhereClause() {
        return String.format("REZERVACIJAID = %d",this.getRezervacijaID());
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> rezervacije = new ArrayList<>();
        
        while(rs.next()){
        int rezervacijaID= rs.getInt("REZERVACIJAID");
        Date datumRezervacije = rs.getDate("DATUMREZERVACIJE");
        float kupoprodajnaCena = rs.getFloat("KUPOPRODAJNACENA");
        int ugovorOPosredovanjuID= rs.getInt("UGOVOROPOSREDOVANJUID");
        float ukupnoZaIsplatu = rs.getFloat("UKUPNOZAISPLATU");
        
        rezervacije.add(new Rezervacija(rezervacijaID, datumRezervacije, kupoprodajnaCena, ugovorOPosredovanjuID, ukupnoZaIsplatu));
        }
        
        return rezervacije;
    }

    @Override
    public String getOrderByColumn() {
        return "REZERVACIJAID";
    }
    
}
