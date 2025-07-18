package domain.object.entities;

import domain.object.DomainObject;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Nekretnina extends DomainObject{

    private int nekretninaID;
    private String brojStana;
    private float cena;
    private float povrsina;
    private String brojParcele;

    public Nekretnina() {
    }

    public Nekretnina(int nekretninaID, String brojStana, float povrsina, float cena, String brojParcele) {
        this.nekretninaID = nekretninaID;
        this.brojStana = brojStana;
        this.cena = cena;
        this.povrsina = povrsina;
        this.brojParcele = brojParcele;
    }

    public int getNekretninaID() {
        return nekretninaID;
    }

    public void setNekretninaID(int nekretninaID) {
        this.nekretninaID = nekretninaID;
    }

    public String getBrojStana() {
        return brojStana;
    }

    public void setBrojStana(String brojStana) {
        this.brojStana = brojStana;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public float getPovrsina() {
        return povrsina;
    }

    public void setPovrsina(float povrsina) {
        this.povrsina = povrsina;
    }

    public String getBrojParcele() {
        return brojParcele;
    }

    public void setBrojParcele(String brojParcele) {
        this.brojParcele = brojParcele;
    }
    
    @Override
    public String getTableName() {
        return "IVA.NEKRETNINA";
    }

   @Override
    public String getAllColumnNames() {
        return "NEKRETNINAID, NEKRETNINAPODACI, BROJPARCELE";
    }

    @Override
    public String getInsertColumnNames() {
        return "NEKRETNINAID, NEKRETNINAPODACI, BROJPARCELE";
    }

    @Override
    public String getColumnValues() {
        return String.format(Locale.US,"%d,  NEW IVA.NEKRETNINA_PODACI('%s', %.2f, %.2f), '%s'",
                nekretninaID,
                brojStana,
                povrsina,
                cena,
                brojParcele);
    }

    @Override
    public String getUpdateClause() {
        return String.format(Locale.US, "NEKRETNINAID = %d,  NEKRETNINAPODACI = IVA.NEKRETNINA_PODACI('%s', %.2f, %.2f), BROJPARCELE= '%s'",
                nekretninaID,
                brojStana,
                povrsina,
                cena,
                brojParcele);
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     @Override
    public String getUpdateWhereClause() {
        return String.format("NEKRETNINAID = %d", this.getNekretninaID());
    }

    @Override
    public String getDeleteWhereClause() {
        return String.format("NEKRETNINAID = %d", nekretninaID);
    }

   @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> nekretnine = new ArrayList<>();

        while (rs.next()) {
            int nekretninaID = rs.getInt("NEKRETNINAID");
            Struct struct = (Struct) rs.getObject("NEKRETNINAPODACI");
            Object[] attributes = struct.getAttributes();
            String brojStana = (String) attributes[0];
            float povrsina = ((BigDecimal) attributes[1]).floatValue();
            float cena = ((BigDecimal) attributes[2]).floatValue();
            String brojParcele = rs.getString("BROJPARCELE");
            nekretnine.add(new Nekretnina(nekretninaID, brojStana, povrsina, cena, brojParcele));
        }

        return nekretnine;
    }

    @Override
    public String getOrderByColumn() {
        return "NEKRETNINAID";
    }
}
