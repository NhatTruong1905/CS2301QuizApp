/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services;

import com.ndnt.pojo.Level;
import com.ndnt.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class LevelServices {

    public List<Level> getLevels() throws SQLException {
        // Mo ket noi
        Connection conn = JdbcConnector.getInstance().connect();

        // Truy van
        Statement stm = conn.createStatement(); // Thuc thi truy van
        ResultSet rs = stm.executeQuery("SELECT * FROM level");

        List<Level> levels = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String note = rs.getString("note");

            Level c = new Level(id, name, note);
            levels.add(c);
        }
        return levels;
    }
}
