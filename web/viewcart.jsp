<%-- 
    Document   : viewcart
    Created on : Mar 11, 2021, 9:59:34 PM
    Author     : ADmin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <c:if test = "${not empty sessionScope.CART }">
            <h1>Your selected phone!</h1>

            <table border="1">
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>ProductID</th>
                        <th>NameProduct</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>TotalPrice</th>
                        <th>Delete</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.CART.cart}" var="cart" varStatus="counter">
                        <c:set var="dto" value="${cart.value}"/>
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.productID}</td>
                            <td>${dto.productName}</td>
                            <td>${dto.price}</td>
                            <td>${dto.quantity}</td>           
                            <td>${dto.quantity * dto.price}</td>
                    <form action="MainController" method="POST">
                        <td>
                            <input type="hidden" name ="id" value="${dto.productID}"/>
                            <input type="submit" name ="action" value="Delete Cart" />
                        </td>          
                    </form>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h3>Total Quantity: ${sessionScope.TOTALQUANTITY}</h3> 
    <h3>Total Price: ${sessionScope.TOTALPRICE}</h3> 

    <form action="MainController" method="POST">
        <input type="submit" name="action" value="Confirm" />
    </form>

</c:if>


<c:if test="${not empty requestScope.MESSAGE}">
    <h2>
        ${requestScope.MESSAGE}
    </h2>
</c:if>

<a href="LoadProductController">Continue To Shopping</a>

</body>
</html>
