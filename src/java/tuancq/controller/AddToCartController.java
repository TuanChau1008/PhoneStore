/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuancq.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tuancq.dao.ProductDAO;
import tuancq.dto.CartDTO;
import tuancq.dto.ProductDTO;

/**
 *
 * @author ADmin
 */
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "LoadProductController";

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        String productID = request.getParameter("productID");
        try {
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartDTO();
            }
            ProductDAO dao = new ProductDAO();
            ProductDTO dto = dao.getProduct(productID);
            int availableQuantity = dto.getQuantity();
            Map<String, ProductDTO> list = cart.getCart();
            int cartQuantity = 0;
            int totalQuantity = 0;
            float totalPrice = 0.0f;

            if (list != null) {
                if (list.get(productID) != null) {
                    cartQuantity = list.get(productID).getQuantity();
                }
            }
            if ((cartQuantity + 1) > availableQuantity) {
                request.setAttribute("CART_ERROR", "Product is out stock");

            } else {
                cart.add(dto);
                list = cart.getCart();
                for (String key : list.keySet()) {
                    if (list.get(key) != null) {
                        totalQuantity += list.get(key).getQuantity();
                        totalPrice += (list.get(key).getPrice() * list.get(key).getQuantity());
                    }
                }

            }
            session.setAttribute("CART", cart);
            session.setAttribute("TOTALQUANTITY", totalQuantity);
            session.setAttribute("TOTALPRICE", totalPrice);
        } catch (Exception e) {
            log("Exception at AddToCartController:" + e.getMessage());
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddToCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddToCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
