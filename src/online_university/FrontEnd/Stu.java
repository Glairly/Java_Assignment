package online_university.FrontEnd;

import java.util.ArrayList;
import java.util.Timer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import online_university.BackEnd.*;

public class Stu extends Application /*implements EventHandler<ActionEvent>*/ {

    Button Im, Vf, Cs, DCs, Score, Join;
    Button check;
    Button Logout, testButton;
    Label lb1;
    Label CurrentCourse = new Label("Current Registered Courses");

    Label lb2, lb3, lbe, lb5, lb6, lb7, lb8, lb9, lba, lbb, lbc;

    Label Nametext = new Label("Name :");
    Label LNametext = new Label("Last Name :");
    Label IDtext = new Label("ID :");
    Label Agetext = new Label("Age :");
    Label Emailtext = new Label("E-Mail :");

    Label name = new Label();
    Label lastname = new Label();
    Label ID = new Label();
    Label Age = new Label();
    Label Email = new Label();
    Label CourseAdded = new Label();
    TableView<Course> Course;
    TableView<Course> AddC;
    TableView<Course> JoinTableView;
    TableView<Student> AttendTableView;

    private TextField tfName = new TextField();
    private TextField tfID = new TextField();
    private TextField tfLastname = new TextField();
    private TextField tfAge = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfEm = new TextField();
    private TextField tfGna = new TextField();

    Student Myself;
    ObservableList<Course> addCourse_Data;

    public static void main(String[] args) {
        launch(args);
    }

    /////////////////////////////////////////////// Main ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage stage) throws Exception {
        // stop query
        stage.setOnCloseRequest(e -> {
            session_Timer.cancel();
            session_Timer.purge();
        });

        Myself = (Student) Authority.login("FoyFoy", "yoFyoF");

        stage.setTitle("Student");
        stage.setResizable(false);
        Vf = new Button("   Edit Profile here  ");
        Tooltip tooltip = new Tooltip("Edit button");
        Tooltip.install(Vf, tooltip);

        String CssProps = "-fx-text-fill:#000000;"
                + "-fx-font-weight: bold;"
                + "-fx-font-size:12pt;"
                + "-fx-background-color:linear-gradient(to bottom right,#95d7e7,#b6f1ff);";

        Vf.setStyle(CssProps);

        Join = new Button("Join Sessions");
        testButton = new Button("Test here");
        Score = new Button("View Score");
        Im = new Button("Change Profile Image");
        Cs = new Button(" Add Course ");
        DCs = new Button(" Remove Course ");
        Logout = new Button("Log Out");
        HBox hb = new HBox(Vf);

        Cs.setPrefSize(150, 50);
        DCs.setPrefSize(150, 50);
        Join.setPrefSize(150, 50);

        Label stuinfo = new Label("  Student Profile");
        stuinfo.setStyle("-fx-text-fill:#ffffff;-fx-font-weight: bold;-fx-font-size:29pt");
        VBox StuInfo = new VBox(stuinfo);
        StuInfo.setPrefSize(325, 75);
        StuInfo.setStyle("-fx-background-color:linear-gradient(to bottom right,#0f3580 0%,#154dba 50%,#0f3580 100%)");//****************///////

        StackPane forEditButton = new StackPane();
        forEditButton.getChildren().add(Vf);
        forEditButton.setLayoutY(220);
        forEditButton.setPrefSize(325, 75);

        StackPane editTextPane = new StackPane();
        VBox edittextvb = new VBox();
        edittextvb.setStyle("-fx-background-color:linear-gradient(to bottom right,#d1f7ff 20%,#a2edff 50%,#d1f7ff 80%)");

        edittextvb.setPrefSize(325, 75);
        editTextPane.getChildren().add(edittextvb);
        editTextPane.setLayoutY(220);

        Im.setLayoutX(200);
        Im.setLayoutY(200);
        GridPane gp = new GridPane();
        gp.setHgap(5);
        gp.setVgap(5);
        Logout.setLayoutX(10);
        Logout.setLayoutY(650);
        Logout.setPrefSize(300, 50);
        Cs.setLayoutX(1100);
        Cs.setLayoutY(110);
        DCs.setLayoutX(1100);
        DCs.setLayoutY(190);
        Join.setLayoutX(1100);
        Join.setLayoutY(600);
        gp.setLayoutX(0);
        gp.setLayoutY(80);

