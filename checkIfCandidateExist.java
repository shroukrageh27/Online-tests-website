/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
@WebServlet(urlPatterns = {"/checkIfCandidateExist"})
public class checkIfCandidateExist extends HttpServlet {

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
            String typee="";
            String type= request.getParameter("type");
            
            if (null != type)switch (type) {
                case "1":
                    typee="java";
                    break;
                case "2":
                    typee="data mining";
                    break;
                case "3":
                    typee="sql";
                    break;
                default:
                    break;
            }
            
            
            String mail="";
            if(request.getHeader("Referer").contains("checkIfUserExist")){
                mail=request.getSession().getAttribute("m").toString();
            }
            else if(request.getHeader("Referer").contains("addNewUser")){
                mail=request.getSession().getAttribute("ma").toString();
            }
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
            } 
            catch (ClassNotFoundException e) {
            }
                
            String url = "jdbc:mysql://localhost:3306/onlinetestswebsite";
            String user = "root";
            String password = "root";
            java.sql.Connection Con =null;
            Statement statement = null;
            ResultSet RS = null;
            Con = (java.sql.Connection) DriverManager.getConnection(url, user, password);
            String query="select * from onlinetestswebsite.candidatesanswers where email = "+"'"+mail+"'";
            statement = Con.createStatement();
            RS = statement.executeQuery(query);
            boolean temp=false;
            while(RS.next()){
                if(typee.equals(RS.getString(2))){
                    temp=true;
                }
               
            }
            if(temp == false){
                HttpSession session = request.getSession(true);
                session.setAttribute("type", typee);      
                session.setAttribute("mail", mail); 
                request.getRequestDispatcher("exam.jsp").forward(request, response);
            }
            else{
                response.sendRedirect("candidates.jsp");
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
            Logger.getLogger(checkIfCandidateExist.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(checkIfCandidateExist.class.getName()).log(Level.SEVERE, null, ex);
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
