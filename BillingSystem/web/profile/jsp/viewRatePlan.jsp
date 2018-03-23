<%-- 
    Document   : viewRatePlan
    Created on : Mar 23, 2018, 1:01:03 PM
    Author     : maryam
--%>

<%@page import="java.sql.Connection"%>
<%@page import="dbconn.DBConnector"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!String query,query2,query3,query4, pkg,service,payment,fu,price,trzone,tizone,profile_name,fees;
    Statement  stmt4 = null,stmt=null,stmt2=null,stmt3=null;
    ResultSet profiles,profile_packages,services,packages;
    int pkg_id,prf_id,srv_id;
%>
<%
    RequestDispatcher r=request.getRequestDispatcher("profiles.jsp");
    r.include(request, response);
    Connection conn = DBConnector.getConnection();
    
    query4 = "select * from profile";
    
    stmt = conn.createStatement();
    stmt2 = conn.createStatement();
    stmt3 = conn.createStatement();
    stmt4 = conn.createStatement();

    
    profiles = stmt4.executeQuery(query4);
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Rate Plans</title>
    </head>
    <body>
        <div>
            <table border="1">
                        <tr>
                        <th></th>                            
                        <th></th>                            
                        <th>Profile</th>                            
                        <th>Fees</th>                            
                        <th>Minutes to any number in Egypt</th>
                        <th>Additional Minutes</th>
                        <th>SMS to any number in Egypt</th>
                        <th>Additional SMS</th>
                        </tr>
                        <%
                       
                        while(profiles.next())
                        {
                         prf_id=profiles.getInt("profile_id");
 //                        out.print("profile_id "+prf_id);                         
                         profile_name=profiles.getString("profile_name");
                         fees=Float.toString(profiles.getFloat("fees"));   
                        %>
                        <tr>
                            <td><a href="#"><img src="../../imgs/edit.png"></a></td>
                            <td><a href="#"><img src="../../imgs/delete.png"></a></td>
                            <td><%=profile_name%></td>
                            <td><%=fees%></td>
                            
                        <%  query="select package_id from profile_packages where profile_id='"+prf_id+"'";
                            profile_packages=stmt.executeQuery(query); 
                           
                            while(profile_packages.next())
                            {
                             pkg_id=profile_packages.getInt("package_id");
                             query2="select service_id from packages where package_id='"+pkg_id+"'";    
                             packages=stmt2.executeQuery(query2);
                             packages.next();
                             srv_id=packages.getInt("service_id");
                             query3="select * from services,services_usage where  services.service_id=services_usage.service_id and services.service_id='"+srv_id+"'";
                             services=stmt3.executeQuery(query3);
                             services.next();
                             fu=services.getString("fu");
                             price=Float.toString(services.getFloat("service_price"));
                             %>
                             <td><%=fu%></td>
                             <td><%=price%></td>
                             <%
//                             out.print("fu "+fu+" price "+price+"   ");
//                             out.print("package_id "+pkg_id+" ");
//                             out.print("service id "+srv_id+" ");
                            }
                          
                        }
                         %>

                        </tr>
                     </table>
        </div>
    </body>
</html>
