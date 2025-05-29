package DataBase;

import domain.object.DomainObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class DatabaseBroker {

    private Connection connection;
    private String url;
    private String username;
    private String password;

    public DatabaseBroker() {
        this.setDatabaseAccessParams();
    }
    
    private void setDatabaseAccessParams() {
        try {
            Properties properties = new Properties();
            String propertiesFileName = "config/db.properties";  
            try (FileInputStream fileInputStream = new FileInputStream(propertiesFileName)) {
                properties.load(fileInputStream);
                
                this.url = properties.getProperty("url");
                this.username = properties.getProperty("username");
                this.password = properties.getProperty("password");
            }
        } catch (IOException ex) {
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void connect() throws Exception {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Uspesno povezivanje na bazu!");
        } catch (SQLException ex) {
            throw new Exception("Greska prilikom uspostavljanja veze sa bazom podataka.");
        }
    }

    public void disconnect() throws Exception {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("DB disconnected");
            } catch (SQLException ex) {
                throw new Exception("Didconnection error!");
            }
        }
    }

    public List<DomainObject> getPartition(DomainObject object, String part) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM UGOVOROKUPOPRODAJI " + part;
            ResultSet rs = statement.executeQuery(query);

            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int insert(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO " + object.getTableName() + "(" + object.getInsertColumnNames() + ")" + " VALUES (" + object.getColumnValues() + ")";
            System.out.println(query);
            return statement.executeUpdate(query);

        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<DomainObject> getAll(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM "
                    + object.getTableName() + " ORDER BY " + object.getOrderByColumn();
            ResultSet rs = statement.executeQuery(query);
            System.out.println(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<DomainObject> getAllWithWhere(DomainObject object, String whereClause) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM "
                    + object.getTableName() + " WHERE " + whereClause + " ORDER BY " + object.getOrderByColumn();
            ResultSet rs = statement.executeQuery(query);

            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int delete(DomainObject odo) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM " + odo.getTableName() + " WHERE " + odo.getDeleteWhereClause();
            int rowsUpdated = statement.executeUpdate(query);
            return rowsUpdated;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int update(DomainObject odo) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE " + odo.getTableName() + " SET " + odo.getUpdateClause() + " WHERE " + odo.getUpdateWhereClause();
            System.out.println(query);
            int rowsUpdated = statement.executeUpdate(query);
            return rowsUpdated;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int updatePartial(DomainObject odo, String setClause) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE " + odo.getTableName() + " SET " + setClause + " WHERE " + odo.getUpdateWhereClause();
            System.out.println(query);
            int rowsUpdated = statement.executeUpdate(query);
            return rowsUpdated;
        } catch (SQLException ex) {
            throw ex;
        }
    }

}
