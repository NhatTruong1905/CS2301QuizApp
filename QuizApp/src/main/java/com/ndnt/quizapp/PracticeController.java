/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndnt.quizapp;

import com.ndnt.pojo.Category;
import com.ndnt.pojo.Level;
import com.ndnt.pojo.Question;
import com.ndnt.services.questions.BaseQuestionServices;
import com.ndnt.services.questions.CategoryQuestionServicesDecorator;
import com.ndnt.services.questions.LevelQuestionServicesDecorator;
import com.ndnt.services.questions.LimitedQuestionServicesDecorator;
import com.ndnt.utils.Configs;
import com.ndnt.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PracticeController implements Initializable {

    @FXML
    private TextField txtNum;
    @FXML
    private Text txtContent;
    @FXML
    private Text txtResult;
    @FXML
    private VBox vboxChoices;
    @FXML
    private ComboBox<Category> cbSearchCates;
    @FXML
    private ComboBox<Level> cbSearchLevels;

    private List<Question> questions;
    private int currentQuestion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbSearchCates.setItems(FXCollections.observableList(Configs.cateServices.getCates()));
            this.cbSearchLevels.setItems(FXCollections.observableList(Configs.levelServices.getLevels()));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void handleStart(ActionEvent action) throws SQLException {
        try {
            int num = Integer.parseInt(this.txtNum.getText());

            BaseQuestionServices s = Configs.questionServices;
            
            Category c = this.cbSearchCates.getSelectionModel().getSelectedItem();
            if (c != null) {
                s = new CategoryQuestionServicesDecorator(s, c.getId());
            }

            Level l = this.cbSearchLevels.getSelectionModel().getSelectedItem();
            if (l != null) {
                s = new LevelQuestionServicesDecorator(s, l.getId());
            }

            s = new LimitedQuestionServicesDecorator(s, num);
            questions = s.list();

            this.currentQuestion = 0;
            this.loadQuestion();
        } catch (NumberFormatException ex) {
            MyAlert.getInstance().showMsg("Vui lòng nhập số câu hỏi hợp lệ!");
        }
    }

    private void loadQuestion() {
        Question q = this.questions.get(this.currentQuestion);
        this.txtContent.setText(q.getContent());

        ToggleGroup g = new ToggleGroup();
        vboxChoices.getChildren().clear();
        for (var c : q.getChoices()) {
            RadioButton rdo = new RadioButton(c.getContent());
            rdo.setToggleGroup(g);

            vboxChoices.getChildren().add(rdo);
        }
    }

    public void handleCheck(ActionEvent action) {
        Question q = this.questions.get(this.currentQuestion);
        for (int i = 0; i < q.getChoices().size(); i++) {
            if (q.getChoices().get(i).isCorrect()) {
                RadioButton r = (RadioButton) this.vboxChoices.getChildren().get(i);
                if (r.isSelected()) {
                    this.txtResult.setText("CHÍNH XÁC");
                    this.txtResult.setStyle("-fx-fill: blue");
                } else {
                    this.txtResult.setText("SAI BÉT HEHE ^^!");
                    this.txtResult.setStyle("-fx-fill: red");
                }
            }
        }
    }

    public void handleNext(ActionEvent action) {
        if (this.currentQuestion < questions.size() - 1) {
            this.currentQuestion++;
            this.loadQuestion();
        }
    }

}
