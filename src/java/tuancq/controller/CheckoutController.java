/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuancq.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tuancq.dao.OrderDAO;
import tuancq.dao.OrderDetailDAO;
import tuancq.dao.ProductDAO;
import tuancq.dto.CartDTO;
import tuancq.dto.ProductDTO;
import tuancq.dto.UserDTO;

/**
 *
 * @author ADmin
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "viewcart.jsp";

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
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if (cart != null) {
                ProductDTO dto = new ProductDTO();
                OrderDAO orderDao = new OrderDAO();
                UserDTO user = (UserDTO) session.getAttribute("USER");
                String userID = user.getUserID();
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                float totalPrice = (float) session.getAttribute("TOTALPRICE");
                int orderID = orderDao.insertOrder(userID, sdf.format(date), totalPrice);
                if (orderID > -1) {
                    Map<String, ProductDTO> list = cart.getCart();
                    for (String productID : list.keySet()) {
                        int quantity = list.get(productID).getQuantity();
                        float price = list.get(productID).getPrice();
                        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                        boolean result = orderDetailDAO.insertOrderDetail(orderID, productID, price, quantity);
                        if(result) {
                            ProductDAO productDAO = new ProductDAO();
                            dto = productDAO.getProduct(productID);
                            productDAO.updateQuantity(productID, dto.getQuantity()- quantity);
                            session.removeAttribute("CART");
                            request.setAttribute("MESSAGE", "Checkout Successfully!!");
                            url = SUCCESS;
                        }
                    }
                }
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
