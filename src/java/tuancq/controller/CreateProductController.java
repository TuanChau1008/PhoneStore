/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuancq.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuancq.dao.ProductDAO;
import tuancq.dto.ProductDTO;
import tuancq.dto.ProductError;

/**
 *
 * @author ADmin
 */
@WebServlet(name = "CreateProductController", urlPatterns = {"/CreateProductController"})
public class CreateProductController extends HttpServlet {

    private static final String SUCCESS = "LoadProductController";
    private static final String ERROR = "createproduct.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String price = request.getParameter("price");
            String quantity = request.getParameter("quantity");
            ProductError errorpro = new ProductError("", "", "", "");
            ProductDAO dao = new ProductDAO();
            boolean flag = true;
            if (productID != null && (productID.length() < 4 || productID.length() > 10)) {
                errorpro.setProductIDErr("productID must be from 4 to 10 character");
                flag = false;
            }
            if (productName != null && (productName.length() < 2 || productName.length() > 10)) {
                errorpro.setProductNameErr("productName must be from 2 to 10 character");
                flag = false;
            }
            if (quantity != null && (quantity.length() < 2 || quantity.length() > 3)) {
                errorpro.setQuantityErr("Quantity must be from 2 to 3 character");
                flag = false;
            }
            boolean numberprice = price.chars().allMatch(Character::isDigit);
            boolean numberquantity = quantity.chars().allMatch(Character::isDigit);
            if (numberprice == false) {
                flag = false;
                errorpro.setPrice("Price is not the number!!");
            }
            if (numberquantity == false) {
                flag = false;
                errorpro.setQuantityErr("Quanity is not the number!!");
            }
            boolean check = dao.checkDuplicate(productID);
            if(check) {
                errorpro.setProductIDErr("ProductID is duplicate!!!");
                flag = false;
            }
            request.setAttribute("ERROR_PRO", errorpro);
            if(flag) {
                float p = Float.parseFloat(price);
                int qua = Integer.parseInt(quantity);
                ProductDTO product = new ProductDTO(productID, productName, p, qua);
                dao.insertPro(product);
                url = SUCCESS;
            }

        } catch (Exception e) { 
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
