/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.FrontEnd;

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
//        stage.setScene(a.scene[1]);
//        stage.setTitle("LOGIN_PAGE");
//        stage.show();
        stage = new TeacherUI().Start(stage, (Staff) Authority.login("Staff1", "12345"));
        stage.show();
//        stage = new Stu().Start(stage, (Student) Authority.login("Drgn", "123"));
//        stage.show();
//        stage = new Stu().Start(stage, (Student) Authority.login("Drgn2", "123"));
//        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
