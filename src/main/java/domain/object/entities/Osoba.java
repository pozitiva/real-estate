package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Osoba extends DomainObject {

    private String JMBG;
    private String imePrezime;
    private String brojLK;
    private int adresaID;
    private int gradID;

    public Osoba() {
    }

    public Osoba(String JMBG, String imePrezime, String brojLK, int adresaID, int gradID) {
        this.JMBG = JMBG;
        this.imePrezime = imePrezime;
        this.brojLK = brojLK;
        this.adresaID = adresaID;
        this.gradID = gradID;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getBrojLK() {
        return brojLK;
    }

    public void setBrojLK(String brojLK) {
        this.brojLK = brojLK;
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
        return "OSOBA";
    }

    @Override
    public String getAllColumnNames() {
        return "JMBG, IMEPREZIME, BROJLK, ADRESAID, GRADID";
    }

    @Override
    public String getInsertColumnNames() {
        return "JMBG, IMEPREZIME, BROJLK, ADRESAID, GRADID";
    }

    @Override
    public String getColumnValues() {
        return String.format("'%s', '%s', '%s', %d, %d",
                JMBG,
                imePrezime,
                brojLK,
                adresaID,
                gradID);
    }

    @Override
    public String getUpdateClause() {
        return String.format("IMEPREZIME = '%s', BROJLK = '%s', ADRESAID = %d, GRADID = %d",
                imePrezime,
                brojLK,
                adresaID,
                gradID);
    }

    @Override
    public String getUpdateWhereClause() {
        return String.format("JMBG = '%s'", JMBG);
    }

     @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String getDeleteWhereClause() {
        return String.format("JMBG = '%s'", JMBG);
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> osobe = new ArrayList<>();
        while (rs.next()) {
            String JMBG = rs.getString("JMBG");
            String imePrezime = rs.getString("IMEPREZIME");
            String brojLK = rs.getString("BROJLK");
            int adresaID = rs.getInt("ADRESAID");
            int gradID = rs.getInt("GRADID");

            osobe.add(new Osoba(JMBG, imePrezime, brojLK, adresaID, gradID));
        }
        return osobe;
    }

    @Override
    public String getOrderByColumn() {
        return "JMBG";
    }

   
}
