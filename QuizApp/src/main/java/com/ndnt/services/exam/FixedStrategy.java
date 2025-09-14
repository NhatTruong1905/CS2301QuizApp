/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.services.exam;

import com.ndnt.pojo.Question;
import com.ndnt.services.questions.BaseQuestionServices;
import com.ndnt.services.questions.LevelQuestionServicesDecorator;
import com.ndnt.services.questions.LimitedQuestionServicesDecorator;
import com.ndnt.utils.Configs;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class FixedStrategy extends ExamStrategy {

    @Override
    public List<Question> getQuestions() throws Exception {
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < Configs.RATES.length; i++) {
            BaseQuestionServices s = new LimitedQuestionServicesDecorator(new LevelQuestionServicesDecorator(Configs.questionServices, i + 1), (int) (Configs.NUM_QUESTIONS * Configs.RATES[i]));

            questions.addAll(s.list());
        }

        return questions;
    }

}
