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
import java.util.ArrayList;
import java.util.List;
import tuancq.dto.ProductDTO;
import tuancq.utils.Dbutils;

/**
 *
 * @author ADmin
 */
public class ProductDAO {

    public List<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO product = null;
        try {
            conn = Dbutils.getConnection();
            String sql = "SELECT productID, productName, price, quantity FROM tblProducts";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String proID = rs.getString("productID");
                String proName = rs.getString("productName");
                Float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                product = new ProductDTO(proID, proName, price, quantity);
                list.add(product);
            }
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
        return list;
    }

    public boolean updatePro(ProductDTO productDTO) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Dbutils.getConnection();
            String sql = "UPDATE"
                    + " tblProducts SET productName = ?, price = ?, quantity = ? "
                    + "where productID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, productDTO.getProductName());
            stm.setFloat(2, productDTO.getPrice());
            stm.setInt(3, productDTO.getQuantity());
            stm.setString(4, productDTO.getProductID());
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public ProductDTO getProduct(String productID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO dto = null;
        try {
            conn = Dbutils.getConnection();
            String sql = "SELECT productID, productName, price, quantity "
                    + " FROM tblProducts "
                    + "WHERE productID =?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, productID);
            rs = stm.executeQuery();
            if (rs.next()) {
                String productName = rs.getString("productName");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                dto = new ProductDTO(productID, productName, price, quantity);
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
        return dto;
    }

    public boolean deletePro(String id) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Dbutils.getConnection();
            String sql = "DELETE FROM tblProducts WHERE  productID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            int row = stm.executeUpdate();
            if (row > 0) {
                result = true;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean insertPro(ProductDTO product) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = Dbutils.getConnection();
            String sql = "INSERT INTO tblProducts(productID,productName,price,quantity) VALUES(?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, product.getProductID());
            stm.setString(2, product.getProductName());
            stm.setFloat(3, product.getPrice());
            stm.setInt(4, product.getQuantity());
            check = stm.executeUpdate() > 0;
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
        return check;
    }

    public List<ProductDTO> searchProduct(String search) throws SQLException {
        List<ProductDTO> listpro = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Dbutils.getConnection();
            if (conn != null) {
                String sql = "SELECT productID, productName, price, quantity "
                        + " FROM tblProducts WHERE productName LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (listpro == null) {
                        listpro = new ArrayList<>();
                    }
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    listpro.add(new ProductDTO(productID, productName, price, quantity));
                }
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
        return listpro;
    }

    public boolean checkDuplicate(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Dbutils.getConnection();
            String sql = "SELECT productID FROM tblProducts WHERE productID = ?";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
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
        return check;
    }

    public boolean updateQuantity(String productID, int quantity) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Dbutils.getConnection();
            String sql = "UPDATE"
                    + " tblProducts SET quantity = ? "
                    + "where productID = ?";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setString(2, productID);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
}
