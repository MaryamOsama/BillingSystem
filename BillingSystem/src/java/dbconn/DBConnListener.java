/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconn;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author maryam
 */
public class DBConnListener implements ServletContextListener {

     @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context=sce.getServletContext();
        String dburl=context.getInitParameter("dbUrl");
        String dbusername=context.getInitParameter("dbUserName");
        String dbpassword=context.getInitParameter("dbPassword");

        DBConnector.createConnection(dburl, dbusername, dbpassword);
        System.out.println("Connection Establised.........");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBConnector.closeConnection();
    }
}
