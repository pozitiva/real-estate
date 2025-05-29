package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JavniBeleznik extends DomainObject{

    private int beleznikID;
    private String imePrezime;
    private int adresaID;
    private int gradID;

    public JavniBeleznik() {
    }

    public JavniBeleznik(int beleznikID, String imePrezime, int adresaID, int gradID) {
        this.beleznikID = beleznikID;
        this.imePrezime = imePrezime;
        this.adresaID = adresaID;
        this.gradID = gradID;
    }

    public int getBeleznikID() {
        return beleznikID;
    }

    public void setBeleznikID(int beleznikID) {
        this.beleznikID = beleznikID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
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
        return "BELEZNIK";
    }

    @Override
    public String getAllColumnNames() {
        return "BELEZNIKID, IMEPREZIME, ADRESAID,GRADID";
    }

    @Override
    public String getInsertColumnNames() {
        return "BELEZNIKID, IMEPREZIME, ADRESAID,GRADID";
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, '%s', %d, %d",
                beleznikID,
                imePrezime,
                adresaID,
                gradID);
    }

    @Override
    public String getUpdateClause() {
       return String.format("BELEZNIKID= %d, IMEPREZIME= '%s', ADRESAID= %d, GRADID= %d",  
                beleznikID,
                imePrezime,
                adresaID,
                gradID);
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
        return String.format("BELEZNIKID= '%d'", this.getBeleznikID());
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> beleznici = new ArrayList<>();
        
        while(rs.next()){
            int beleznikID= rs.getInt("BELEZNIKID");
            String imePrezime = rs.getString("IMEPREZIME");
            int adresaID= rs.getInt("ADRESAID");
            int gradID= rs.getInt("GRADID");
                  
            beleznici.add(new JavniBeleznik(beleznikID, imePrezime, adresaID, gradID));
        }
        
        return beleznici;
    }

    @Override
    public String getOrderByColumn() {
        return "BELEZNIKID";
    }
    
}
