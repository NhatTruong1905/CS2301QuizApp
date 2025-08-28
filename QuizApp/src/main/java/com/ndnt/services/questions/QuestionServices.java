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
public class QuestionServices extends BaseQuestionServices {

    @Override
    public String getSQL(List<Object> params) {
        return "SELECT * FROM question WHERE 1=1";
    }

//    public List<Question> getQuestions(String kw) throws SQLException {
//        // Mo ket noi
//        Connection conn = JdbcConnector.getInstance().connect();
//
//        // Truy van
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE content like concat('%',?,'%')");
//        stm.setString(1, kw);
//
//        ResultSet rs = stm.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
//            questions.add(q);
//        }
//        return questions;
//    }

//    public List<Question> getQuestions(int num) throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
//
//        // Truy van
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
//        stm.setInt(1, num);
//
//        ResultSet rs = stm.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content"))
//                    .addAllChoice(this.getChoiceByQuestionId(rs.getInt("id"))).build();
//            questions.add(q);
//        }
//        return questions;
//    }



}
