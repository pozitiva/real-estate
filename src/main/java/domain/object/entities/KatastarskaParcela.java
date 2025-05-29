package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KatastarskaParcela extends DomainObject {

    private int brojParcele;
    private String katastarskaOpstina;
    private int brListNepokretnosti;
    private int adresaID;
    private int gradID;

    public KatastarskaParcela() {
    }

    public KatastarskaParcela(int brojParcele, String katastarskaOpstina, int brListNepokretnosti, int adresaID, int gradID) {
        this.brojParcele = brojParcele;
        this.katastarskaOpstina = katastarskaOpstina;
        this.brListNepokretnosti = brListNepokretnosti;
        this.adresaID = adresaID;
        this.gradID = gradID;
    }

    public int getBrojParcele() {
        return brojParcele;
    }

    public void setBrojParcele(int brojParcele) {
        this.brojParcele = brojParcele;
    }

    public String getKatastarskaOpstina() {
        return katastarskaOpstina;
    }

    public void setKatastarskaOpstina(String katastarskaOpstina) {
        this.katastarskaOpstina = katastarskaOpstina;
    }

    public int getBrListNepokretnosti() {
        return brListNepokretnosti;
    }

    public void setBrListNepokretnosti(int brListNepokretnosti) {
        this.brListNepokretnosti = brListNepokretnosti;
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
        return "KATASTARSKAPARCELA kp";
    }

    @Override
    public String getAllColumnNames() {
        return "kp.BROJPARCELE, kp.KATASTARSKAOPSTINA, kp.BRLISTNEPOKRETNOSTI, kp.ADRESAID, kp.GRADID";
    }

    @Override
    public String getInsertColumnNames() {
        return "BROJPARCELE, KATASTARSKAOPSTINA, BRLISTNEPOKRETNOSTI, ADRESAID, GRADID";
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, '%s', %d, %d, %d",
                brojParcele,
                katastarskaOpstina,
                brListNepokretnosti,
                adresaID,
                gradID);
    }

    @Override
    public String getUpdateClause() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUpdateWhereClause() {
        return String.format("BROJPARCELE = %d", this.getBrojParcele());
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> parcele = new ArrayList<>();

        while (rs.next()) {
            int brojParcele = rs.getInt("BROJPARCELE");
            String katastarskaOpstina = rs.getString("KATASTARSKAOPSTINA");
            int brListNepokretnosti = rs.getInt("BRLISTNEPOKRETNOSTI");
            int adresaID = rs.getInt("ADRESAID");
            int gradID = rs.getInt("GRADID");

            parcele.add(new KatastarskaParcela(brojParcele, katastarskaOpstina, brListNepokretnosti, adresaID, gradID));
        }

        return parcele;
    }

    @Override
    public String getOrderByColumn() {
        return "BROJPARCELE";
    }
}
