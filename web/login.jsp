<%-- 
    Document   : login
    Created on : Mar 10, 2021, 9:12:52 PM
    Author     : ADmin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
    </head>
    <body>
       <form action="MainController" method="POST">
            UserID<input type="text" name = "userID"/></br>
            Password <input type="password" name = "password"/></br>
            <input type = "submit" value="Login" name = "action"/>
            <input type="reset" value ="Reset"/>
        </form>
       <c:if test="${not empty requestScope.ERROR}">
           <font color ="red">
           ${requestScope.ERROR}
           </font>
       </c:if></br>
       <a href="createuser.jsp"> Create New User Member</a></br>
       <a href="LoadProductController">Back To Home</a>
    </body>
</html>
