/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static com.sun.xml.bind.v2.util.EditDistance.editDistance;
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

/**
 *
 * @author Shrouk
 */
@WebServlet(urlPatterns = {"/addNewCandidate"})
public class addNewCandidate extends HttpServlet {

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
           String AQ1= request.getParameter("textBox1");
           String AQ2= request.getParameter("textBox2");
           String AQ3= request.getParameter("textBox3");
           String mail=request.getSession().getAttribute("mai").toString();
           String type=request.getSession().getAttribute("typ").toString();
           
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
            Con = (java.sql.Connection) DriverManager.getConnection(url, user, password);
            statement = Con.createStatement();
            statement.executeUpdate("INSERT INTO onlinetestswebsite.candidatesanswers " + "values('" + mail+"'," +"'"+ type +"'," +"'"+request.getSession().getAttribute("Q0").toString()+"'," +"'"+ AQ1 +"')");
            statement.executeUpdate("INSERT INTO onlinetestswebsite.candidatesanswers " + "values('" + mail+"'," +"'"+ type +"'," +"'"+request.getSession().getAttribute("Q1").toString()+"'," +"'"+ AQ2 +"')");
            statement.executeUpdate("INSERT INTO onlinetestswebsite.candidatesanswers " + "values('" + mail+"'," +"'"+ type +"'," +"'"+request.getSession().getAttribute("Q2").toString()+"'," +"'"+ AQ3 +"')");
            int count=0;
            for (int i=0; i<3; i++){
                ResultSet RS =statement.executeQuery("select QID from onlinetestswebsite.questions where text = "+"'"+request.getSession().getAttribute("Q"+i).toString()+"'");
                if(RS.next()){
                    ResultSet RS2=statement.executeQuery("select text from onlinetestswebsite.answers where QID = "+"'"+RS.getInt("QID")+"'");
                    if(RS2.next()){
                        double ratio= similarity(RS2.getString("text"),request.getParameter("textBox"+(i+1)));
                        if(ratio >= 0.65){
                            count++;
                        }
                    }
                }
            }
            if(count>=2)
                response.sendRedirect("pass.jsp");

            else
                response.sendRedirect("fail.jsp");
            
         
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
            Logger.getLogger(addNewCandidate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(addNewCandidate.class.getName()).log(Level.SEVERE, null, ex);
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

    public double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { 
          longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }

        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

}
