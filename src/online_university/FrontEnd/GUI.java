/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.FrontEnd;

import java.util.ArrayList;
import java.util.Stack;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import online_university.BackEnd.*;

/**
 *
 * @author USER
 */
public class GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Register s = new Register(stage);
        stage.setScene(s.scene[1]);
        stage.show();
//        stage = new TeacherUI().Start(stage, (Staff) Authority.login("Staff1", "12345"));
//        stage.show();
//        stage = new Stu().Start(stage, (Student) Authority.login("Drgn", "123"));
//        stage.show();
//        stage = new Stu().Start(stage, (Student) Authority.login("Drgn2", "123"));
//        stage.show();
    }

    void test(Stage s) {
        s.setScene(null);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