        Nametext.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        LNametext.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        IDtext.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        Agetext.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        Emailtext.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        StackPane fortest = new StackPane();
        fortest.getChildren().add(StuInfo);
        gp.add(Nametext, 2, 2);
        gp.add(name, 3, 2);
        name.setText(Myself.getFirstName());

        gp.add(LNametext, 2, 3);
        gp.add(lastname, 3, 3);
        lastname.setText(Myself.getLastName());

        gp.add(Agetext, 2, 4);
        gp.add(Age, 3, 4);
        Age.setText(Myself.getAge());

        gp.add(IDtext, 2, 5);
        gp.add(ID, 3, 5);
        ID.setText(Myself.getStudentId());

        gp.add(Emailtext, 2, 6);
        gp.add(Email, 3, 6);
        Email.setText(Myself.getEmail());

        lb2 = new Label("Student Profile Information");
        lb2.setLayoutX(0);
        lb2.setLayoutY(0);
        Course = new TableView<>();
        Course.setPrefSize(700, 550);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- TableColumn --------------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        TableColumn<Course, String> CourseNameColumn = new TableColumn<>("Course Name");
        CourseNameColumn.setMinWidth(100);
        CourseNameColumn.setMaxWidth(250);
        CourseNameColumn.setCellValueFactory(new PropertyValueFactory<>("UserName"));

        TableColumn<Course, String> CourseColumn = new TableColumn<>("Class Description");
        CourseColumn.setMinWidth(350);
        CourseColumn.setMaxWidth(350);
        CourseColumn.setCellValueFactory(new PropertyValueFactory<>("classDescription"));

        TableColumn<Course, ArrayList> TNameColumn = new TableColumn<>("Teacher Name");
        TNameColumn.setMinWidth(150);
        TNameColumn.setMaxWidth(250);
        TNameColumn.setCellValueFactory(new PropertyValueFactory<>("staffs"));

        TableColumn<Course, ArrayList> ScoreColumn = new TableColumn<>("Grade");
        ScoreColumn.setMinWidth(100);
        ScoreColumn.setMaxWidth(100);
        ScoreColumn.setCellValueFactory(new PropertyValueFactory<>("students"));

        Course.getColumns().addAll(CourseNameColumn, CourseColumn, TNameColumn, ScoreColumn);
        for (Course c : Myself.getCourse()) {
            Course.getItems().add(c);
        }

        Course.setLayoutX(375);
        Course.setLayoutY(100);

        CurrentCourse.setLayoutX(350);
        CurrentCourse.setLayoutY(10);
        CurrentCourse.setStyle("-fx-text-fill:#000000;-fx-font-weight: bold;-fx-font-size:29pt");

        VBox CurrentcourseText = new VBox();
        CurrentcourseText.setPrefSize(1280, 75);
        CurrentcourseText.setStyle("-fx-background-color:linear-gradient(rgba(65,87,156,1) 0%,rgba(194,236,255,1)50%,rgba(0,212,255,100) 100%)");

        Button confirmEditButton = new Button("Confirm to edit");

        Button CancleEditButton = new Button("Cancle");

        HBox ConfAndCancleHBox = new HBox();
        ConfAndCancleHBox.getChildren().addAll(CancleEditButton, confirmEditButton);
        ConfAndCancleHBox.setSpacing(10);
        ConfAndCancleHBox.setVisible(false);

        ConfAndCancleHBox.setLayoutX(150);
        ConfAndCancleHBox.setLayoutY(450);

        GridPane editPane = new GridPane();
        editPane.setAlignment(Pos.CENTER);
        editPane.setHgap(5.5);
        editPane.setVgap(5.5);
        editPane.setPadding(new Insets(11, 11, 11, 11));
        editPane.add(new Label("Name"), 0, 0);
        editPane.add(tfName, 1, 0);
        editPane.add(new Label("LastName"), 0, 1);
        editPane.add(tfLastname, 1, 1);
        editPane.add(new Label("Age"), 0, 2);
        editPane.add(tfAge, 1, 2);
        editPane.add(new Label("E-mail"), 0, 3);
        editPane.add(tfEm, 1, 3);
        editPane.setVisible(false);

        editPane.setLayoutX(0);
        editPane.setLayoutY(300);

