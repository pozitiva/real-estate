package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class NekretninaProdavca extends DomainObject {

    private int nekretninaID;
    private String jmbg;
    private String udeoVlasnistva;
    private String osnovSticanja;

    public NekretninaProdavca() {
    }

    public NekretninaProdavca(int nekretninaID, String jmbg, String udeoVlasnistva, String osnovSticanja) {
        this.nekretninaID = nekretninaID;
        this.jmbg = jmbg;
        this.udeoVlasnistva = udeoVlasnistva;
        this.osnovSticanja = osnovSticanja;
    }

    public int getNekretninaID() {
        return nekretninaID;
    }

    public void setNekretninaID(int nekretninaID) {
        this.nekretninaID = nekretninaID;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getUdeoVlasnistva() {
        return udeoVlasnistva;
    }

    public void setUdeoVlasnistva(String udeoVlasnistva) {
        this.udeoVlasnistva = udeoVlasnistva;
    }

    public String getOsnovSticanja() {
        return osnovSticanja;
    }

    public void setOsnovSticanja(String osnovSticanja) {
        this.osnovSticanja = osnovSticanja;
    }

    @Override
    public String getTableName() {
        return "IVA.NEKRETNINAPRODAVCA np";
    }

    @Override
    public String getAllColumnNames() {
        return "np.NEKRETNINAID, np.JMBG, np.UDEOVLASNISTVA, np.OSNOV_STICANJA";
    }

    @Override
    public String getInsertColumnNames() {
        return "NEKRETNINAID, JMBG, UDEOVLASNISTVA, OSNOV_STICANJA";
    }

    @Override
    public String getColumnValues() {
        return String.format("%d, '%s', '%s', IVA.OSNOV_STICANJA('%s')",
                nekretninaID,
                jmbg,
                udeoVlasnistva,
                osnovSticanja);
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
        return String.format("NEKRETNINAID = %d AND JMBG = '%s'", this.nekretninaID, this.jmbg);
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> nekretnineProdavca = new ArrayList<>();

        while (rs.next()) {
            int nekretninaID = rs.getInt("NEKRETNINAID");
            String jmbg = rs.getString("JMBG");
            String udeoVlasnistva = rs.getString("UDEOVLASNISTVA");
            Struct struct = (Struct) rs.getObject("OSNOV_STICANJA");
            Object[] attributes = struct.getAttributes();
            String osnovSticanja = attributes[0] != null ? attributes[0].toString() : null;
            
            nekretnineProdavca.add(new NekretninaProdavca(nekretninaID, jmbg, udeoVlasnistva, osnovSticanja));
        }

        return nekretnineProdavca;
    }

    @Override
    public String getOrderByColumn() {
        return "NEKRETNINAID";
    }
}
