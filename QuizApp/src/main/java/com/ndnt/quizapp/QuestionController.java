/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndnt.quizapp;

import com.ndnt.pojo.Category;
import com.ndnt.pojo.Choice;
import com.ndnt.pojo.Level;
import com.ndnt.pojo.Question;
import com.ndnt.services.CategoryServices;
import com.ndnt.services.LevelServices;
import com.ndnt.services.QuestionServices;
import com.ndnt.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionController implements Initializable {

    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private ComboBox<Level> cbLevels;
    @FXML
    private VBox vboxChoice;
    @FXML
    private TextArea txtContent;
    @FXML
    private ToggleGroup toggleChoice;

    private static final CategoryServices cateServices = new CategoryServices();
    private static final LevelServices levelServices = new LevelServices();
    private static final QuestionServices questionServices = new QuestionServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            this.cbCates.setItems(FXCollections.observableList(cateServices.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(levelServices.getLevels()));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addChoice(ActionEvent action) {
        HBox h = new HBox();
        h.getStyleClass().add("Main");

        RadioButton rdo = new RadioButton();
        rdo.setToggleGroup(toggleChoice);
        TextField text = new TextField();

        h.getChildren().addAll(rdo, text);

        this.vboxChoice.getChildren().add(h);
    }

    public void addQuestion(ActionEvent action) {
        try {
            Question.Builder b = new Question.Builder(this.txtContent.getText(),
                    this.cbCates.getSelectionModel().getSelectedItem(),
                    this.cbLevels.getSelectionModel().getSelectedItem());

            for (var c : this.vboxChoice.getChildren()) {
                HBox h = (HBox) c;

                Choice choice = new Choice(((TextField) h.getChildren().get(1)).getText(),
                        ((RadioButton) h.getChildren().get(0)).isSelected());

                b.addChoice(choice);
            }

            questionServices.addQuestion(b.build());
            MyAlert.getInstance().showMsg("Thêm câu hỏi thành công!");
        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Thêm dữ liệu không thành công, lý do: " + ex.getMessage());
        } catch (Exception ex) {
            MyAlert.getInstance().showMsg("Dữ liệu không hợp lệ!!!");
        }
    }
}
