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
import tuancq.utils.Dbutils;

/**
 *
 * @author ADmin
 */
public class OrderDAO {

    public int insertOrder(String userID, String date, float total) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int orderID = -1;
        try {
            conn = Dbutils.getConnection();
            String sql = "INSERT INTO tblOrders(userID,date,total) "
                    + " OUTPUT inserted.orderID "
                    + " VALUES(?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, userID);
            stm.setString(2, date);
            stm.setFloat(3, total);
            rs = stm.executeQuery();
            if (rs.next()) {
                orderID = rs.getInt("orderID");

            }

        } catch (Exception e) {
            e.printStackTrace();
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
        return orderID;
    }

}
