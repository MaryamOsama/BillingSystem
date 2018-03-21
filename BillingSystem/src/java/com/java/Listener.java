/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author sun com
 */
public class Listener implements ServletContextListener,HttpSessionListener  {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
   
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/billing", "postgres", "root");
            sce.getServletContext().setAttribute("conn", conn);
        } catch (SQLException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
      
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
