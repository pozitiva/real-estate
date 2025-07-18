package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Adresa extends DomainObject{

    private int adresaID;
    private int gradID;
    private String ulicaBroj;
    private String nazivGrada;

    public Adresa() {
    }

    public Adresa(int adresaID, int gradID, String ulicaIBroj, String nazivGrada) {
        this.adresaID = adresaID;
        this.gradID = gradID;
        this.ulicaBroj = ulicaIBroj;
        this.nazivGrada = nazivGrada;
    }

    public int getAdresaID() {
        return adresaID;
    }

    public void setAdresaID(int adresaID) {
        this.adresaID = adresaID;
    }

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int gradID) {
        this.gradID = gradID;
    }

    public String getUlicaIBroj() {
        return ulicaBroj;
    }

    public void setUlicaIBroj(String ulicaIBroj) {
        this.ulicaBroj = ulicaIBroj;
    }

    public String getNazivGrada() {
        return nazivGrada;
    }

    public void setNazivGrada(String nazivGrada) {
        this.nazivGrada = nazivGrada;
    }
    
    @Override
    public String getTableName() {
       return "IVA.ADRESA";
    }

    @Override
    public String getAllColumnNames() {
        return "ADRESAID, GRADID, ULICABROJ, NAZIVGRADA";
    }

    @Override
    public String getInsertColumnNames() {
        return "ADRESAID, GRADID, ULICABROJ, NAZIVGRADA";
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, %d, '%s', '%s'", 
               this.adresaID,
               this.gradID,
               this.ulicaBroj,
               this.nazivGrada);
    }

    @Override
    public String getUpdateClause() {
        return String.format("ADRESAID = %d, GRADID = %d, ULICABROJ='%s', NAZIVGRADA = '%s'", 
               this.adresaID,
               this.gradID,
               this.ulicaBroj,
               this.nazivGrada);
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
     return String.format("ADRESAID = %d", this.getAdresaID());
    }

    @Override
    public String getDeleteWhereClause() {
        return String.format("ADRESAID = %d AND GRADID = %d", this.getAdresaID(), this.getGradID());
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> adrese = new ArrayList<>();
        
        while(rs.next()){
            int adresaID= rs.getInt("ADRESAID");
            int gradID = rs.getInt("GRADID");
            String ulicaBroj = rs.getString("ULICABROJ");
            String nazivGrada= rs.getString("NAZIVGRADA");
            
            adrese.add(new Adresa(adresaID, gradID, ulicaBroj, nazivGrada));
        }
        
        return adrese;
    }

    @Override
    public String getOrderByColumn() {
        return "GRADID";
    }
    
    
    
}
