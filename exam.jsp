<%-- 
    Document   : exam
    Created on : Nov 24, 2019, 5:14:23 PM
    Author     : Shrouk
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Page</title>
    </head>
    <body style="background: url('signup.jpg') no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;">
        
        <form action="index.html"> <input type="submit" value="Log out"/></form>
        <%
            String typee = request.getSession().getAttribute("type").toString();
            String maill = request.getSession().getAttribute("mail").toString();
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
            String query="select text from onlinetestswebsite.questions where type = "+"'"+ typee+ "'"+" ORDER BY RAND()LIMIT 3;";
            preparedStatement = Con.prepareStatement(query);
            RS = preparedStatement.executeQuery();%>
            
            <div style="position:relative;float:right;display:inline; margin-top:50px; margin-right:400px ">
                <fieldset>
                    <legend style="color:#191970;font-size:30px;"><b><%= typee %> exam</b></legend>
                    <p style="font-size:25px; font-weight:bold;color:#191970;"><ins> Questions</ins></p>
                    <form action="addNewCandidate" name = "myform"><br>
                <% 
                ArrayList<String> arr= new ArrayList();
                int i=0;
                while(RS.next())
                { i++;
                  arr.add(RS.getString("text"));
                %>
                    <label style="font-size:17px;"><%=RS.getString("text") %></label><br><br>
                    <input type="text" name="<%= "textBox"+i %>" placeholder="Your answer" style="height:50px; width:500px"><br><br>
                <% }
                %>
                            <input type="submit" value="Get Result" onclick="validate4()"style="height:40px; width:150px; background-color:#191970;color:rgb(300,300,300); font-weight:bold;font-size:20px">
                        </form>
                    </fieldset>
             </div>
            <%
                HttpSession s = request.getSession(true);
                for(int j=0; j<arr.size();j++){
                    s.setAttribute("Q"+j , arr.get(j));
                }
                s.setAttribute("typ", typee);
                s.setAttribute("mai", maill);
            %>                
            <script type="text/javascript" src="javascript.js"></script>
            
    </body>
</html>
