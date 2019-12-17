<%-- 
    Document   : login
    Created on : Nov 18, 2019, 2:32:28 AM
    Author     : Shrouk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body style="background: url('login2.jpg') no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;">
       <!--<p><%=request.getHeader("Referer")%>)</p>-->
        <div style="position:relative;float:right;display:inline; margin-top:20px; margin-right:450px ">
            <fieldset>
                <legend>Log In Form</legend>
                <form action="checkIfUserExist" name="form">
                    <input type="text" name="email" placeholder="Email or phone" style="height:20px; width:200px;"> &nbsp; &nbsp; &nbsp; &nbsp;
                    <input type="password" name="pass" placeholder="Password" style="height:20px; width:200px;">
                    <input type="submit" value="Log In" onclick="validate2()" style="color:rgb(300,300,300); background-color:#6495ED; height:30px; width:70px; font-weight:bold">
                </form>
            </fieldset>
        </div>
        
        <script type="text/javascript" src="javascript.js"></script>
        <%
            
            if(request.getHeader("referer").contains("signup.jsp"))
            { %>
                <script>
                    alert("You already have an account");
                </script>
                
            <% }
            else if(request.getHeader("referer").contains("login.jsp")){%>
                <script>
                    alert("Your password or email is wrong");
                </script>
            <% }
           
             %>
           
    </body>
</html>
