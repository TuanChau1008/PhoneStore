/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuancq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tuancq.dto.UserDTO;
import tuancq.utils.Dbutils;

/**
 *
 * @author ADmin
 */
public class userDAO {

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            conn = Dbutils.getConnection();
            if (conn != null) {
                String sql = "SELECT userID, fullName, roleID, password "
                        + " FROM tblUsers "
                        + " WHERE userID='" + userID + "' AND password='" + password + "'";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String Password = rs.getString("password");
                    user = new UserDTO(id, fullName, roleID, Password);
                }

            }
        } catch (Exception e) {
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return user;
    }

    public void insert(UserDTO user) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO tblUsers(userID, fullName, roleID, password) "
                + " VALUES(?,?,?,?)";
        try {
            conn = Dbutils.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, user.getUserID());
            stm.setString(2, user.getFullName());
            stm.setString(3, user.getRoleID());
            stm.setString(4, user.getPassword());
            rs = stm.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}
