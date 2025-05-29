package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Zapisnik extends DomainObject {

    private int zapisnikID;
    private Date datumPrimopredaje;
    private String tip;
    private int potvrdaID;

    public Zapisnik() {
    }

    public Zapisnik(int zapisnikID, Date datumPrimopredaje, String tip, int potvrdaID) {
        this.zapisnikID = zapisnikID;
        this.datumPrimopredaje = datumPrimopredaje;
        this.tip = tip;
        this.potvrdaID = potvrdaID;
    }

    public int getZapisnikID() {
        return zapisnikID;
    }

    public void setZapisnikID(int zapisnikID) {
        this.zapisnikID = zapisnikID;
    }

    public Date getDatumPrimopredaje() {
        return datumPrimopredaje;
    }

    public void setDatumPrimopredaje(Date datumPrimopredaje) {
        this.datumPrimopredaje = datumPrimopredaje;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getPotvrdaID() {
        return potvrdaID;
    }

    public void setPotvrdaID(int potvrdaID) {
        this.potvrdaID = potvrdaID;
    }
    
    
    @Override
    public String getTableName() {
        return "ZAPISNIK";
    }

    @Override
    public String getAllColumnNames() {
        return "ZAPISNIKID, DATUMPRIMOPREDAJE, TIP, POTVRDAID";
    }

    @Override
    public String getInsertColumnNames() {
     return "ZAPISNIKID, DATUMPRIMOPREDAJE, TIP, POTVRDAID";
    }

    @Override
    public String getColumnValues() {
       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
       String datumPrimopredajeStr= datumPrimopredaje != null ? "TO_DATE('" + sdf.format(datumPrimopredaje) + "', 'DD-MM-YY')" : "NULL";
       
       return String.format("%d , '%s', '%s', %d", 
               zapisnikID,
               datumPrimopredajeStr,
               tip,
               potvrdaID);
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
        return String.format("ZAPISNIKID = %d", this.getZapisnikID());
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> zapisnici =new ArrayList<>();
        
        while(rs.next()){
        int zapisnikID= rs.getInt("ZAPISNIKID");
        Date datumPrimopredaje = rs.getDate("DATUMPRIMOPREDAJE");
        String tip = rs.getString("TIP");
        int potvrdaID= rs.getInt("POTVRDAID");
        
        zapisnici.add(new Zapisnik(zapisnikID, datumPrimopredaje, tip, potvrdaID));
        }
        
        return zapisnici;
    }

    @Override
    public String getOrderByColumn() {
      return "ZAPISNIKID";
    }
    
}
