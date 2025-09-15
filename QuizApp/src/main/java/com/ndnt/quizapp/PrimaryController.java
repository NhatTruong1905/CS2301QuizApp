package com.ndnt.quizapp;

import com.ndnt.themes.Theme;
import com.ndnt.utils.MyAlert;
import com.ndnt.utils.MyStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class PrimaryController implements Initializable {

    @FXML
    private ComboBox<Theme> cbThemes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(Theme.values()));
    }

    public void handleChangeThemes(ActionEvent action) {
        this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
    }

    public void handleQuestionManager(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("question");
    }

    public void handlePratice(ActionEvent event) throws IOException {
       MyStage.getInstance().showStage("practice");
    }

    public void handleTest(ActionEvent event) throws IOException {
         MyStage.getInstance().showStage("exam");
    }

    public void handleSignUp(ActionEvent event) {
        MyAlert.getInstance().showMsg("Coming hehe...");
    }

    public void handleSignIn(ActionEvent event) {
        MyAlert.getInstance().showMsg("Coming soon...");
    }

}
