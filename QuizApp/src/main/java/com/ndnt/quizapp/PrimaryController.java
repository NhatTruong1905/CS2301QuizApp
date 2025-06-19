package com.ndnt.quizapp;

import com.ndnt.utils.MyAlert;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {

    public void handleQuestionManager(ActionEvent event) throws IOException {
        Scene scene = new Scene(new FXMLLoader(App.class.getResource("question.fxml")).load());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quiz App");
        stage.show();
    }

    public void handlePratice(ActionEvent event) {
        MyAlert.getInstance().showMsg("Coming soon...");
    }

    public void handleTest(ActionEvent event) {
        MyAlert.getInstance().showMsg("Coming soon...");
    }

    public void handleSignUp(ActionEvent event) {
        MyAlert.getInstance().showMsg("Coming hehe...");
    }

    public void handleSignIn(ActionEvent event) {
        MyAlert.getInstance().showMsg("Coming soon...");
    }
}
