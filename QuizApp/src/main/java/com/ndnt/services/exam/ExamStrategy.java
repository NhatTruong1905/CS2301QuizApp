/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services.exam;

import com.ndnt.pojo.Exam;
import com.ndnt.pojo.Question;
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
public abstract class ExamStrategy {

    public abstract List<Question> getQuestions() throws Exception;

    public void saveExam(List<Question> questions) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);

        Exam ex = new Exam(questions);
        PreparedStatement stm = conn.prepareCall("INSERT INTO exam(title, created_date) VALUE(?, ?)");
        stm.setString(1, ex.getTitle());
        stm.setString(2, ex.getCreateDate().toString());

        if (stm.executeUpdate() > 0) {
            int exId = -1;
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                exId = rs.getInt(1);
            }

            stm = conn.prepareCall("INSERT INTO exam_question(exam_id, question_id) VALUE(?, ?)");
            stm.setInt(1, exId);

            for (var q : questions) {
                stm.setInt(2, q.getId());
                stm.executeUpdate();
            }

            conn.commit();
        } else {
            conn.rollback();
        }
    }
}
