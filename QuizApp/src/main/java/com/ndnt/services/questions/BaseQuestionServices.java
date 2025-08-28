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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class BaseQuestionServices {

    public abstract String getSQL(List<Object> params);

    public List<Question> list() throws SQLException {
        // Mo ket noi
        Connection conn = JdbcConnector.getInstance().connect();

        // Truy van
        List<Object> params = new ArrayList<>();
        PreparedStatement stm = conn.prepareCall(this.getSQL(params)); // Thuc thi truy van
        for (int i = 0; i < params.size(); i++) {
            stm.setObject(i + 1, params.get(i));
        }

        ResultSet rs = stm.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }

    public List<Choice> getChoiceByQuestionId(int questionId) throws SQLException {
        // Mo ket noi
        Connection conn = JdbcConnector.getInstance().connect();

        // Truy van
        PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
        stm.setInt(1, questionId);

        ResultSet rs = stm.executeQuery();

        List<Choice> choices = new ArrayList<>();
        while (rs.next()) {
            Choice c = new Choice(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_correct"));
            choices.add(c);
        }
        return choices;
    }   
}
