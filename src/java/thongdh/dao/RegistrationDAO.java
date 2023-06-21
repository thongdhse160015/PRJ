package thongdh.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import thongdh.controller.LoginServlet;
import thongdh.dto.RegistrationDTO;
import thongdh.util.DBHelper;
import thongdh.util.MyLogger;

/**
 *
 * @author thongdhse160015
 */
public class RegistrationDAO implements Serializable {

    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "select username "
                        + "from Registration "
                        + "where username = ? "
                        + "and password = ?;";
                //3. Create Statement Object
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                //4. Execute Query
                rs = ps.executeQuery();
                //5. Process Result
                if (rs.next()) {
                    result = true;
                }//end rs had got only one row 
            }//end connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();;
            }
            if (con != null) {
                con.close();
            }

        }
        return result;
    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "delete from Registration "
                        + "where username = ?";
                //3. Create Statement Object
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                //4. Execute Query
                int effectedRows = ps.executeUpdate();
                //5. Process Result
                if (effectedRows > 0) {
                    result = true;
                    Logger.getLogger(this.getClass().toString())
                            .log(Level.INFO, "Successfully deleteAccount with username = ".concat("username"));
                }//end rs had got only one row 
            }//end connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();;
            }
            if (con != null) {
                con.close();
            }

        }
        return result;
    }

    public void searchLastname(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE lastname LIKE ?";
                //3. Create Statement Object
                ps = con.prepareStatement(sql);
                ps.setString(1, searchValue);
                //4. Execute Query
                ResultSet resultSet = ps.executeQuery();
                //5. Process Result
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String lastname = resultSet.getString("lastname");
                    boolean role = resultSet.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(
                            username, password, lastname, role);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }
                    this.accountList.add(dto);
                }
            }//end connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }

        }
    }

    public boolean UpdateAccount(String username, String password, boolean role)
            throws SQLException, NamingException {;
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            // 1. Establish the database connection
            con = DBHelper.makeConnection();
            if (con != null) {
                // 1. Establish the database connection
                String sql = "UPDATE Registration "
                        + "SET password = ?, isAdmin = ? "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                // 3. Set the parameters for the prepared statement
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                // 4. Execute the update
                int effectRows = stm.executeUpdate();
                // 5. Process the result
                if (effectRows > 0) {
                    result = true;
                    Logger.getLogger(this.getClass().toString())
                            .log(Level.INFO, new StringBuilder().append("Update ")
                                    .append(username)
                                    .append(" successfully!")
                                    .toString());
                } else {
                    Logger.getLogger(this.getClass().toString())
                            .log(Level.INFO, new StringBuilder().append("Update ")
                                    .append(username)
                                    .append(" failure!")
                                    .toString());
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
