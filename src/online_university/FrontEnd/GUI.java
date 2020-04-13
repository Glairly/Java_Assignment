/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.FrontEnd;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.control.ScrollBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Register a = new Register(stage);
        stage.setScene(a.scene[1]);
        stage.setTitle("LOGIN_PAGE");
        stage.show();

//        ///////////////////////LoginPage////////////////
//        //*****Check ID and PassWord
//        a.next.setOnAction((ActionEvent t) -> {
//            if (a.p == 0) {
//                a.SetErrpage();
//                a.p++;
//            }
//            if (a.idcheckersTEACHER() == true) {
//                System.out.println(" TEACHER LOGIN ");
//            }
//            else if (a.idcheckersSTUDENT() == true) {
//                System.out.println(" STUDENT LOGIN ");
//            }
//            else if (a.idcheckersADMIN() == true) {
//                System.out.println(" ADMIN LOGIN ");
//            }
//            else{
//                stage.setScene(a.scene[2]);
//                stage.setTitle("Error404_PAGE");
//            }
//        });
//        //////  When clicked registor button
//        a.allbutton[3].setOnAction((ActionEvent t) -> {
//            if (a.i == 0) {
//                a.SetRegis();
//                a.i++;
//            }
//            stage.setScene(a.scene[3]);
//            stage.setTitle("Registor_PAGE");
//        });
//
//        ///////////////ErrorPage//////////////////
//        a.back.setOnAction((ActionEvent t) -> {
//            a.Setbacktostartingpage();
//            stage.setScene(a.scene[1]);
//            stage.setTitle("LOGIN_PAGE");
//        });
//
//        ///////////Registor Page///////////////////
//        ////// Back to Loginpage From RegistorPage
//        a.allbutton[5].setOnAction((ActionEvent t) -> {
//            a.Setbacktostartingpage();
//            a.registerclear();
//             a.alltext[12].setText(""); 
//            stage.setScene(a.scene[1]);
//            stage.setTitle("LOGIN_PAGE");
//        });
//        a.allbutton[4].setOnAction((ActionEvent t) -> {
//            if(a.checkpassWord()){
//                for (int i = 1; i < 8; i++) {
//                    System.out.println(a.allfill[i].getText());
//                }
//                a.alltext[12].setText(" Login Success Press Back To Continues ");  
//                System.out.println(a.comboBox2.getValue());
//                a.registerclear();
//            }
//            else{
//                if (a.p == 0) {
//                a.SetErrpage();
//                a.p++;
//            }
//                a.registerclear();
//                stage.setScene(a.scene[2]);
//                stage.setTitle("Error_PAGE");
//            }
//                
//        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
