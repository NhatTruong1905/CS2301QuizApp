/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.utils;

import com.ndnt.services.CategoryServices;
import com.ndnt.services.LevelServices;
import com.ndnt.services.questions.BaseQuestionServices;
import com.ndnt.services.questions.QuestionServices;
import com.ndnt.services.questions.UpdateQuestionServices;

/**
 *
 * @author admin
 */
public class Configs {

    public static final LevelServices levelServices = new LevelServices();
    public static final BaseQuestionServices questionServices = new QuestionServices();
    public static final UpdateQuestionServices uQuestionServices = new UpdateQuestionServices();
    public static final CategoryServices cateServices = new CategoryServices();

}
