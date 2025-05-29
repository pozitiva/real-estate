package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Grad extends DomainObject {
    
    private int gradID;
    private String nazivGrada;

    public Grad() {
    }
    
    public Grad(int GradID, String NazivGrada) {
        this.gradID = GradID;
        this.nazivGrada = NazivGrada;
    }

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int GradID) {
        this.gradID = GradID;
    }

    public String getNazivGrada() {
        return nazivGrada;
    }

    public void setNazivGrada(String NazivGrada) {
        this.nazivGrada = NazivGrada;
    }

    @Override
    public String getTableName() {
        return "IVA.GRAD";
    }

    @Override
    public String getAllColumnNames() {
        return "GRADID, NAZIVGRADA";
    }

    @Override
    public String getInsertColumnNames() {
        return "GRADID, NAZIVGRADA";
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, '%s'", 
                gradID, 
                nazivGrada);
    }

    @Override
    public String getUpdateClause() {
      return String.format("GRADID = %d, NAZIVGRADA = '%s'", 
                gradID, 
                nazivGrada);}

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateWhereClause() {
        return String.format("GRADID = %d", this.getGradID());
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        
        List<DomainObject> gradovi = new ArrayList<>();
        
        while (rs.next()){
        int gradID = rs.getInt("GRADID");
        String nazivGrada = rs.getString("NAZIVGRADA");
        gradovi.add(new Grad(gradID, nazivGrada));
        }
        
        return gradovi;
    }

    @Override
    public String getOrderByColumn() {
        return "GRADID";
    }
    
    
    
    
}
