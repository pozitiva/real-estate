package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LicnaKarta extends DomainObject {

    private String brojLK;
    private String policijskaStanica;
    private Date datumIzdavanja;
    private Date datumIsteka;

    public LicnaKarta() {
    }

    public LicnaKarta(String brojLK, String policijskaStanica, Date datumIzdavanja, Date datumIsteka) {
        this.brojLK = brojLK;
        this.policijskaStanica = policijskaStanica;
        this.datumIzdavanja = datumIzdavanja;
        this.datumIsteka = datumIsteka;
    }

    public String getBrojLK() {
        return brojLK;
    }

    public void setBrojLK(String brojLK) {
        this.brojLK = brojLK;
    }

    public String getPolicijskaStanica() {
        return policijskaStanica;
    }

    public void setPolicijskaStanica(String policijskaStanica) {
        this.policijskaStanica = policijskaStanica;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Date getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(Date datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    @Override
    public String getTableName() {
        return "LICNAKARTA";
    }

    @Override
    public String getAllColumnNames() {
        return "BROJLK, POLICIJSKASTANICA, DATUMIZDAVANJA, DATUMISTEKA";
    }

    @Override
    public String getInsertColumnNames() {
        return "BROJLK, POLICIJSKASTANICA, DATUMIZDAVANJA, DATUMISTEKA";
    }

    @Override
    public String getColumnValues() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

        String datumIzdavanjaStr = datumIzdavanja != null ? "TO_DATE('" + sdf.format(datumIzdavanja) + "', 'DD-MM-YY')" : "NULL";
        String datumIstekaStr = datumIsteka != null ? "TO_DATE('" + sdf.format(datumIsteka) + "', 'DD-MM-YY')" : "NULL";

        return String.format("'%s', '%s', %s, %s",
                brojLK,
                policijskaStanica,
                datumIzdavanjaStr,
                datumIstekaStr);
    }

    @Override
    public String getUpdateClause() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        
        String datumIzdavanjaStr = datumIzdavanja != null ? "TO_DATE('" + sdf.format(datumIzdavanja) + "', 'DD-MM-YY')" : "NULL";
        String datumIstekaStr = datumIsteka != null ? "TO_DATE('" + sdf.format(datumIsteka) + "', 'DD-MM-YY')" : "NULL";

        return String.format("POLICIJSKASTANICA = '%s', DATUMIZDAVANJA = %s, DATUMISTEKA = %s",
                policijskaStanica,
                datumIzdavanjaStr,
                datumIstekaStr);
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
        return String.format("BROJLK = '%s'", brojLK);
    }

    @Override
    public String getDeleteWhereClause() {
       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> licneKarte = new ArrayList<>();
        while (rs.next()) {
            String brojLK = rs.getString("BROJLK");
            String policijskaStanica = rs.getString("POLICIJSKASTANICA");
            Date datumIzdavanja = rs.getDate("DATUMIZDAVANJA");
            Date datumIsteka = rs.getDate("DATUMISTEKA");

            licneKarte.add(new LicnaKarta(brojLK, policijskaStanica, datumIzdavanja, datumIsteka));
        }
        return licneKarte;
    }

    @Override
    public String getOrderByColumn() {
        return "BROJLK";
    }
}
