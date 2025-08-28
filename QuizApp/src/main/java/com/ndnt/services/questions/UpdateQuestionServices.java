/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services.questions;

import com.ndnt.pojo.Choice;
import com.ndnt.pojo.Question;
import com.ndnt.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class UpdateQuestionServices {

    public void addQuestion(Question q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());
        if (stm.executeUpdate() > 0) {
            int questionId = -1;
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                questionId = rs.getInt(1);
            }
            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";
            stm = conn.prepareCall(sql);
            for (Choice c : q.getChoices()) {
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, questionId);
                stm.executeUpdate();
            }
            conn.commit();
        } else {
            conn.rollback();
        }
    }

    public boolean deleteQuestion(int id) throws SQLException {
        // Mo ket noi
        Connection conn = JdbcConnector.getInstance().connect();
        // Truy van
        PreparedStatement stm = conn.prepareCall("DELETE FROM question WHERE id=?");
        stm.setInt(1, id);
        return stm.executeUpdate() > 0;
    }

}
