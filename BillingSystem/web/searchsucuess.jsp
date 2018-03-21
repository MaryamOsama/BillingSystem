<%-- 
    Document   : searchsucuess
    Created on : Mar 21, 2018, 2:34:39 AM
    Author     : sun com
--%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // PreparedStatement stmt;
    boolean found = false;
    Connection con = (Connection) request.getServletContext().getAttribute("conn");

    String MSISDN = request.getParameter("MSISDN");
    //System.out.println(MSISDN);

    String search = "select * from customers where msisdn='" + MSISDN + "'";

    Statement st = con.createStatement();

    ResultSet rs = st.executeQuery(search);

    while (rs.next()) {
        if (MSISDN.equals(rs.getString(2))) {
            found = true;

            out.println("<br>");
            out.println("<table align='center' border='3'>"
                    + "   <tr>\n"
                    + "        <th>Customer ID</th>\n"
                    + "        <th>MSISDN</th>\n"
                    + "        <th>Date of birth</th>\n"
                    + "        <th>Address</th>\n"
                    + "        <th>Job</th>\n"
                    + "        <th>Bill cycle</th>\n"
                    + "        <th>Profile name</th>\n"
                    + "        <th>Firstname</th>\n"
                    + "        <th>Lastname</th>\n"
                    + "   </tr>   ");

            out.println("<tr>");
            out.print("<td >" + rs.getInt(1) + "</td>");
            out.print("<td >" + rs.getString(2) + "</td>");
            out.print("<td >" + rs.getString(3) + "</td>");
            out.print("<td >" + rs.getString(4) + "</td>");
            out.print("<td >" + rs.getString(5) + "</td>");
            out.print("<td >" + rs.getString(6) + "</td>");
            out.print("<td >" + rs.getString(7) + "</td>");
            out.print("<td >" + rs.getString(8) + "</td>");
            out.print("<td >" + rs.getString(9) + "</td>");
            out.println("</tr>");

            out.println("<table>"
                    + "<br>");

        }
    }

    if (!found) {
        out.println("<html><body>invalid MSISDN(not found)</body></html>");
    }

%>
