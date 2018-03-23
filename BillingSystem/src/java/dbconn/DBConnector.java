/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author maryam
 */
public class DBConnector {
private static Connection con;

    public static void createConnection(String dbUrl,String dbusername,String dbPassword){
        try {
            Class.forName("org.postgresql.Driver");
            con=DriverManager.getConnection(dbUrl, dbusername, dbPassword);     
        } catch (Exception ex) {
            ex.printStackTrace();
        }    
    }

    public static Connection getConnection(){
        return con;
    }

    public static void closeConnection(){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
        }
    
}    
}
