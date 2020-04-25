/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.FrontEnd;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import online_university.BackEnd.Admin;
import online_university.BackEnd.Authority;
import online_university.BackEnd.Person;
import online_university.BackEnd.Staff;
import online_university.BackEnd.Student;

/**
 *
 * @author UNS_CT
 */
public class LoginAndSignUp extends Application{

    HBox mainPane = new HBox();
    Pane universityPane = new Pane();
    Pane loginPane = new Pane();
    Pane signUpPane = new Pane();
    StackPane stackPaneRight = new StackPane();
    
    Label uNameLabel = new Label("HWU");
    Label universityLabel = new Label("UNIVERSITY");
    Label userNameLabel = new Label("USERNAME");
    Label passWordLabel = new Label("PASSWORD");
    Label signUpLabel = new Label("SIGN UP");
    Label firstNameTopic = new Label("Firstname");
    Label lastNameTopic = new Label("Lastname");
    Label ageTopic = new Label("Age");
    Label userNameTopic = new Label("Username");
    Label passWordTopic = new Label("Password");
    Label emailTopic = new Label("E-Mail");
    Label loginErrorLabel = new Label("Username or Password is wrong");
    Label signUpErrorLabel = new Label("This username has already used");
    Label signUpSuccessLabel = new Label("Sign Up Success");
    
    TextField loginUserName = new TextField();
    PasswordField loginPassWord = new PasswordField();
    Line lineUserName = new Line();
    Line linePassWord = new Line();
    TextField textFirstName = new TextField();
    TextField textLastName = new TextField();
    TextField textAge = new TextField();
    TextField textStudentID = new TextField();
    TextField textUserName = new TextField();
    PasswordField textPassWord = new PasswordField();
    TextField textEmail = new TextField();
    ComboBox<String> genderSelector = new ComboBox<>();
    ComboBox<String> roleSelector = new ComboBox<>();
    
    Button loginButton = new Button("Log in");
    Button signUpButton = new Button("Sign Up");
    Button okButton = new Button("OK");
    
    AlertBox alertBox = new AlertBox();

