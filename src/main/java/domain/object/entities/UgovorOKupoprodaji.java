package domain.object.entities;

import domain.object.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UgovorOKupoprodaji extends DomainObject {

    private int ugovorOKupoprodajiID;
    private Date datumPotpisivanja;
    private int rezervacijaID;

    public UgovorOKupoprodaji() {
    }

    public UgovorOKupoprodaji(int ugovorOKupoprodajiID, Date datumPotpisivanja, int rezervacijaID) {
        this.ugovorOKupoprodajiID = ugovorOKupoprodajiID;
        this.datumPotpisivanja = datumPotpisivanja;
        this.rezervacijaID = rezervacijaID;
    }

    public int getUgovorOKupoprodajiID() {
        return ugovorOKupoprodajiID;
    }

    public void setUgovorOKupoprodajiID(int ugovorOKupoprodajiID) {
        this.ugovorOKupoprodajiID = ugovorOKupoprodajiID;
    }

    public Date getDatumPotpisivanja() {
        return datumPotpisivanja;
    }

    public void setDatumPotpisivanja(Date datumPotpisivanja) {
        this.datumPotpisivanja = datumPotpisivanja;
    }

    public int getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    @Override
    public String getTableName() {
        return "UGOVOROKUPOPRODAJI u";
    }

    @Override
    public String getAllColumnNames() {
        return "u.UGOVOROKUPOPRODAJIID, u.DATUMPOTPISIVANJA, u.REZERVACIJAID";
    }

    @Override
    public String getInsertColumnNames() {
        return "UGOVOROKUPOPRODAJIID, DATUMPOTPISIVANJA, REZERVACIJAID";
    }

    @Override
    public String getColumnValues() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        String datumPotpisivanjaStr = datumPotpisivanja != null ? 
            "TO_DATE('" + sdf.format(datumPotpisivanja) + "', 'DD-MM-YY')" : "NULL";

        return String.format("%d, '%s', %d",
                ugovorOKupoprodajiID,
                datumPotpisivanjaStr,
                rezervacijaID);
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
        return String.format("UGOVOROKUPOPRODAJIID = %d", this.getUgovorOKupoprodajiID());
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> ugovori = new ArrayList<>();

        while (rs.next()) {
            int ugovorID = rs.getInt("UGOVOROKUPOPRODAJIID");
            Date datum = rs.getDate("DATUMPOTPISIVANJA");
            int rezervacijaID = rs.getInt("REZERVACIJAID");

            ugovori.add(new UgovorOKupoprodaji(ugovorID, datum, rezervacijaID));
        }

        return ugovori;
    }

    @Override
    public String getOrderByColumn() {
        return "UGOVOROKUPOPRODAJIID";
    }
}
