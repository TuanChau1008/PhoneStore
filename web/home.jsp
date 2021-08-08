<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : home.jsp
    Created on : Mar 10, 2021, 7:44:41 PM
    Author     : ADmin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME</title>
    </head>
    <body>
        <c:if test="${empty sessionScope}">
            <h1>Welcome To Store</h1>
            <a href="login.jsp">LOGIN</a>
        </c:if>

        <c:if test="${not empty sessionScope}">
            <h1>Welcome: ${sessionScope.USER.fullName}</h1>
            <c:url var="Logout" value="MainController">
                <c:param name="action" value="Logout"></c:param>
            </c:url>
            <a href="${Logout}">LOGOUT</a></br>
        </c:if>

        <form action="MainController" method="GET">
            <input type="text" name="search" value="${param.search}" />
            <input type="submit" name="action" value="Search" />
        </form>

        <c:if test="${not empty requestScope.LIST_PRO}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ProductID</th>
                        <th>ProductName</th>
                        <th>Price</th>
                        <th>Quantity</th>
                            <c:if test="${sessionScope.USER.roleID == 'AD'}">
                            <th>Update</th>
                            <th>Delete</th>
                            </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.LIST_PRO}" var="listproduct" varStatus="counter">
                        <tr>
                            <c:if test="${sessionScope.USER.roleID == 'AD'}">
                        <form action="MainController" method="POST">
                            <td>${listproduct.productID}</td>
                            <td>
                                <input type="text" name="productName" value="${listproduct.productName}" />
                            </td>
                            <td>
                                <input type="text" name="price" value="${listproduct.price}" />
                            </td>
                            <td>
                                <input type="text" name="quantity" value="${listproduct.quantity}" />
                            </td>
                            <td>
                                <input type="hidden" name="productID" value="${listproduct.productID}"/>
                                <input type="submit" name="action" value="Update"/>
                            </td>
                            <td>
                                <input type="hidden" name="productID" value="${listproduct.productID}"/>
                                <input type="submit" name="action" value="Delete"/>
                            </td>
                        </form>
                    </c:if>

                    <c:if test="${sessionScope.USER.roleID != 'AD'}">
                        <form action = "MainController" method="POST">
                            <td>${listproduct.productID}</td>
                            <td>${listproduct.productName}</td>
                            <td>${listproduct.price}</td>
                            <td>${listproduct.quantity}</td>
                            <c:if test = "${sessionScope.USER.roleID == 'CS'}" >
                                <td>

                                    <input type="hidden" name="productID" value="${listproduct.productID}"/>
                                    <input type="submit" name="action" value="AddToCart"/>
                                </td>

                            </c:if> 
                        </form>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>


    <c:if test = "${sessionScope.USER.roleID == 'CS'}" >
        <a href="viewcart.jsp">Cart</a></br>
        <c:if test="${ not empty requestScope.CART_ERROR}">
            <font color ="red">
            ${requestScope.CART_ERROR}
            </font>
        </c:if>
    </c:if> 

</c:if>

<c:if test="${empty requestScope.LIST_PRO}">
    No product available!!!
</c:if></br>

<c:if test="${sessionScope.USER.roleID == 'AD'}">
    <a href="createproduct.jsp">Create Product</a>
</c:if>
</body>
</html>
