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
import tuancq.dao.userDAO;
import tuancq.dto.UserDTO;
import tuancq.dto.UserError;

/**
 *
 * @author ADmin
 */
@WebServlet(name = "CreateUserController", urlPatterns = {"/CreateUserController"})
public class CreateUserController extends HttpServlet {

    final static String SUCCESS = "login.jsp";
    final static String ERROR = "createuser.jsp";

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
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            UserError error = new UserError("", "", "", "", "");
            userDAO dao = new userDAO();
            boolean flag = true;
            if (userID != null && (userID.length() < 4 || userID.length() > 10)) {
                error.setUserIDErr("userID must be from 4 to 10 character");
                flag = false;
            }
            if (fullName != null && (fullName.length() < 5 || fullName.length() > 15)) {
                error.setFullNameErr("fullName must be from 5 to 15 character");
                flag = false;
            }
            if (roleID != null && (roleID.length() < 2 || roleID.length() > 5)) {
                error.setRoleIDErr("roleID must be from 4 to 10 character");
                flag = false;
            }
            if (!password.equals(confirm)) {
                error.setPassword("Password and Confirm is not same");
                flag = false;
            }
            if (flag) {
                UserDTO user = new UserDTO(userID, fullName, roleID, password);
                dao.insert(user);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", error);
            }
        } catch (Exception e) {
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
