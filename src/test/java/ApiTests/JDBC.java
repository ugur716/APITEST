package ApiTests;

import org.testng.annotations.Test;

import java.sql.*;

public class JDBC {
    String dbURL="jdbc:oracle:thin:@54.162.203.9:1521:xe";
    String dbUsername="hr";
    String dbpassword="hr";

    @Test
    public void tc1() throws SQLException {
        Connection connection= DriverManager.getConnection(dbURL,dbUsername,dbpassword);

        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet=statement.executeQuery("select * from countries");

        resultSet.next();
        ResultSetMetaData rsmd= resultSet.getMetaData();
        System.out.println(rsmd.getColumnName(1));

    }
}
