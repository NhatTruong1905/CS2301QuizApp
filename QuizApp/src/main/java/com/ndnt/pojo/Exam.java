/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.pojo;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author admin
 */
public class Exam {

    private int id;
    private String title;
    private LocalDate createDate;
    private List<Question> questions;

    public Exam(List<Question> questions) {
        this.title = String.format("Exam-%s", LocalDate.now().toString());
        this.createDate = LocalDate.now();
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

}
