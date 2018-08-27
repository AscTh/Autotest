package sample;

import com.jfoenix.controls.*;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tests.LoginTest;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static tests.FunctionalTest.clear;
import static tests.FunctionalTest.tearDown;

public class Controller implements Initializable {

    @FXML
    public JFXDatePicker datePicter;
    @FXML
    public JFXToggleButton default_button;
    @FXML
    private JFXButton create_chain, publish_plans, stop;
    @FXML
    private JFXTextArea result_console;
    @FXML
    public JFXTextField login;
    @FXML
    private JFXPasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeView();
    }

    @FXML
    public void btn_stop() {
        try {
            clear();
            tearDown();
        } catch (Exception ex) {
            View.getResult_console().setText(ex.toString());
        }
    }

    @FXML
    public void btn_create_chain() {
        Task task = new Task() {
            @Override
            protected Object call() throws IOException, InterruptedException {
                LoginTest.create_chain();
                return null;
            }
        };
        new Thread(task).start();
    }

    @FXML
    public void btn_publish_plans() {
        Task task = new Task() {
            @Override
            protected Object call() throws InterruptedException {
                try {
                    LoginTest.publish_plans();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    private void initializeView() {
        View.setCreate_chain(create_chain);
        View.setPublish_plans(publish_plans);
        View.setStop(stop);
        View.setResult_console(result_console);
        View.setLogin(login);
        View.setPassword(password);
        View.setDatePicker(datePicter);
        View.setDefault_button(default_button);
    }

    @FXML
    public void toggleButton() {
        if (View.getDefault_button().isSelected()) {
            View.getLogin().setText("magomedovtsh");
            View.getPassword().setText("AscTh8565346");
            View.getDatePicker().setValue(LocalDate.now());
        } else {
            View.getLogin().clear();
            View.getPassword().clear();
            View.getDatePicker().setValue(null);
        }
    }
}