        ///////////////////////////////          Edit profile button     ////////////////////////////////////////////////////////
        Vf.setOnAction(e -> {
            editPane.setVisible(true);
            ConfAndCancleHBox.setVisible(true);
            CancleEditButton.setOnAction(eh -> {
                editPane.setVisible(false);
                ConfAndCancleHBox.setVisible(false);
            });
            confirmEditButton.setOnAction(eh2 -> {
                display();
                editPane.setVisible(false);
                ConfAndCancleHBox.setVisible(false);
            });

        });
        ///////////////////////////////         Add Course Button      //////////////////////////////////////////////////////////
        Cs.setOnAction(e -> {
            Add();
        });
        //////////////////////////////         Remove Button      ///////////////////////////////////////////////////////////////
        DCs.setOnAction(e -> {
            Remove();
        });

        Join.setOnAction(e -> {
            Joinclass();
        });

        Logout.setOnAction(e -> {
            LogO();
        });

        Pane rootPane = new Pane();

        StackPane EditButtonPane = new StackPane();
        EditButtonPane.getChildren().add(hb);
        EditButtonPane.setLayoutX(10);
        EditButtonPane.setLayoutY(450);
        Vf.setPrefSize(300, 50);

        Scene scene = new Scene(rootPane);

        VBox Backgroundleft = new VBox();
        Backgroundleft.setPrefSize(325, 720);
        Backgroundleft.setStyle("-fx-background-color:linear-gradient(to  bottom right,#c9deff,#4e80cf)");
        StackPane Background = new StackPane();
        Background.getChildren().add(Backgroundleft);

        rootPane.getChildren().addAll(CurrentcourseText, Backgroundleft, editPane, fortest, Logout, gp, Course, Cs, DCs, Join, CurrentCourse,
                EditButtonPane, editTextPane, ConfAndCancleHBox, forEditButton);

