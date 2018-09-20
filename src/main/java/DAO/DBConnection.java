package DAO;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;


public class DBConnection {

    private static DBConnection instance = null;
    private Connection connection = null;
    private final String DBNAME = "jdbc:sqlite:src/main/resources/phoneBook.db";

    public DBConnection() {
        getConnectionToDatabase();
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    private void getConnectionToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(DBNAME);
        } catch (SQLException e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public ArrayList<ArrayList<String>> getAllObjectsFromDatabase(Connection connection, String query) throws SQLException {
        ArrayList<ArrayList<String>> allObjects = new ArrayList();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery();

        // Group all column names from query result
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> columnNames = new ArrayList<String>();

        for (int row = 1; row <= columnCount; row++){
            String columnName = metaData.getColumnName(row).toString();
            columnNames.add(columnName);
        }

        while(result.next()){
            ArrayList<String> rowResult = new ArrayList<String>();
            for (int i = 0; i < columnNames.size(); i++){
                rowResult.add(result.getString(columnNames.get(i)));
            }
            allObjects.add(rowResult);
        }
        return allObjects;
    }
}

