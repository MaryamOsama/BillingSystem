<%-- 
    Document   : newRatePlan
    Created on : Mar 23, 2018, 8:53:20 AM
    Author     : maryam
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="dbconn.DBConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!String query, query2, query3,pkg,service,payment,fu,price,trzone,tizone;
    Statement stmt = null, stmt2 = null, stmt3 = null;
    ResultSet services, services_usage, packages;
    int package_id;
%>
<%
    Connection conn = DBConnector.getConnection();
    query = "select * from services";
    query2 = "select * from services_usage";
    query3 = "select * from packages";
    stmt = conn.createStatement();
    stmt2 = conn.createStatement();
    stmt3 = conn.createStatement();
    services = stmt.executeQuery(query);
    services_usage = stmt2.executeQuery(query2);
    packages = stmt3.executeQuery(query3);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Profile</title>
    </head>
    <body>
        <div>
            <form method="get" action="../../Profiles">
                <table>
                    <tr><td>Profile Name</td></tr>
                    <tr><td>
                            <input type="text" name="profile_name" required>   
                        </td>
                    </tr>
                    <tr><td>Packages</td></tr>
                       <table>
                        <tr>
                        <th>Package</th>
                        <th>Service</th>
                        <th>Payment</th>
                        <th>Free Units</th>
                        <th>Price</th>
                        <th>Tariff Zone</th>
                        <th>Time Zone</th>
                        </tr>
                        <%
                        services_usage.next();
                        packages.next();
                        while(services.next())
                        {
                         pkg=packages.getString("package_name");
                         package_id=packages.getInt("package_id");
                         service=services.getString("service_name");
                         payment=services.getString("r_or_one");
                         fu=services.getString("fu");
                         trzone=services_usage.getString("tariff_zone");
                         tizone=services_usage.getString("time_zone");
                         price=Float.toString(services_usage.getFloat("service_price"));
                         services_usage.next();
                         packages.next();
                         
                        %>
                        <tr>
                            <td><%=pkg%></td>
                            <td><%=service%></td>
                            <td><%=payment%></td>
                            <td><%=fu%></td>
                            <td><%=price%></td>           
                            <td><%=trzone%></td>
                            <td><%=tizone%></td>
                            
                            <td><input type="checkbox" name="selected_pkg" value='<%=package_id%>'/></td>
                        </tr>
                        <%}%>
                     </table>
                    <tr><td>Fees</td></tr>
                    <tr><td>
                            <input type="text" name="fees" required>   
                        </td>
                    </tr>
                    <tr><td>
                            <br><input type="submit" value="submit"/>
                        </td></tr>
                </table>
            </form>    
        </div>
    </body>
</html>
