package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StavkaRezervacije extends DomainObject {

    private int stavkaRezervacijeID;
    private int rezervacijaID;
    private String opis;
    private float zaIsplatu;

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(int stavkaRezervacijeID, int rezervacijaID, String opis, float zaIsplatu) {
        this.stavkaRezervacijeID = stavkaRezervacijeID;
        this.rezervacijaID = rezervacijaID;
        this.opis = opis;
        this.zaIsplatu = zaIsplatu;
    }

    public int getStavkaRezervacijeID() {
        return stavkaRezervacijeID;
    }

    public void setStavkaRezervacijeID(int stavkaRezervacijeID) {
        this.stavkaRezervacijeID = stavkaRezervacijeID;
    }

    public int getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public float getZaIsplatu() {
        return zaIsplatu;
    }

    public void setZaIsplatu(float zaIsplatu) {
        this.zaIsplatu = zaIsplatu;
    }
    
    @Override
    public String getTableName() {
        return "STAVKAREZERVACIJE";
    }

    @Override
    public String getAllColumnNames() {
        return "STAVKAREZERVACIJEID, REZERVACIJAID, OPIS, ZAISPLATU";
    }

    @Override
    public String getInsertColumnNames() {
       return "STAVKAREZERVACIJEID, REZERVACIJAID, OPIS, ZAISPLATU";
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, %d, '%s', %.2f",
             stavkaRezervacijeID,
             rezervacijaID,
             opis,
             zaIsplatu);        
    }

    @Override
    public String getUpdateClause() {
    return String.format("STAVKAREZERVACIJEID = %d, REZERVACIJAID = %d, OPIS = '%s', ZAISPLATU = %.2f",
             stavkaRezervacijeID,
             rezervacijaID,
             opis,
             zaIsplatu); 
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
        return String.format("STAVKAREZERVACIJAID = %d AND REZERVACIJAID= %d", this.getStavkaRezervacijeID(), this.getRezervacijaID());
    }

    @Override
    public String getDeleteWhereClause() {
        return String.format("STAVKAREZERVACIJAID = %d AND REZERVACIJAID= %d", this.getStavkaRezervacijeID(), this.getRezervacijaID());
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> stavkeRezervacije = new ArrayList<>();
        
        while(rs.next()){
        int stavkaRezervacijeID= rs.getInt("STAVKAREZERVACIJEID");
        int rezervacijaID= rs.getInt("REZERVACIJAID");
        String opis = rs.getString("OPIS");
        float zaIsplatu = rs.getFloat("ZAISPLATU");
        
        stavkeRezervacije.add(new StavkaRezervacije(stavkaRezervacijeID, rezervacijaID, opis, zaIsplatu));
        }
        
        return stavkeRezervacije;
    }

    @Override
    public String getOrderByColumn() {
        return "REZERVACIJAID";
    }
    
}
