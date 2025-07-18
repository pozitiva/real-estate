package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UgovorOPosredovanju extends DomainObject {

    private int ugovorOPosredovanjuID;
    private Date datumPotpisivanja;
    private int nekretninaID;
    private String brojParcele;
    private String agencijaID;

    public UgovorOPosredovanju() {
    }

    public UgovorOPosredovanju(int ugovorOPosredovanjuID, Date datumPotpisivanja, int nekretninaID, String brojParcele, String agencijaID) {
        this.ugovorOPosredovanjuID = ugovorOPosredovanjuID;
        this.datumPotpisivanja = datumPotpisivanja;
        this.nekretninaID = nekretninaID;
        this.brojParcele = brojParcele;
        this.agencijaID = agencijaID;
    }

    public int getUgovorOPosredovanjuID() {
        return ugovorOPosredovanjuID;
    }

    public void setUgovorOPosredovanjuID(int ugovorOPosredovanjuID) {
        this.ugovorOPosredovanjuID = ugovorOPosredovanjuID;
    }

    public Date getDatumPotpisivanja() {
        return datumPotpisivanja;
    }

    public void setDatumPotpisivanja(Date datumPotpisivanja) {
        this.datumPotpisivanja = datumPotpisivanja;
    }

    public int getNekretninaID() {
        return nekretninaID;
    }

    public void setNekretninaID(int nekretninaID) {
        this.nekretninaID = nekretninaID;
    }

    public String getBrojParcele() {
        return brojParcele;
    }

    public void setBrojParcele(String brojParcele) {
        this.brojParcele = brojParcele;
    }

    public String getAgencijaID() {
        return agencijaID;
    }

    public void setAgencijaID(String agencijaID) {
        this.agencijaID = agencijaID;
    }
    
    @Override
    public String getTableName() {
       return "IVA.UGOVOROPOSREDOVANJU u";
    }

    @Override
    public String getAllColumnNames() {
        return "u.UGOVOROPOSREDOVANJUID, u.DATUMPOTPISIVANJA, u.NEKRETNINAID, u.BROJPARCELE, u.AGENCIJAID";
    }

    @Override
    public String getInsertColumnNames() {
        return "UGOVOROPOSREDOVANJUID, DATUMPOTPISIVANJA, NEKRETNINAID, BROJPARCELE, AGENCIJAID";
    }

    @Override
    public String getColumnValues() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
         
         String datumPotpisivanjaStr = datumPotpisivanja != null ? "TO_DATE('"+ sdf.format(datumPotpisivanja)+ "', 'DD-MM-YY')": "NULL";
         
         return String.format("%d, %s, %d, '%s', '%s'",
                 ugovorOPosredovanjuID,
                 datumPotpisivanjaStr,
                 nekretninaID,
                 brojParcele,
                 agencijaID);
    }

    @Override
    public String getUpdateClause() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
         
        String datumPotpisivanjaStr = datumPotpisivanja != null ? "TO_DATE('"+ sdf.format(datumPotpisivanja)+ "', 'DD-MM-YY')": "NULL";
         
       return String.format("UGOVOROPOSREDOVANJUID=%d, DATUMPOTPISIVANJA=%s, NEKRETNINAID=%d, AGENCIJAID = '%s'",
                 ugovorOPosredovanjuID,
                 datumPotpisivanjaStr,
                 nekretninaID,
                 agencijaID);
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
        return String.format("UGOVOROPOSREDOVANJUID = %d", this.getUgovorOPosredovanjuID());
    }

    @Override
    public String getDeleteWhereClause() {
         return String.format("UGOVOROPOSREDOVANJUID = %d", this.getUgovorOPosredovanjuID());
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
         List<DomainObject> ugovoriOPosredovanju = new ArrayList<>();
         
         while(rs.next()){
             int ugovorOPosredovanjuID= rs.getInt("UGOVOROPOSREDOVANJUID");
             Date datumPotpisivanja = rs.getDate("DATUMPOTPISIVANJA");
             int nekretninaID= rs.getInt("NEKRETNINAID");
             String brojParcele = rs.getString("BROJPARCELE");
             String agencijaID = rs.getString("AGENCIJAID");
             
             ugovoriOPosredovanju.add(new UgovorOPosredovanju(ugovorOPosredovanjuID, datumPotpisivanja,nekretninaID, brojParcele, agencijaID));
         }
    
         return ugovoriOPosredovanju;
    }

    @Override
    public String getOrderByColumn() {
       return "UGOVOROPOSREDOVANJUID";
    }
    
}
