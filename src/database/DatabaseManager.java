package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import windows.ErrorPopupWindow;

public class DatabaseManager {
    private final String DATABASE_URL = "jdbc:derby://localhost:1527/yogi_database";
    private final String SELECT_QUERY = "SELECT playerName, playerScores FROM scores ORDER BY playerScores DESC FETCH FIRST 10 ROWS ONLY";

    public ArrayList<String> getTop10() {
        try (
            Connection connection = DriverManager.getConnection(DATABASE_URL, "username", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY)
        ) {
            String tmp;
            ArrayList<String> output = new ArrayList<>();
            while (resultSet.next()) {
                tmp = (String) resultSet.getObject(1).toString() + " " + resultSet.getObject(2).toString();
                output.add(tmp);
            }
            return output;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            new ErrorPopupWindow("DATABASE ERROR","Something went wrong!");
        }
        return null;
    }
    
    public void insert(String name, int score){
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, "username", "password");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO scores " + "VALUES ('"+name+"', "+new Integer(score).toString()+")");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
