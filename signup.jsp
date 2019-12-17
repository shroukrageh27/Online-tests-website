<%-- 
    Document   : signup
    Created on : Nov 16, 2019, 4:16:15 PM
    Author     : Shrouk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up page</title>
    </head>
    <body style="background: url('signup.jpg') no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;">
        
        <div style="position:relative;float:right;display:inline; margin-top:20px; margin-right:450px ">
                <p style="font-size:40px; font-weight:bold">Create a new account</p>
                <p style="font-size:20px;">It's quick and easy.</p>
                
                <fieldset>
                    <legend>Sign Up Form</legend>
                    <form action="checkIfAccExist" name = "myForm">
                        <input type="text" placeholder="First Name" name="fName" style="height:40px; width:180px">
                        &nbsp; &nbsp;
                        <input type="text" placeholder="Surname" name="sName" style="height:40px; width:180px"><br><br>
                        <input type="text" placeholder="Mobile number or email address" name="email" style="height:40px; width:380px"><br><br>
                        <input type="password" placeholder="New password" name="pass" minlength="8" style="height:40px; width:380px">
                        <p style="font-size:16px; "> <b>Birthday</b> <br>
                            (Age starts from 21 years and not more than 35 years)</p>
                        <select name="Day" style="height:30px; width:55px">
                            <% 
                                for(int i=1; i<=31; i++){
                                    out.print("<option>");
                                    out.print(i); 
                                    out.print("</option>");
                                }
                            %>
                        </select>
                        <select name="Month" style="height:30px; width:75px">
                            <option>January</option>
                            <option>February</option>
                            <option>March</option>
                            <option>April</option>
                            <option>May</option>
                            <option>June</option>
                            <option>July</option>
                            <option>August</option>
                            <option>September</option>
                            <option>October</option>
                            <option>November</option>
                            <option>December</option>
                        </select>
                        <select name="Year" style="height:30px; width:75px">
                            <% 
                                for(int i=1984; i<=1998; i++){
                                    out.print("<option>");
                                    out.print(i); 
                                    out.print("</option>");
                                }
                            %>
                        </select>
                        <p style="font-size:16px; font-weight:bold">Gender</p>
                        <input type="radio" name="gender" value="Female">Female
                        <input type="radio" name="gender" value="Male">Male
                        <br><br>
                        <input type="submit" value="Sign Up" onclick="validate()" style="height:40px; width:150px; background-color:black;color:rgb(300,300,300); font-weight:bold;font-size:20px">
                       
                    </form>
                </fieldset>
        </div>
                        
            <script type="text/javascript" src="javascript.js"></script>
             <%
            
            if(request.getHeader("referer").contains("login.jsp"))
            { %>
                <script>
                    alert("You do not have an account, please create a new account");
                </script>
                
            <% } %>
    </body>
</html>
