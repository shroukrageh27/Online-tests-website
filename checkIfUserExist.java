/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shrouk
 */
@WebServlet(urlPatterns = {"/checkIfUserExist"})
public class checkIfUserExist extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try{
                Class.forName("com.mysql.jdbc.Driver");
            } 
            catch (ClassNotFoundException e) {
            }
                
            String url = "jdbc:mysql://localhost:3306/onlinetestswebsite";
            String user = "root";
            String password = "root";
            java.sql.Connection Con =null;
            PreparedStatement preparedStatement = null;
            ResultSet RS = null;
            Con = (java.sql.Connection) DriverManager.getConnection(url, user, password);
            String email=request.getParameter("email");
            String pass=request.getParameter("pass");
            String query="select * from candidates where email = "+"'"+ email+ "'";
            preparedStatement = Con.prepareStatement(query);
            RS = preparedStatement.executeQuery();
            
            String mail="";
            String pas="";
            while(RS.next())
            {
                mail=RS.getString(3);
                pas=RS.getString(4);
            }
            
            if(email.equals(mail) && pass.equals(pas))
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("m", request.getParameter("email"));       
                request.getRequestDispatcher("candidates.jsp").forward(request, response);
            }
            else if(email.equals(mail) && !pass.equals(pas))
            {
                response.sendRedirect("login.jsp");
            }
            else if(!email.equals(mail))
            {
                response.sendRedirect("signup.jsp");
            }
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
            Logger.getLogger(checkIfUserExist.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(checkIfUserExist.class.getName()).log(Level.SEVERE, null, ex);
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
