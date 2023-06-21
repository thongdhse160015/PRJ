package thongdh.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author thongdhse160015
 */
public class DBHelper {

//    private static final String USERNAME = "sa";
//    private static final String PASSWORD = "12345678";
//    private static final String URL = "jdbc:sqlserver://"
//            + "localhost:1433;"
//            + "databaseName=SinhVien2K17;"
//            + "trustServerCertificate=false;"
//            + "loginTimeout=30;";
//
//    public static Connection makeConnection()
//            throws SQLException, ClassNotFoundException {
//        //1. Loading the required JDBC Driver class - Add jar file first
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create connection string
//        //3. Open connection: driver + driverManater
//        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        //4. return caller
//        return connection;
//    }
    public static Connection makeConnection()
            throws NamingException, SQLException {
        //1. Get current context
        Context initialContext = new InitialContext();
        //2. Get Web Server Context
        Context tomcatContext = (Context) initialContext.lookup("java:comp/env");
        //3. get DS to open connection
        DataSource dataSource = (DataSource) tomcatContext.lookup("DSSE1710");
        //4. Open connection
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
