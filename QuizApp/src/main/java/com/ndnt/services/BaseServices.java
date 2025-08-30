/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services;

import com.ndnt.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class BaseServices<T> {

    public abstract PreparedStatement getStm(Connection conn) throws SQLException;

    public abstract List<T> getResult(ResultSet rs) throws SQLException;

    public List<T> list() throws SQLException { // template method
        // Mo ket noi
        Connection conn = JdbcConnector.getInstance().connect();

        // Truy van
        PreparedStatement stm = getStm(conn);

        return this.getResult(stm.executeQuery());
    }

}
