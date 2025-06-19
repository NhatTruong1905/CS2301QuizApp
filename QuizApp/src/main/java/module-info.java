module com.ndnt.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.ndnt.quizapp to javafx.fxml;
    exports com.ndnt.quizapp;
}
