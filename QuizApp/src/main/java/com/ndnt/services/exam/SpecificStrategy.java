/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services.exam;

import com.ndnt.pojo.Question;
import com.ndnt.services.questions.BaseQuestionServices;
import com.ndnt.services.questions.LimitedQuestionServicesDecorator;
import com.ndnt.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class SpecificStrategy extends ExamStrategy {

    private int num;

    public SpecificStrategy(int num) {
        this.num = num;
    }

    @Override
    public List<Question> getQuestions() throws SQLException {
        BaseQuestionServices s = new LimitedQuestionServicesDecorator(Configs.questionServices, num);

        return s.list();
    }

}
