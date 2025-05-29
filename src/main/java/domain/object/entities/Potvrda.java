package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Potvrda extends DomainObject{

    private int potvrdaID;
    private Date datumPotvrde;
    private String opu;
    private int ugovorOKupoprodajiID;
    private int beleznikID;
    private String imePrezime;

    public Potvrda() {
    }

    public Potvrda(int potvrdaID, Date datumPotvrde, String OPU, int uovorOKupoprodajiID, int beleznikID, String imePrezime) {
        this.potvrdaID = potvrdaID;
        this.datumPotvrde = datumPotvrde;
        this.opu = OPU;
        this.ugovorOKupoprodajiID = uovorOKupoprodajiID;
        this.beleznikID = beleznikID;
        this.imePrezime = imePrezime;
    }

    public int getPotvrdaID() {
        return potvrdaID;
    }

    public void setPotvrdaID(int potvrdaID) {
        this.potvrdaID = potvrdaID;
    }

    public Date getDatumPotvrde() {
        return datumPotvrde;
    }

    public void setDatumPotvrde(Date datumPotvrde) {
        this.datumPotvrde = datumPotvrde;
    }

    public String getOPU() {
        return opu;
    }

    public void setOPU(String OPU) {
        this.opu = OPU;
    }

    public int getUovorOKupoprodajiID() {
        return ugovorOKupoprodajiID;
    }

    public void setUovorOKupoprodajiID(int uovorOKupoprodajiID) {
        this.ugovorOKupoprodajiID = uovorOKupoprodajiID;
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
    
    
    @Override
    public String getTableName() {
       return "POTVRDA p";
    }

    @Override
    public String getAllColumnNames() {
        return "p.POTVRDAID,p.DATUMPOTVRDE, p.OPU, p.UGOVOROKUPOPRODAJIID, p.BELEZNIKID, p.IMEPREZIME";
    }

    @Override
    public String getInsertColumnNames() {
        return "POTVRDAID, DATUMPOTVRDE, OPU, UGOVOROKUPOPRODAJIID, BELEZNIKID, IMEPREZIME";
    }

    @Override
    public String getColumnValues() {
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
         
         String datumPotvrdeStr = datumPotvrde != null ? "TO_DATE('"+ sdf.format(datumPotvrde)+ "', 'DD-MM-YY')": "NULL";
         
         return String.format("%d, '%s', '%s', %d, %d, '%s'",
                potvrdaID,
                datumPotvrdeStr,
                opu,
                ugovorOKupoprodajiID,
                beleznikID,
                imePrezime);
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
        return String.format("POTVRDAID = %d", this.getPotvrdaID());
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> potvrde = new ArrayList<>();
        
        while(rs.next()){
            int potvrdaID= rs.getInt("POTVRDAID");
            Date datumPotvrde = rs.getDate("DATUMPOTVRDE");
            String opu = rs.getString("OPU");
            int ugovorOKupoprodajiID = rs.getInt("UGOVOROKUPOPRODAJIID");
            int beleznikID = rs.getInt("BELEZNIKID");
            String imePrezime = rs.getString("IMEPREZIME");
            
            potvrde.add(new Potvrda(potvrdaID, datumPotvrde, opu, ugovorOKupoprodajiID, beleznikID, imePrezime));
        }
        
        return potvrde;
    }

    @Override
    public String getOrderByColumn() {
        return "POTVRDAID";
    }
    
    
    
}
