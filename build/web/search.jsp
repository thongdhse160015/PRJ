<%-- 
    Document   : search
    Created on : Jun 8, 2023, 7:17:46 AM
    Author     : thongdhse160015
--%>

<%@page import="thongdh.dto.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="<%= request.getParameter("txtSearchValue")%>"/><br/>
            <input type="submit" name="btAction" value="Search"/>
        </form>
        <br/>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH RESULT");
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchServlet"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;
                %>
            <form action="DispatchServlet" method="POST">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername"
                               value="<%= dto.getUsername()%>"/>
                    </td>
                    <td>
                        <input type="text" name="txtPassword"
                               value="<%= dto.getPassword()%>"/>
                    </td>
                    <td>
                        <%= dto.getFullName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }//end user is an admin
                               %>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction"/>
                        <input type="hidden" name="lastSearchValue" value="<%= searchValue%>"/>
                    </td>
                </tr>
            </form>                            
            <%
                }//end get data from result
            %>
        </tbody>
    </table>

    <%
    } else {//result == null
    %>
    <h2>
        No record is matched!!!
    </h2>
    <%
            }
        }//end page is called invalid
%>
</body>
</html>
