<%-- 
    Document   : candidates.jsp
    Created on : Nov 18, 2019, 3:04:02 AM
    Author     : Shrouk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Candidates Page</title>
    </head>
    <body style="background: url('signup.jpg') no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;">
        
        <form action="index.html"> <input type="submit" value="Log out"/></form>
        <div style="position:relative;float:right;display:inline; margin-top:100px; margin-right:400px ">
            <fieldset>
                <legend style="color:#191970;font-size:30px;"><b>Exams List</b></legend>
                <p style="font-size:25px; font-weight:bold;color:#191970;">Please insert the <ins>number</ins> of exam you want to get</p>
                <form action="checkIfCandidateExist" name = "fform"><br>
                    <label style="font-size:17px;">Enter number <b>1</b> to get <b>Java</b> exam</label><br><br>
                    <label style="font-size:17px;">Enter number <b>2</b> to get <b>Data mining</b> exam</label><br><br>
                    <label style="font-size:17px;">Enter number <b>3</b> to get <b>SQL</b> exam</label><br><br>
                    <input type="text" placeholder="The Number" name="type" style="height:25px; width:200px"><br><br>
                    <input type="submit" value="Show Exam" onclick="validate3()"style="height:40px; width:150px; background-color:#191970;color:rgb(300,300,300); font-weight:bold;font-size:20px">

                </form>
            </fieldset>
        </div>
        
        
        
        <%
        if(request.getHeader("referer").contains("signup.jsp"))
        { %>
            <script>
                alert("You have successfully created an account");
            </script>

        <% }
        else if(request.getHeader("referer").contains("checkIfUserExist")){ %>
            <script>
                alert("You have tested this exam before and unfortunately you can not test it again");
            </script>
        <%}
        %>
               
    <script type="text/javascript" src="javascript.js"></script>       
    </body>
</html>
