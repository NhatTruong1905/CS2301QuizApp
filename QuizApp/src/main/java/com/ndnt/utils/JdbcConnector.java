/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class JdbcConnector {

    private static JdbcConnector intance;
    private final Connection conn;

    // Nap driver ben goi Utils tien ich cho cac lop
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JdbcConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JdbcConnector() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost/quizapp", "root", "root@123");
    }

    public static JdbcConnector getInstance() throws SQLException {
        if (intance == null) {
            intance = new JdbcConnector();
        }
        return intance;
    }

    public Connection connect() {
        return this.conn;
    }

    public void close() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
        }
    }
}
