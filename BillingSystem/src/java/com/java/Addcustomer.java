package com.java;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Noha
 */
public class Addcustomer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // PreparedStatement stmt = null;

        boolean found = false;
        Statement stmt = null;
        PrintWriter out = response.getWriter();

        try {
          //  Class.forName("org.postgresql.Driver");

           // Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/billing", "postgres", "root");
              Connection con = (Connection) request.getServletContext().getAttribute("conn");

            String MSISDN = request.getParameter("MSISDN");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String date = request.getParameter("date");
            String address = request.getParameter("address");
            String job = request.getParameter("job");
            String Bill_time = request.getParameter("selectbill");
            int profile = Integer.parseInt(request.getParameter("selectpackage"));

            //    String query = "insert into items values (?,?,?,?,?,?)";  
            String search = "select MSISDN from customers";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(search);
            while (rs.next()) {
                if (MSISDN.equals(rs.getString(1))) {
                    found = true;

                }
            }
            if (!found) {

                String query = "insert into customers(msisdn,birthdate,address,job,billcycle,profile_id,fname,lname) values ('" + MSISDN + "','" + date + "','" + address + "','" + job + "','" + Bill_time + "','" + profile + "', '" + fname + "','" + lname + "')";
                stmt = con.createStatement(); 
                int i = stmt.executeUpdate(query);
                out.println(stmt);

                 out.println("<html><body><p> product inserted successfully :D'</p></body></html>");
              //  response.sendRedirect("deletesuccess.jsp");
            }
            if (found) {

                 out.println("<html><body><p>product arealy existed </p></body></html>");
             //   response.sendRedirect("deletefail.jsp");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Addcustomer.class.getName()).log(Level.SEVERE, null, ex);

        
        }
    }
}
