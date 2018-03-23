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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author maryam
 */
public class Profiles extends HttpServlet {

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
        Connection conn = DBConnector.getConnection();
        String query = " insert into profile (profile_name,fees) values (?,?)";
        String query3 = " insert into profile_packages (profile_id,package_id) values (?,?)";

        PreparedStatement prepareStatement = null;
        PreparedStatement prepareStatement2 = null;

        prepareStatement = conn.prepareStatement(query);
        prepareStatement2 = conn.prepareStatement(query3);

        String profile_name = request.getParameter("profile_name");
        Float fees = Float.parseFloat(request.getParameter("fees"));
        String package_id[] = request.getParameterValues("selected_pkg");
        PrintWriter out = response.getWriter();

        prepareStatement.setString(1, profile_name);
        prepareStatement.setFloat(2, fees);
        boolean inserted = prepareStatement.execute();
        out.print(inserted);
        int profile_id;
        if (!inserted) {
            Statement stmt = null;
            stmt = conn.createStatement();
            String query2 = "select profile_id from profile where profile_name ='" + profile_name + "'";
            ResultSet rs = stmt.executeQuery(query2);
            rs.next();
            profile_id = rs.getInt(1);
            System.out.print(profile_id);
            int no_pkgs = package_id.length;
            int pkg_id[] = new int[no_pkgs];

            for (int i = 0; i < no_pkgs; i++) {
                pkg_id[i] = Integer.parseInt(package_id[i]);
                out.print(pkg_id[i]);
                prepareStatement2.setInt(1, profile_id);
                prepareStatement2.setInt(2, pkg_id[i]);
                boolean inserted2 = prepareStatement2.execute();

            }
            response.sendRedirect("profile/jsp/profiles.jsp");

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
            Logger.getLogger(Profiles.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Profiles.class.getName()).log(Level.SEVERE, null, ex);
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
