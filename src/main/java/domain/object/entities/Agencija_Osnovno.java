package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Agencija_Osnovno extends DomainObject{
    
    private String maticniBroj;
    private String naziv;
    private String pib;

    public Agencija_Osnovno() {
    }

    public Agencija_Osnovno(String maticniBroj, String naziv, String pib) {
        this.maticniBroj = maticniBroj;
        this.naziv = naziv;
        this.pib = pib;
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
    
   
    @Override
    public String getTableName() {
        return "AGENCIJA_OSNOVNO";
    }

    @Override
    public String getAllColumnNames() {
        return "MATICNIBROJ, NAZIV, PIB";
    }

    @Override
    public String getInsertColumnNames() {
        return "MATICNIBROJ, NAZIV, PIB";    
    }

    @Override
    public String getColumnValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> agencije = new ArrayList<>();
        
        while(rs.next()){
            String maticniBroj = rs.getString("MATICNIBROJ");
            String naziv = rs.getString("NAZIV");
            String pib = rs.getString("PIB");
            
            agencije.add(new Agencija_Osnovno(maticniBroj, naziv, pib));
        } 
        return agencije;
    }

    @Override
    public String getOrderByColumn() {
       return "MATICNIBROJ";
    }
    
}
