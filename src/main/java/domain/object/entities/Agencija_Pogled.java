package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Agencija_Pogled extends DomainObject {

    private String maticniBroj;
    private String naziv;
    private String pib;
    private String vlasnik;
    private int adresaID;
    private int gradID;

    public Agencija_Pogled() {
    }

    public Agencija_Pogled(String maticniBroj, String naziv, String pib, String vlasnik, int adresaID, int gradID) {
        this.maticniBroj = maticniBroj;
        this.naziv = naziv;
        this.pib = pib;
        this.vlasnik = vlasnik;
        this.adresaID = adresaID;
        this.gradID = gradID;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(String vlasnik) {
        this.vlasnik = vlasnik;
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
    
    @Override
    public String getTableName() {
        return "AGENCIJA_POGLED";
    }

    @Override
    public String getAllColumnNames() {
        return "MATICNIBROJ, NAZIV, PIB, VLASNIK, ADRESAID, GRADID";
    }

    @Override
    public String getInsertColumnNames() {
        return String.format("'%s', '%s', '%s','%s', %d, %d",
               maticniBroj,
               naziv,
               pib,
               vlasnik,
               adresaID,
               gradID);
    }

    @Override
    public String getColumnValues() {
    return String.format("MATICNIBROJ = '%s', NAZIV = '%s', PIB = '%s',VLASNIK = '%s', ADRESAID = %d, GRADID = %d",
               maticniBroj,
               naziv,
               pib,
               vlasnik,
               adresaID,
               gradID);}

    @Override
    public String getUpdateClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
        return String.format("MATICNIBROJ = '%s'", this.getMaticniBroj());
    }

    @Override
    public String getDeleteWhereClause() {
        return String.format("MATICNIBROJ = '%s'", this.getMaticniBroj());
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> agencije = new ArrayList<>(); 
        
        while(rs.next()){
            String maticniBroj=rs.getString("MATICNIBROJ");
            String naziv = rs.getString("NAZIV");
            String pib = rs.getString("PIB");
            String vlasnik = rs.getString("VLASNIK");
            int adresaID= rs.getInt("ADRESAID");
            int gradID= rs.getInt("GRADID");
            
            agencije.add(new Agencija_Pogled(maticniBroj, naziv, pib,vlasnik, adresaID, gradID));
        }
    
        return agencije;
    }

    @Override
    public String getOrderByColumn() {
        return "MATICNIBROJ";
    }
    
}
