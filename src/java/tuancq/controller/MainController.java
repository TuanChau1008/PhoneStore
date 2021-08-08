/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuancq.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADmin
 */
public class MainController extends HttpServlet {

    private final String HOME = "LoadProductController";
    private final String ERROR = "error.jsp";
    private final String LOGIN = "LoginController";
    private final String LOGOUT = "LogoutController";
    private final String UPDATE = "UpdateController";
    private final String CREATE = "CreateUserController";
    private final String ADD = "AddToCartController";
    private final String DELETE = "DeleteController";
    private final String SEARCH = "SearchController";
    private final String CREATEPRO = "CreateProductController";
    private final String CHECKOUT = "CheckoutController";
    private final String DELCART = "DeleteCartController";

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
        String action = request.getParameter("action");
        try {
            if ("Home".equals(action)) {
                url = HOME;
            }
            if ("Login".equals(action)) {
                url = LOGIN;
            }
            if ("Logout".equals(action)) {
                url = LOGOUT;
            }
            if ("Update".equals(action)) {
                url = UPDATE;
            }
            if ("Create".equals(action)) {
                url = CREATE;
            }
            if ("AddToCart".equals(action)) {
                url = ADD;
            }
            if ("Delete".equals(action)) {
                url = DELETE;
            }
            if ("Search".equals(action)) {
                url = SEARCH;
            }
            if ("CreatePro".equals(action)) {
                url = CREATEPRO;
            }
            if ("Confirm".equals(action)) {
                url = CHECKOUT;
            }
            if ("Delete Cart".equals(action)) {
                url = DELCART;
            }

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
