/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain.object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author subot
 */
public abstract class DomainObject {
    public abstract String getTableName();
    
    public abstract String getAllColumnNames();
    
    public abstract String getInsertColumnNames();

    public abstract String getColumnValues();

    public abstract String getUpdateClause();

    public abstract String getWhereIdClause();
    
    public abstract String getUpdateWhereClause();

    public abstract String getDeleteWhereClause();
    
    public abstract List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException;

    public abstract String getOrderByColumn();
}
