/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndnt.quizapp;

import com.ndnt.pojo.Choice;
import com.ndnt.pojo.Question;
import com.ndnt.services.exam.ExamStrategy;
import com.ndnt.services.exam.ExamTypes;
import com.ndnt.services.exam.FixedStrategy;
import com.ndnt.services.exam.SpecificStrategy;
import com.ndnt.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
public class ExamController implements Initializable {

    @FXML
    ComboBox<ExamTypes> cbTypes;
    @FXML
    TextField txtNum;
    @FXML
    ListView<Question> lvQuestions;

    private Map<Integer, Choice> answer = new HashMap<>();
    private List<Question> questions;
    private ExamStrategy s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbTypes.setItems(FXCollections.observableArrayList(ExamTypes.values()));

        this.txtNum.setVisible(false);
        this.cbTypes.getSelectionModel().selectedItemProperty().addListener(e -> {
            if (this.cbTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC) {
                this.txtNum.setVisible(true);
            } else {
                this.txtNum.setVisible(false);
            }
        });

        this.lvQuestions.setCellFactory(param -> new ListCell<Question>() {
            @Override
            protected void updateItem(Question question, boolean empty) {
                super.updateItem(question, empty);

                if (question == null || empty == true) {
                    setGraphic(null);
                } else {
                    VBox v = new VBox(5);
                    v.setStyle("-fx-border-width:3;-fx-border-color:gray;-fx-padding:2;");

                    Text t = new Text(question.getContent());
                    v.getChildren().add(t);

                    ToggleGroup tgo = new ToggleGroup();
                    for (var c : question.getChoices()) {
                        RadioButton rdo = new RadioButton(c.getContent());
                        rdo.setToggleGroup(tgo);

                        // Xử lý UI
                        if (answer.get(question.getId()) == c) {
                            rdo.setSelected(true);
                        }

                        rdo.setOnAction(e -> {
                            if (rdo.isSelected()) {
                                answer.put(question.getId(), c);
                            }
                        });

                        v.getChildren().add(rdo);
                    }

                    setGraphic(v);
                }
            }

        });
    }

    public void handleStart(ActionEvent action) throws Exception {
        s = new FixedStrategy();
        if (this.cbTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC) {
            s = new SpecificStrategy(Integer.parseInt(this.txtNum.getText()));
        }

        this.questions = s.getQuestions();
        this.lvQuestions.setItems(FXCollections.observableArrayList(this.questions));
    }

    public void handleMark(ActionEvent action) {
        int count = 0;
        for (var c : this.answer.values()) {
            if (c.isCorrect()) {
                count++;
            }
        }

        MyAlert.getInstance().showMsg(String.format("Bạn trả lời đúng %d/%d", count, this.questions.size()));
    }

    public void handleSave(ActionEvent action) {
        Optional<ButtonType> t = MyAlert.getInstance().showMsg("Bạn chắc chắn lưu đề thi ?", Alert.AlertType.CONFIRMATION);
        if (t.isPresent() && t.get() == ButtonType.OK) {
            try {
                s.saveExam(questions);
                MyAlert.getInstance().showMsg("Lưu đề thi thành công !", Alert.AlertType.INFORMATION);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                MyAlert.getInstance().showMsg("Lưu đề thi thất bại, lý do: " + ex.getMessage(), Alert.AlertType.INFORMATION);
            }
        }

    }
}
