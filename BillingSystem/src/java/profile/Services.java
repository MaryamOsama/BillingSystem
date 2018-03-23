/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profile;

import dbconn.DBConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author maryam
 */
public class Services extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.setContentType("text/html;charset=UTF-8");
//          response.sendRedirect("html/usersHTML/registration.html");
            Connection conn = DBConnector.getConnection();
            String query = " insert into services (service_name,r_or_one,fu,fu_description) values (?,?,?,?)";
            String query2 = " insert into services_usage(service_id,service_price,tariff_zone,time_zone) values (?,?,?,?)";
            String query4 = " insert into packages(package_name,service_id) values (?,?)";

            PreparedStatement prepareStatement = null;
            PreparedStatement prepareStatement2 = null;
            PreparedStatement prepareStatement3 = null;
            Statement stmt = null;
            stmt = conn.createStatement();

            prepareStatement = conn.prepareStatement(query);
            prepareStatement2 = conn.prepareStatement(query2);
            prepareStatement3 = conn.prepareStatement(query4);

            String package_name = request.getParameter("package_name");
            String service_name = request.getParameter("service_name");
            String service_price = request.getParameter("service_price");
            String time_zone = request.getParameter("time_zone");
            String tariff_zone = request.getParameter("tariff_zone");
            String r_or_one = request.getParameter("r_or_one");
            String fu = request.getParameter("fu");
            String fu_description = request.getParameter("fu_description");

            prepareStatement.setString(1, service_name);
            prepareStatement.setString(2, r_or_one);
            prepareStatement.setString(3, fu);
            prepareStatement.setString(4, fu_description);
            boolean inserted = prepareStatement.execute();
            System.out.println("inserted " + inserted);

            if (!inserted) {
                String query3 = "select service_id from services where service_name ='" + service_name + "'";
                ResultSet rs = stmt.executeQuery(query3);
                rs.next();
                int service_id = rs.getInt(1);
                System.out.println(service_id);
                prepareStatement2.setInt(1, service_id);
                prepareStatement2.setFloat(2, Float.parseFloat(service_price));
                prepareStatement2.setString(3, tariff_zone);
                prepareStatement2.setString(4, time_zone);
                boolean inserted2 = prepareStatement2.execute();
                System.out.println("inserted " + inserted2);

                prepareStatement3.setString(1, package_name);
                prepareStatement3.setInt(2, service_id);
                
                boolean inserted3 = prepareStatement3.execute();
                System.out.println("inserted " + inserted3);
                response.sendRedirect("profile/jsp/profiles.jsp");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
