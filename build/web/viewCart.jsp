<%-- 
    Document   : viewCart
    Created on : Jun 19, 2023, 7:49:53 AM
    Author     : thongdhse160015
--%>

<%@page import="java.util.Map"%>
<%@page import="thongdh.dto.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <%
            //1. Customer goes to cart place
            if (session != null) {
                //2. Customer takes his/her cart
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer gets all items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Customer review item
        %>
        <form action="DispatchServlet">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                    %>
                    <tr>
                        <td><%=++count%></td>
                        <td><%=key%></td>
                        <td><%=items.get(key)%></td>
                        <td><input type="checkbox" name="chckItems" 
                                   value="<%=key%>" /></td>
                    </tr>
                    <% }//end traverse Map based on key %>
                    <tr>
                        <td colspan="3">
                            <a href="shopping.html">
                                Add more Books to Your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Items"
                                   name="btAction" />
                        </td>
                    </tr>
                </tbody>
            </table>

        </form>
        <%

                        //4. Cust traverses all item to shop
                        return;
                    }//end items have existed
                }//cart has existed
            }//session has existed
%>
        <h2>
            No cart is existed
        </h2>
    </body>
</html>