        rootPane.setStyle("-fx-background-color:linear-gradient(rgba(0,212,255,100), rgba(253,255,180,100))");
        rootPane.setPrefSize(1280, 720);
        stage.setTitle("Student : Home");
        stage.setScene(scene);
        stage.show();
    }

    public void update(Course c) {
        API.saveToDatabase(Myself);
        if (c != null) {
            API.saveToDatabase(c);
        }
    }

    public ObservableList<Course> getCourse() {

        ObservableList<Course> CourseTable = FXCollections.observableArrayList();

        // check course before add to table
        var allC = API.getAllCourse();

        if (allC != null) {
            for (Course c : allC) {
/////////////////// make all course in main stage is not exist -------------------------------------------------                
                boolean isExist = false;
                /////    check if Course table on main stage is not blank ---------------------------------------
                if (Myself.getCourse() != null) {
                    for (Course cc : Myself.getCourse()) {
                        if (c.toString().equals(cc.toString())) {
                            isExist = true;
                            break;
                        }
                    }
                }
                if (!isExist) {
                    CourseTable.add(c);
                }
            }
        }
        return CourseTable;

    }

    Stage EditStage, AddCourse;
    Button AddnewCourse;
    Button CancelAddCourse;
    Button DoneAddCourse;

    ///////////////////////////////////////////////// Add course button clicked /////////////////////////////////////////////////////////////
    private void Add() {

        AddCourse = new Stage();
        AddCourse.initModality(Modality.APPLICATION_MODAL);
        AddCourse.setResizable(false);
        AddC = new TableView<>();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- TableColumn -----------------------------------------------------------//
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        TableColumn<Course, String> CourseNameColumn = new TableColumn<>("Course Name");
        CourseNameColumn.setMinWidth(200);
        CourseNameColumn.setCellValueFactory(new PropertyValueFactory<>("UserName"));

        TableColumn<Course, String> CourseColumn = new TableColumn<>("Description");
        CourseColumn.setMinWidth(200);
        CourseColumn.setCellValueFactory(new PropertyValueFactory<>("classDescription"));

        TableColumn<Course, ArrayList> TNameColumn = new TableColumn<>("Teacher Name");
        TNameColumn.setMinWidth(200);
        TNameColumn.setCellValueFactory(new PropertyValueFactory<>("staffs"));

        AddC = new TableView<>();
        addCourse_Data = getCourse();
        AddC.setItems(addCourse_Data);

        AddC.getColumns().addAll(CourseNameColumn, CourseColumn, TNameColumn);

        AddCourse.setTitle("Add course");
        AddnewCourse = new Button("Add");
        CancelAddCourse = new Button("Cancel");
        DoneAddCourse = new Button("Close");

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Text Field (Can't do it) -------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        TextField searchField;

        searchField = new TextField();
        searchField.setLayoutX(20);
        searchField.setLayoutY(5);
        searchField.setPromptText("Search Course");

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- HBox for button-----------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        HBox Addhb = new HBox(AddnewCourse, DoneAddCourse);
        Addhb.setSpacing(5);
        GridPane Add = new GridPane();
        Add.setLayoutY(28);
        Add.setHgap(20);
        Add.setVgap(10);
        Add.add(AddC, 1, 1);
        Add.add(Addhb, 1, 2);

        StackPane addstackPane = new StackPane();
        addstackPane.getChildren().add(searchField);

        Pane rootPane = new Pane();
        rootPane.getChildren().addAll(Add, searchField);
        Scene Ac = new Scene(rootPane, 645, 500);
        Ac.getStylesheets().add("online_university/TestAgain.css");
        AddCourse.setScene(Ac);
        AddCourse.show();
        DoneAddCourse.setOnAction(e -> AddCourse.close());
        AddnewCourse.setOnAction(e -> ANC());
    }
    static boolean answer;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //-------------------------------- Edit Profile Button Clicked ----------------------------------------------//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void display() {

        Stage CFST = new Stage();
        CFST.setTitle("Confirm");
        CFST.initModality(Modality.APPLICATION_MODAL);
        GridPane cfEdit = new GridPane();
        cfEdit.setAlignment(Pos.CENTER);
        cfEdit.setHgap(5);
        cfEdit.setVgap(5);
        cfEdit.setPadding(new Insets(11, 11, 11, 11));
        cfEdit.add(new Label("         Confirm?"), 1, 1);
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e
                -> {
            name.setText(tfName.getText());
            lastname.setText(tfLastname.getText());
            Age.setText(tfAge.getText());
            Email.setText(tfEm.getText());
            CFST.close();
        });

        noButton.setOnAction(e
                -> {
            CFST.close();
        });
        Scene confirm = new Scene(cfEdit, 250, 100);
        cfEdit.add(yesButton, 1, 2);
        cfEdit.add(noButton, 2, 2);
        CFST.setScene(confirm);
        CFST.showAndWait();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //-------------------------------- Add pressed --------------------------------------------------------------//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void ANC() {
        //AddC.setItems(FXCollections.observableSet(new HashSet<T>()));  
        // course AddC.getSelectionModel().getSelectedItem()
        AddC.getSelectionModel().selectFirst();

        if (AddC.getSelectionModel().getSelectedItem() != null) {
            Course.getItems().add(AddC.getSelectionModel().getSelectedItem());
            Myself.addCourse(AddC.getSelectionModel().getSelectedItem());
            AddC.getSelectionModel().getSelectedItem().addStudent(Myself);
            update(AddC.getSelectionModel().getSelectedItem());
            AddC.getItems().remove(AddC.getSelectionModel().getSelectedItem());
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //-------------------------------- Remove course pressed ----------------------------------------------------//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void Remove() {
        Course.getSelectionModel().selectFirst();
        if (Course.getSelectionModel().getSelectedItem() != null) {
//            AddC.getItems().add(Course.getSelectionModel().getSelectedItem());
            Course.getSelectionModel().getSelectedItem().removeStudent(Myself);
            Myself.removeCourse(Course.getSelectionModel().getSelectedItem());
            System.out.println(Course.getSelectionModel().getSelectedItem().getStudents());
            update(Course.getSelectionModel().getSelectedItem());
            Course.getItems().remove(Course.getSelectionModel().getSelectedItem());
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //-------------------------------- Log out pressed ----------------------------------------------------------//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void LogO() {
        Stage Logoutstage = new Stage();
        Logoutstage.initModality(Modality.APPLICATION_MODAL);
        Logoutstage.setTitle("Confirm Logout");
        GridPane cfLogout = new GridPane();
        cfLogout.setHgap(5);
        cfLogout.setVgap(5);
        cfLogout.setPadding(new Insets(11, 11, 11, 11));
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        Label conf = new Label("Confirm to logout?");
        conf.setLayoutX(10);
        conf.setLayoutY(50);
        StackPane text = new StackPane();
        text.getChildren().add(conf);

        yesButton.setOnAction(e -> {
            Platform.exit();
        });
        noButton.setOnAction(e -> {
            Logoutstage.close();
        });
        cfLogout.add(yesButton, 0, 2);
        cfLogout.add(noButton, 1, 2);
        Pane rootpane = new Pane();
        rootpane.setLayoutX(75);
        rootpane.setLayoutY(25);
        rootpane.getChildren().addAll(text, cfLogout);
        Scene confirm = new Scene(rootpane, 250, 100);
        Logoutstage.setScene(confirm);
        Logoutstage.showAndWait();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //-------------------------------- Join class pressed -------------------------------------------------------//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void Joinclass() {
        Stage selectClassStage = new Stage();
        selectClassStage.setResizable(false);
        selectClassStage.setTitle("Select Class");
        selectClassStage.initModality(Modality.APPLICATION_MODAL);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Table in join sessions ---------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        JoinTableView = new TableView<>();
        JoinTableView.setPrefSize(700, 500);
        JoinTableView.setLayoutX(50);
        JoinTableView.setLayoutY(50);
        //JoinTableView.getItems().add(Course);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- TableColumn --------------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        TableColumn<Course, String> CourseColumn = new TableColumn<>("On going Sessions");
        CourseColumn.setMinWidth(250);
        CourseColumn.setMaxWidth(250);
        CourseColumn.setCellValueFactory(new PropertyValueFactory<>("classDescription"));

        TableColumn<Course, ArrayList> NameColumn = new TableColumn<>("Teacher Name");
        NameColumn.setMinWidth(350);
        NameColumn.setMaxWidth(350);
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("sessions"));

        JoinTableView.getColumns().addAll(CourseColumn, NameColumn);
        ////////////////////////////////////
        // insert ongoin session in table //
        ////////////////////////////////////
        ArrayList<Session> allSS = Session.getSessionByStudent(Myself);
        if (allSS != null) {
            for (Session ss : allSS) {
                JoinTableView.getItems().add(ss.getCourse());
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Button -------------------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Button go = new Button("Join");
        go.setPrefSize(100, 50);

        Button back = new Button("Back");
        back.setPrefSize(100, 50);
        back.setLayoutX(750);
        back.setLayoutY(480);

        go.setOnAction(e -> {
            // pass session
            if (allSS != null) {
                for (Session s : allSS) {
                    if (s.getCourse().toString().equals(JoinTableView.getSelectionModel().getSelectedItem().toString())) {

                        joined(s);
                    }
                }
            }
        });
        back.setOnAction(e -> selectClassStage.close()/*back()*/);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Pane for tableview -------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        StackPane TablePane = new StackPane();
        TablePane.getChildren().add(JoinTableView);
        TablePane.setLayoutX(20);
        TablePane.setLayoutY(20);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Pane for join button -----------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        StackPane ButtonStackPane = new StackPane();
        ButtonStackPane.getChildren().add(go);
        ButtonStackPane.setLayoutX(750);
        ButtonStackPane.setLayoutY(400);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- rootpane -----------------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Pane rootPane = new Pane();
        rootPane.getChildren().addAll(TablePane, ButtonStackPane, back);
        rootPane.setStyle("-fx-background-color:linear-gradient(to bottom right,#404059 50% ,#ffffff 50%,#404059 100%);");

        Scene JoinScene = new Scene(rootPane, 900, 550);
        selectClassStage.setScene(JoinScene);
        selectClassStage.showAndWait();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //-------------------------------- Join button pressed ------------------------------------------------------//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Timer session_Timer = new Timer();
    Session nowSession;
    ObservableList<Student> Attended_Student = FXCollections.observableArrayList();
    coresTimerTask update_Attend = new coresTimerTask() {
        @Override
        public void run() {
            this.setRun(true);
            ArrayList<Session> allSS = Session.getSessionByStudent(Myself);
            if (allSS != null) {
                for (Session s : allSS) {
                    if (s.toString().equals(nowSession)) {
                        nowSession = s;
                        break;
                    }
                }
            }
            if (nowSession.getAttended_Students() != null) {
                if (Attended_Student != null) {
                    Attended_Student.clear();
                }
                for (Student st : nowSession.getAttended_Students()) {
                    System.out.println(st);
                    Attended_Student.add(st);
                    System.out.println(st);
                }
            }
        }
    };

    private void joined(Session ss) {
        nowSession = ss;

        if (!update_Attend.isRun()) {
            session_Timer.schedule(update_Attend, 0, 500);
        }

        Stage joinedStage = new Stage();
        joinedStage.setTitle("On going session");
        joinedStage.setResizable(false);
        TextArea SimulationArea = new TextArea();

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Simulation Display -------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        SimulationArea.setPrefSize(500, 240);
        SimulationArea.setLayoutX(40);
        SimulationArea.setLayoutY(20);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Class Description -------------------------------------------------------//
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        StackPane BGClassdescriptionPane = new StackPane();
        BGClassdescriptionPane.setLayoutX(0);
        BGClassdescriptionPane.setLayoutY(50);

        VBox ClassDescripVbox = new VBox();
        ClassDescripVbox.setPrefSize(900, 150);
        ClassDescripVbox.setStyle("-fx-background-color:linear-gradient(rgba(255,255,255,1) 0%, #a7eeff 50%, rgba(255,255,255,1) 100%);");

        Label ClassdescriptionLabel = new Label("Class Description here");
        ClassdescriptionLabel.setStyle("-fx-text-fill:#00026b;-fx-font-weight: bold;-fx-font-size:20pt");

        StackPane forClassdescriptionPane = new StackPane();
        forClassdescriptionPane.setLayoutX(600);
        forClassdescriptionPane.setLayoutY(100);
        forClassdescriptionPane.getChildren().addAll(ClassdescriptionLabel);
        BGClassdescriptionPane.getChildren().addAll(ClassDescripVbox);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //----------------------------------- Teachere Name ---------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        StackPane teachernamePane = new StackPane();

        teachernamePane.setLayoutX(720);
        teachernamePane.setLayoutY(150);

        Staff stff = ss.getStaff();
        Label teachernameLabel = new Label(stff.getFirstName() + " " + stff.getLastName() + " is Teaching");
        teachernameLabel.setStyle("-fx-text-fill:#00026b;-fx-font-weight: bold;-fx-font-size:12pt");

        teachernamePane.getChildren().add(teachernameLabel);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //------------------------------- Subject Name ---------------------------------------------------------------//
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        StackPane subjectnamePane = new StackPane();
        subjectnamePane.setLayoutX(675);
        subjectnamePane.setLayoutY(40);

        Label subjectnameLabel = new Label(ss.getCourse().getUserName());
        subjectnameLabel.setStyle("-fx-text-fill:#00026b;-fx-font-weight: bold;-fx-font-size:17pt");

        subjectnamePane.getChildren().addAll(subjectnameLabel);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //------------------------------ Table View ------------------------------------------------------------------//
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        AttendTableView = new TableView<>();
        AttendTableView.setPrefSize(600, 200);

        TableColumn<Student, String> AttendedColumn = new TableColumn<>("Attended Student");
        AttendedColumn.setMinWidth(300);
        AttendedColumn.setMaxWidth(300);
        AttendedColumn.setCellValueFactory(new PropertyValueFactory<>("FullName"));

        AttendTableView.getColumns().addAll(AttendedColumn);
        AttendTableView.setLayoutX(150);
        AttendTableView.setLayoutY(280);

        AttendTableView.setItems(Attended_Student);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Back Button -------------------------------------------------------//
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        Button Backtosessionslist = new Button("Back");
        Backtosessionslist.setPrefSize(300, 50);
        Backtosessionslist.setLayoutX(105);
        Backtosessionslist.setLayoutY(550);
        Backtosessionslist.setOnAction(e -> joinedStage.close());

        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Attend Button-------------------------------------------------------//
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        Button AttendedButton = new Button("Attended");
        AttendedButton.setPrefSize(300, 50);
        AttendedButton.setLayoutX(480);
        AttendedButton.setLayoutY(550);
        AttendedButton.setOnAction(eh -> {
            ss.addAtended_Student(Myself);
            API.saveToDatabase(ss);
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Background -------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        StackPane DecoratePane = new StackPane();
        DecoratePane.setStyle("-fx-background-color:linear-gradient(#ffffff 80%,#383866 80%)");

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- rootpane -------------------------------------------------------//
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        Pane rootPane = new Pane();
        rootPane.setStyle("-fx-background-color:linear-gradient(#ffffff 40%,#bfbfbf 80%)");
        rootPane.getChildren().addAll(DecoratePane, BGClassdescriptionPane, subjectnamePane, teachernamePane,
                AttendTableView, AttendedButton, Backtosessionslist, forClassdescriptionPane, SimulationArea);

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Scene ----------------------------------------------------------//
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        Scene OngoingScene = new Scene(rootPane, 900, 650);
        joinedStage.setScene(OngoingScene);
        joinedStage.showAndWait();
    }

}
