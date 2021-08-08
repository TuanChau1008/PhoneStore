/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuancq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tuancq.utils.Dbutils;

/**
 *
 * @author ADmin
 */
public class OrderDetailDAO {

    public boolean insertOrderDetail(int orderID, String productID, float price, int quantity) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = Dbutils.getConnection();
            String sql = "INSERT INTO tblOrderDetail(orderID,productID,price,quantity)  "
                    + "VALUES(?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, orderID);
            stm.setString(2, productID);
            stm.setFloat(3, price);
            stm.setInt(4, quantity);
            check = stm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