    public LoginAndSignUp(Stage stage) {
        /// Set up universityPane ///
        universityPane.setPrefSize(640,720);
        universityPane.getStyleClass().add("university-background");
        uNameLabel.getStyleClass().add("u-name-label");
        universityLabel.getStyleClass().add("university-label");
        ImageView universityImage = new ImageView("Images/university_icon.png");
        universityImage.setFitHeight(300);
        universityImage.setFitWidth(300);
        universityPane.getChildren().addAll(uNameLabel,universityLabel,universityImage);
        // Set LayOut //
        universityImage.setLayoutX(170);
        universityImage.setLayoutY(80);
        uNameLabel.setLayoutX(195);
        uNameLabel.setLayoutY(460);
        universityLabel.setLayoutX(150);
        universityLabel.setLayoutY(580);
        
        /// Set up loginPane ///
        loginPane.setPrefSize(640,720);
        loginPane.getStyleClass().add("login-background");
        userNameLabel.getStyleClass().add("topic-label");
        passWordLabel.getStyleClass().add("topic-label");
        loginUserName.getStyleClass().add("login-text-field");
        loginPassWord.getStyleClass().add("login-text-field");
        lineUserName.getStyleClass().add("login-line");
        linePassWord.getStyleClass().add("sign-up-line");
        loginErrorLabel.getStyleClass().add("before-error");
        loginUserName.setPrefSize(300, 50);
        loginPassWord.setPrefSize(300, 50);
        lineUserName.setStartX(170);
        lineUserName.setStartY(245);
        lineUserName.setEndX(470);
        lineUserName.setEndY(245);
        linePassWord.setStartX(170);
        linePassWord.setStartY(395);
        linePassWord.setEndX(470);
        linePassWord.setEndY(395);
        loginButton.setPrefSize(200, 50);
        signUpButton.setPrefSize(200, 50);
        // Add Elements into loginPane //
        loginPane.getChildren().add(userNameLabel);
        loginPane.getChildren().add(passWordLabel);
        loginPane.getChildren().add(loginUserName);
        loginPane.getChildren().add(loginPassWord);
        loginPane.getChildren().add(lineUserName);
        loginPane.getChildren().add(linePassWord);
        loginPane.getChildren().add(loginErrorLabel);
        loginPane.getChildren().add(loginButton);
        loginPane.getChildren().add(signUpButton);
        // Set LayOut //
        userNameLabel.setLayoutX(240);
        userNameLabel.setLayoutY(150);
        passWordLabel.setLayoutX(240);
        passWordLabel.setLayoutY(300);
        loginUserName.setLayoutX(170);
        loginUserName.setLayoutY(200);
        loginPassWord.setLayoutX(170);
        loginPassWord.setLayoutY(350);
        loginErrorLabel.setLayoutX(210);
        loginErrorLabel.setLayoutY(420);
        loginButton.setLayoutX(220);
        loginButton.setLayoutY(500);
        signUpButton.setLayoutX(220);
        signUpButton.setLayoutY(560);
        
        /// Set up signUpPane ///
        signUpPane.setPrefSize(640,720);
        signUpPane.getStyleClass().add("login-background");
        signUpLabel.getStyleClass().add("sign-up-label");
        textFirstName.getStyleClass().add("sign-up-text-field");
        textLastName.getStyleClass().add("sign-up-text-field");
        textAge.getStyleClass().add("sign-up-text-field");
        textAge.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textAge.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }

        }); 
        textUserName.getStyleClass().add("sign-up-text-field");
        textPassWord.getStyleClass().add("sign-up-text-field");
        textEmail.getStyleClass().add("sign-up-text-field");
        firstNameTopic.getStyleClass().add("sign-up-topic");
        lastNameTopic.getStyleClass().add("sign-up-topic");
        ageTopic.getStyleClass().add("sign-up-topic");
        userNameTopic.getStyleClass().add("sign-up-topic");
        passWordTopic.getStyleClass().add("sign-up-topic");
        emailTopic.getStyleClass().add("sign-up-topic");
        signUpErrorLabel.getStyleClass().add("before-error");
        signUpSuccessLabel.getStyleClass().add("before-success");
        textFirstName.setPrefSize(240, 35);
        textLastName.setPrefSize(240, 35);
        textAge.setPrefSize(240, 35);
        textUserName.setPrefSize(240, 35);
        textPassWord.setPrefSize(240, 35);
        textEmail.setPrefSize(240, 35);
        roleSelector.getItems().addAll("Student","Teacher","Admin");
        roleSelector.setValue("Student");
        genderSelector.getItems().addAll("Female","Male");
        genderSelector.setValue("Female");
        okButton.setPrefSize(150, 50);
        ImageView backButton = new ImageView("Images/back.png");
        backButton.setFitHeight(50);
        backButton.setFitWidth(50);
        // Add Elements into signUpPane //
        signUpPane.getChildren().add(signUpLabel);
        signUpPane.getChildren().add(textFirstName);
        signUpPane.getChildren().add(textLastName);
        signUpPane.getChildren().add(textAge);
        signUpPane.getChildren().add(textUserName);
        signUpPane.getChildren().add(textPassWord);
        signUpPane.getChildren().add(textEmail);
        signUpPane.getChildren().add(firstNameTopic);
        signUpPane.getChildren().add(lastNameTopic);
        signUpPane.getChildren().add(ageTopic);
        signUpPane.getChildren().add(userNameTopic);
        signUpPane.getChildren().add(passWordTopic);
        signUpPane.getChildren().add(emailTopic);
        signUpPane.getChildren().add(roleSelector);
        signUpPane.getChildren().add(genderSelector);
        signUpPane.getChildren().add(signUpErrorLabel);
        signUpPane.getChildren().add(signUpSuccessLabel);
        signUpPane.getChildren().add(okButton);
        signUpPane.getChildren().add(backButton);
        // Set LayOut //
        signUpLabel.setLayoutX(150);
        signUpLabel.setLayoutY(50);
        textFirstName.setLayoutX(150);
        textFirstName.setLayoutY(170);
        textLastName.setLayoutX(150);
        textLastName.setLayoutY(242);
        textAge.setLayoutX(150);
        textAge.setLayoutY(315);
        textUserName.setLayoutX(150);
        textUserName.setLayoutY(387);
        textPassWord.setLayoutX(150);
        textPassWord.setLayoutY(460);
        textEmail.setLayoutX(150);
        textEmail.setLayoutY(535);
        firstNameTopic.setLayoutX(150);
        firstNameTopic.setLayoutY(140);
        lastNameTopic.setLayoutX(150);
        lastNameTopic.setLayoutY(212);
        ageTopic.setLayoutX(150);
        ageTopic.setLayoutY(285);
        userNameTopic.setLayoutX(150);
        userNameTopic.setLayoutY(357);
        passWordTopic.setLayoutX(150);
        passWordTopic.setLayoutY(430);
        emailTopic.setLayoutX(150);
        emailTopic.setLayoutY(505);
        roleSelector.setLayoutX(150);
        roleSelector.setLayoutY(590);
        genderSelector.setLayoutX(250);
        genderSelector.setLayoutY(590);
        signUpErrorLabel.setLayoutX(400);
        signUpErrorLabel.setLayoutY(390);
        signUpSuccessLabel.setLayoutX(470);
        signUpSuccessLabel.setLayoutY(620);
        okButton.setLayoutX(450);
        okButton.setLayoutY(535);
        backButton.setLayoutX(20);
        backButton.setLayoutY(10);
        
        stackPaneRight.getChildren().add(loginPane);
        /// Add 2 panes into mainPane ///
        mainPane.getChildren().addAll(universityPane,stackPaneRight);
        
        // Log in Button //
        loginButton.setOnAction(e -> {
            Person user = (Person) Authority.login(loginUserName.getText(), loginPassWord.getText());
            if (user != null) {
                System.out.println(user.getRole() + " LOGIN ");
                if (user.getRole().equals("Student")) {
                    StudentUI s = new StudentUI();
                    s.Start(stage, (Student) user);
                } else if (user.getRole().equals("Staff")) {
                    TeacherUI s = new TeacherUI();
                    s.Start(stage, (Staff) user);
                } else if (user.getRole().equals("Admin")) {
                    AdminUI s = new AdminUI();
                    s.Start(stage, (Admin) user);
                }
            } else {
                loginErrorLabel.getStyleClass().remove(loginErrorLabel.getStyleClass().size()-1);
                loginErrorLabel.getStyleClass().add("after-error");
            }
        });
        
        // signUp Button //
        signUpButton.setOnMouseClicked(e -> {
            signUpErrorLabel.getStyleClass().remove(signUpErrorLabel.getStyleClass().size()-1);
            signUpErrorLabel.getStyleClass().add("before-error");
            // Clear Old Text //
            textFieldSignUpClear();
            stackPaneRight.getChildren().add(signUpPane);
            signUpPane.translateXProperty().set(640);
            changePage(stackPaneRight,signUpPane);  
        });
        
        // back Button //
        backButton.setOnMouseClicked(e -> {
                // Change after error back to before error //
                loginErrorLabel.getStyleClass().remove(loginErrorLabel.getStyleClass().size()-1);
                loginErrorLabel.getStyleClass().add("before-error");
            
                stackPaneRight.getChildren().add(loginPane);
                loginPane.translateXProperty().set(640);
                changePage(stackPaneRight,loginPane);  
        });
        
        // ok Button //
        okButton.setOnAction(e ->{
            int countSignUpTexted = 0;
            boolean[] signUpTextError = new boolean[6];
            for(int i=0;i<6;i++){
                signUpTextError[i] = false;
            }
            
            if(textFirstName.getText().length() == 0){
                countSignUpTexted++;
                signUpTextError[0] = true;
            } 
            if(textLastName.getText().length() == 0){
                countSignUpTexted++;
                signUpTextError[1] = true;
            } 
            if(textAge.getText().length() == 0){
                countSignUpTexted++;
                signUpTextError[2] = true;
            } 
            if(textUserName.getText().length() == 0){
                countSignUpTexted++;
                signUpTextError[3] = true;
            } 
            if(textPassWord.getText().length() == 0){
                countSignUpTexted++;
                signUpTextError[4] = true;
            } 
            if(textEmail.getText().length() == 0){
                countSignUpTexted++;
                signUpTextError[5] = true;
            } 
            
            if(countSignUpTexted == 0){
                if (roleSelector.getValue().equals("Student")) {
                    Student st = new Student(textFirstName.getText(), textLastName.getText(), textAge.getText(), null, textUserName.getText(), textPassWord.getText(), textEmail.getText());
                    st.setGender(genderSelector.getValue());
                    if(!Authority.registor(st)){
                        signUpErrorLabel.getStyleClass().remove(signUpErrorLabel.getStyleClass().size()-1);
                        signUpErrorLabel.getStyleClass().add("after-error");
                        textUserName.clear();
                        textUserName.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
                        textUserName.getStyleClass().add("add-user-text-field-error");
                    }else{
                        textFieldSignUpClear();
                        signUpErrorLabel.getStyleClass().remove(signUpErrorLabel.getStyleClass().size()-1);
                        signUpErrorLabel.getStyleClass().add("before-error");
                        signUpSuccessLabel.getStyleClass().remove(signUpSuccessLabel.getStyleClass().size()-1);
                        signUpSuccessLabel.getStyleClass().add("after-success");
                    }
                }// get role
                else if (roleSelector.getValue().equals("Teacher")) {
                        Staff st = new Staff(textFirstName.getText(), textLastName.getText(), textAge.getText(), null, textUserName.getText(), textPassWord.getText(), textEmail.getText());
                        st.setGender(genderSelector.getValue());
                        if(!Authority.registor(st)){
                            signUpErrorLabel.getStyleClass().remove(signUpErrorLabel.getStyleClass().size()-1);
                            signUpErrorLabel.getStyleClass().add("after-error");
                            textUserName.clear();
                            textUserName.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
                            textUserName.getStyleClass().add("add-user-text-field-error");
                        }else{
                            textFieldSignUpClear();
                            signUpErrorLabel.getStyleClass().remove(signUpErrorLabel.getStyleClass().size()-1);
                            signUpErrorLabel.getStyleClass().add("before-error");
                            signUpSuccessLabel.getStyleClass().remove(signUpSuccessLabel.getStyleClass().size()-1);
                            signUpSuccessLabel.getStyleClass().add("after-success");
                        }
                } else if (roleSelector.getValue().equals("Admin")) {
                        Admin st = new Admin(textFirstName.getText(), textLastName.getText(), textAge.getText(), null, textUserName.getText(), textPassWord.getText(), textEmail.getText());
                        st.setGender(genderSelector.getValue());
                        if(!Authority.registor(st)){
                            signUpErrorLabel.getStyleClass().remove(signUpErrorLabel.getStyleClass().size()-1);
                            signUpErrorLabel.getStyleClass().add("after-error");
                            textUserName.clear();
                            textUserName.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
                            textUserName.getStyleClass().add("add-user-text-field-error");
                        }else{
                            textFieldSignUpClear();
                            signUpErrorLabel.getStyleClass().remove(signUpErrorLabel.getStyleClass().size()-1);
                            signUpErrorLabel.getStyleClass().add("before-error");
                            signUpSuccessLabel.getStyleClass().remove(signUpSuccessLabel.getStyleClass().size()-1);
                            signUpSuccessLabel.getStyleClass().add("after-success");
                        }
                }
            }else{
                alertBox.display("Alert Box", "Please enter all textfield.");
                setSignUpTextError(signUpTextError);
            }
        });
        
        Scene loginScene = new Scene(mainPane);
        loginScene.getStylesheets().add("css/loginSide.css");
        stage.setScene(loginScene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    /// Change Page Function ///
    void changePage(StackPane stackPane,Pane nextPage){
        /// Change Page Animation ///
         Timeline changePage = new Timeline();
         KeyValue kv = new KeyValue(nextPage.translateXProperty(), 0, Interpolator.EASE_IN);
         KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
         changePage.getKeyFrames().add(kf);
         changePage.setOnFinished(e -> {
             stackPane.getChildren().remove(0);
         });
         changePage.play();
    }
    
    /// TextField Sign Up Clear ///
    void textFieldSignUpClear() {
        textFirstName.clear();
        textLastName.clear();
        textAge.clear();
        textUserName.clear();
        textPassWord.clear();
        textEmail.clear();
        roleSelector.setValue("Student");
        genderSelector.setValue("Female");
        textFirstName.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
        textFirstName.getStyleClass().add("add-user-text-field");
        textLastName.getStyleClass().remove(textLastName.getStyleClass().size()-1);
        textLastName.getStyleClass().add("add-user-text-field");
        textAge.getStyleClass().remove(textAge.getStyleClass().size()-1);
        textAge.getStyleClass().add("add-user-text-field");
        textUserName.getStyleClass().remove(textUserName.getStyleClass().size()-1);
        textUserName.getStyleClass().add("add-user-text-field");
        textPassWord.getStyleClass().remove(textPassWord.getStyleClass().size()-1);
        textPassWord.getStyleClass().add("add-user-text-field");
        textEmail.getStyleClass().remove(textEmail.getStyleClass().size()-1);
        textEmail.getStyleClass().add("add-user-text-field");
    }
    
    /// AlertBox ///
    class AlertBox {

        void display(String windowTitle, String confirmLabel) {
            Stage alertWindow = new Stage();
            alertWindow.setTitle(windowTitle);
            alertWindow.initModality(Modality.APPLICATION_MODAL);

            Pane pane = new Pane();
            Button yesButton = new Button("OK");
            Label label = new Label(confirmLabel);
            label.setStyle("-fx-font-size:15; -fx-font-weight: bold");

            yesButton.setPrefSize(75, 50);

            // Add buttons into gridPane //
            label.setLayoutX(12);
            label.setLayoutY(50);
            yesButton.setLayoutX(62.5);
            yesButton.setLayoutY(100);

            // Add gridPane into vBox //
            pane.getChildren().addAll(label, yesButton);
            pane.setPrefSize(200, 200);

            // Add vBox into confirmScene //
            Scene confirmScene = new Scene(pane);

            // Click Yes //
            yesButton.setOnAction(e -> {
                alertWindow.close();
            });

            alertWindow.setScene(confirmScene);
            alertWindow.showAndWait();
        }
    }
    
    /// Set Add User TextError ///
    void setSignUpTextError(boolean[] textError){
        // Set all wrong to be red //
        if(textError[0] == true){
           textFirstName.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
           textFirstName.getStyleClass().add("add-user-text-field-error");
        }else{
           textFirstName.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
           textFirstName.getStyleClass().add("add-user-text-field");
        }
        if(textError[1] == true){
           textLastName.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
           textLastName.getStyleClass().add("add-user-text-field-error");
        }else{
           textLastName.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
           textLastName.getStyleClass().add("add-user-text-field");
        }
        if(textError[2] == true){
           textAge.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
           textAge.getStyleClass().add("add-user-text-field-error");
        }else{
           textAge.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
           textAge.getStyleClass().add("add-user-text-field");
        }
        if(textError[3] == true){
            textUserName.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
            textUserName.getStyleClass().add("add-user-text-field-error");
        }else{
            textUserName.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
            textUserName.getStyleClass().add("add-user-text-field");
        }
        if(textError[4] == true){
            textPassWord.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
            textPassWord.getStyleClass().add("add-user-text-field-error");
        }else{
            textPassWord.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
            textPassWord.getStyleClass().add("add-user-text-field");
        }
        if(textError[5] == true){
            textEmail.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
            textEmail.getStyleClass().add("add-user-text-field-error");
        }else{
            textEmail.getStyleClass().remove(textFirstName.getStyleClass().size()-1);
            textEmail.getStyleClass().add("add-user-text-field");
        }
    }
}
