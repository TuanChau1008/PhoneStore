<%-- 
    Document   : createproduct
    Created on : Mar 12, 2021, 5:05:12 PM
    Author     : ADmin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creat Product</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope}">
            <c:set value="${requestScope.ERROR_PRO}" var ="errorpro"/>
            <form action="MainController" method="POST">
                ProductID:<input type="text" name="productID" required="true"/><br>
                <c:if test="${errorpro != null}">
                    ${errorpro.productIDErr}</br>
                </c:if>
                ProductName:<input type="text" name="productName" required="true"/><br>
                <c:if test="${errorpro != null}">
                    ${errorpro.productNameErr}</br>
                </c:if>
                Price:<input type="text" name="price" required="true"/><br>
                <c:if test="${errorpro != null}">
                    ${errorpro.price}</br>
                </c:if>
                Quantity:<input type="text" name="quantity" required="true"/><br>
                <c:if test="${errorpro != null}">
                    ${errorpro.quantityErr}</br>
                </c:if>
                <input type ="submit" value="CreatePro" name="action"/>
                <a href="createproduct.jsp">Reset</a>
            </form>
        </c:if>
        <c:if test="${empty sessionScope}">
            <a href="login.jsp">LOGIN</a>
        </c:if>
        
    </body>
</html>
