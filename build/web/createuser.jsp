<%-- 
    Document   : createuser
    Created on : Mar 11, 2021, 12:48:47 PM
    Author     : ADmin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
    <c:set value="${requestScope.ERROR}" var="error"/>
    <form action ="MainController" method="POST">
        UserID:    <input type="text" name="userID" required="true"/><br>
        <c:if test="${error != null}">
            ${error.userIDErr}</br>
        </c:if>
        FullName:  <input type="text" name="fullName"/><br>
        <c:if test="${error != null}">
            ${error.fullNameErr}</br>
        </c:if>
        ROLE       <input type="text" name="roleID"/><br>
        <c:if test="${error != null}">
            ${error.roleIDErr}</br>
        </c:if>
        Password:   <input type="password" name="password" required="true"/><br>
        Confirm:   <input type="password" name="confirm" required="true"/><br>
        <c:if test="${error != null}">
            ${error.password}</br>
        </c:if>
        <input type ="submit" value="Create" name="action"/><br>
        <input type="reset" value="Reset"><br>
    </form>
</body>
</html>
