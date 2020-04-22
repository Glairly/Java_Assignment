package online_university.FrontEnd;

import java.util.ArrayList;
import java.util.Timer;
import javafx.application.Application;
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
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.util.Pair;
import javafx.collections.transformation.FilteredList;
import javafx.scene.layout.Border;
import online_university.BackEnd.*;

public class Stu extends Application /*implements EventHandler<ActionEvent>*/ {

    @Override
    public void start(Stage stage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
    TableView<Course> Courses;
    TableView<Course> AddC;
    TableView<Course> JoinTableView;
    TableView<Student> AttendTableView;
    TableView<Student> StudentListTableView;//----------------- Student List here

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

    Stage stage;

    void cancelTimer() {
        session_Timer.cancel();
        session_Timer.purge();
    }

    ;
    /////////////////////////////////////////////// Main ////////////////////////////////////////////////////////////////////////////////////
    public Stage Start(Stage stage, Student stu) {
        this.stage = stage;
        Myself = stu;
        // stop query
        stage.setOnCloseRequest(e -> {
            cancelTimer();
        });

        //Myself = (Student) Authority.login("FoyFoy", "yoFyoF");
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
        if (Myself.getFirstName() != null) {
            name.setText(Myself.getFirstName());
        }

        gp.add(LNametext, 2, 3);
        gp.add(lastname, 3, 3);
        if (Myself.getLastName() != null) {
            lastname.setText(Myself.getLastName());
        }

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
        Courses = new TableView<>();
        Courses.setPrefSize(700, 550);

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
        TNameColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_Name"));

        TableColumn<Course, ArrayList> ScoreColumn = new TableColumn<>("Grade");
        ScoreColumn.setMinWidth(100);
        ScoreColumn.setMaxWidth(100);
        ScoreColumn.setCellValueFactory(new PropertyValueFactory<>("Mark"));

        Courses.getColumns().addAll(CourseNameColumn, CourseColumn, TNameColumn, ScoreColumn);
        Myself.updateCourse();
        for (Course c : Myself.getCourse()) {
            c.setMark(c.getStudent(Myself).getValue().getGrade());
            Courses.getItems().add(c);
        }

        Courses.setLayoutX(375);
        Courses.setLayoutY(100);

        CurrentCourse.setLayoutX(350);
        CurrentCourse.setLayoutY(0);
        CurrentCourse.setStyle("-fx-text-fill:#d7d7d7;-fx-font-weight: bold;-fx-font-size:29pt");

        VBox CurrentcourseText = new VBox();
        CurrentcourseText.setPrefSize(1280, 75);
        CurrentcourseText.setStyle("-fx-background-color:linear-gradient(to bottom right, rgba(0,22,68,1) 20%, rgba(125,161,255,1) 50%, rgba(45,71,255,1) 100%)");

        Button confirmEditButton = new Button("Confirm to edit");

        Button CancleEditButton = new Button("Cancel");

        HBox ConfAndCancelHBox = new HBox();
        ConfAndCancelHBox.getChildren().addAll(CancleEditButton, confirmEditButton);
        ConfAndCancelHBox.setSpacing(10);
        ConfAndCancelHBox.setVisible(false);

        ConfAndCancelHBox.setLayoutX(150);
        ConfAndCancelHBox.setLayoutY(450);

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
            ConfAndCancelHBox.setVisible(true);
            CancleEditButton.setOnAction(eh -> {
                editPane.setVisible(false);
                ConfAndCancelHBox.setVisible(false);
            });
            confirmEditButton.setOnAction(eh2 -> {
                display();
                editPane.setVisible(false);
                ConfAndCancelHBox.setVisible(false);
            });

        });
        ///////////////////////////////         Add Courses Button      //////////////////////////////////////////////////////////
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

        rootPane.getChildren().addAll(CurrentcourseText, Backgroundleft, editPane, fortest, Logout, gp, Courses, Cs, DCs, Join, CurrentCourse,
                EditButtonPane, editTextPane, ConfAndCancelHBox, forEditButton);

        rootPane.setStyle("-fx-background-color:linear-gradient(to bottom right,#0A60C8,#072F5F)");
        rootPane.setPrefSize(1280, 720);
        stage.setTitle("Student : Home");
        stage.setScene(scene);
        stage.show();
        return stage;
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
                /////    check if Courses table on main stage is not blank ---------------------------------------
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
    FilteredList<Course> search;

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
        TNameColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_Name"));

        AddC = new TableView<>();
        addCourse_Data = getCourse();
        AddC.setItems(addCourse_Data);

        AddC.getColumns().addAll(CourseNameColumn, CourseColumn, TNameColumn);

        AddCourse.setTitle("Add course");
        AddnewCourse = new Button("Add");
        CancelAddCourse = new Button("Cancel");
        DoneAddCourse = new Button("Close");

        search = new FilteredList(addCourse_Data, p -> true);//--------------------------------------------------------------------------------
        AddC.setItems(search);
        AddC.getColumns().addAll(CourseNameColumn, CourseColumn, TNameColumn);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Text Field (Can't do it) -------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        TextField searchField;

        searchField = new TextField();
        searchField.setLayoutX(20);
        searchField.setLayoutY(5);
        searchField.setPromptText("Search Course Name");

        TextField TeacherNamefField;

        TeacherNamefField = new TextField();
        TeacherNamefField.setLayoutX(230);
        TeacherNamefField.setLayoutY(5);
        TeacherNamefField.setPromptText("Search Teacher Name");

        searchField.setOnKeyReleased(keyEvent -> {
            {
                search.setPredicate(p -> p.getUserName().toLowerCase().contains(searchField.getText().toLowerCase().trim()));
            }
        });
        TeacherNamefField.setOnKeyReleased(keyEvent -> {
            {
                search.setPredicate(p -> p.getTeacher_Name().toLowerCase().contains(TeacherNamefField.getText().toLowerCase().trim()));
            }
        });
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
        rootPane.getChildren().addAll(Add, searchField, TeacherNamefField);
        Scene Ac = new Scene(rootPane, 645, 500);
        Ac.getStylesheets().add("css/TestAgain.css");
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

        yesButton.setOnAction(e -> {
            if (!" ".equals(tfName.getText()) && !"".equals(tfName.getText())) {
                name.setText(tfName.getText());
                Myself.setFirstName(tfName.getText());
            }

            if (!" ".equals(tfLastname.getText()) && !"".equals(tfLastname.getText())) {
                lastname.setText(tfLastname.getText());
                Myself.setLastName(tfLastname.getText());
            }

            if (!" ".equals(tfAge.getText()) && !"".equals(tfAge.getText())) {
                Age.setText(tfAge.getText());
                Myself.setAge(tfAge.getText());
            }

            if (!" ".equals(tfEm.getText()) && !"".equals(tfEm.getText())) {
                Email.setText(tfEm.getText());
                Myself.setEmail(tfEm.getText());
            }

            update(null);
            var allStuCourse = Course.getCouresByStudent(Myself);
            for (var c : allStuCourse) {
                c.upDateStudent();
                API.saveToDatabase(c);
            }
            CFST.close();
        });

        noButton.setOnAction(e -> {
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
            Courses.getItems().add(AddC.getSelectionModel().getSelectedItem());
            Myself.addCourse(AddC.getSelectionModel().getSelectedItem());
            AddC.getSelectionModel().getSelectedItem().addStudent(Myself);
            update(AddC.getSelectionModel().getSelectedItem());
            AddC.getItems().remove(AddC.getSelectionModel().getSelectedItem());
        }
        AddC.setItems(addCourse_Data);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //-------------------------------- Remove course pressed ----------------------------------------------------//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void Remove() {
        //Course.getSelectionModel().selectFirst();
        if (Courses.getSelectionModel().getSelectedItem() != null) {
//            AddC.getItems().add(Course.getSelectionModel().getSelectedItem());
            Courses.getSelectionModel().getSelectedItem().removeStudent(Myself);
            Myself.removeCourse(Courses.getSelectionModel().getSelectedItem());
            System.out.println(Courses.getSelectionModel().getSelectedItem().getStudents());
            update(Courses.getSelectionModel().getSelectedItem());
            Courses.getItems().remove(Courses.getSelectionModel().getSelectedItem());
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
            LoginAndSignUp loginAndSignUp = new LoginAndSignUp(this.stage);
            cancelTimer();
            this.stage.show();
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
        //JoinTableView.getItems().add(Courses);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- TableColumn --------------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        TableColumn<Course, String> CourseColumn = new TableColumn<>("On going Sessions");
        CourseColumn.setMinWidth(250);
        CourseColumn.setMaxWidth(250);
        CourseColumn.setCellValueFactory(new PropertyValueFactory<>("classDescription"));

        TableColumn<Course, String> NameColumn = new TableColumn<>("Teacher Name");
        NameColumn.setMinWidth(350);
        NameColumn.setMaxWidth(350);
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_Name"));

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
        rootPane.setStyle("-fx-background-color:linear-gradient(to bottom right,#072F5F 50% ,#ffffff 50%,#214d80 100%);");

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
    ObservableList<Student> List_Student = FXCollections.observableArrayList();

    void test() {
        ClassdescriptionLabel.setText("Session Has Ended");
    }
    coresTimerTask update_Attend = new coresTimerTask() {
        @Override
        public void run() {
            this.setRun(true);
            ArrayList<Session> allSS = Session.getSessionByStudent(Myself);
            boolean isOnsession = false;
            if (allSS != null) {
                for (Session s : allSS) {
                    if (s.toString().equals(nowSession.toString())) {
                        nowSession = s;
                        isOnsession = true;
                        break;
                    }
                }
            }
            if (!isOnsession) {
//                this.setRun(false);
//                this.cancel();
                return;
            }
            if (nowSession.getAttended_Students() != null) {

                SimulationArea.setText(nowSession.getLecture());

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

    TextArea SimulationArea = new TextArea();
    Label ClassdescriptionLabel = new Label("On-going Session");
    StackPane forClassdescriptionPane = new StackPane();

    private void joined(Session ss) {

        nowSession = ss;

        if (!update_Attend.isRun()) {
            session_Timer.schedule(update_Attend, 0, 500);
        }
        // set List student
        List_Student.clear();
        for (Pair<Student, Grading> st : nowSession.getCourse().getStudents()) {
            List_Student.add(st.getKey());
        }

        Stage joinedStage = new Stage();
        joinedStage.setTitle("On going session");
        joinedStage.setResizable(false);
        TextArea SimulationArea = new TextArea();

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Simulation Display -------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        SimulationArea.setPrefSize(400, 400);
        SimulationArea.setLayoutX(30);
        SimulationArea.setLayoutY(130);
        SimulationArea.setEditable(false);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Class Description -------------------------------------------------------//
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        StackPane BGClassdescriptionPane = new StackPane();
        BGClassdescriptionPane.setLayoutX(0);
        BGClassdescriptionPane.setLayoutY(50);

        VBox ClassDescripVbox = new VBox();
        ClassDescripVbox.setPrefSize(900, 150);
        //ClassDescripVbox.setStyle("-fx-background-color:linear-gradient(#e1e2ff 0%, #a7eeff 50%, #e1e2ff 100%);");

        Label ClassdescriptionLabel = new Label("On-going Session");
        ClassdescriptionLabel.setStyle("-fx-text-fill:#ffffff;-fx-font-weight: bold;-fx-font-size:13pt;");

        StackPane forClassdescriptionPane = new StackPane();
        forClassdescriptionPane.setLayoutX(5);
        forClassdescriptionPane.setLayoutY(0);
        if (!forClassdescriptionPane.getChildren().contains(ClassdescriptionLabel)) {
            forClassdescriptionPane.getChildren().addAll(ClassdescriptionLabel);
        }
        if (!BGClassdescriptionPane.getChildren().contains(ClassDescripVbox)) {
            BGClassdescriptionPane.getChildren().addAll(ClassDescripVbox);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //----------------------------------- Teachere Name ---------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        VBox SmallBox = new VBox();
        SmallBox.setStyle("-fx-background-color:linear-gradient(#0A60C8,#072F5F)");
        SmallBox.setPrefSize(250, 120);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //----------------------------------- Teachere Name ---------------------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        StackPane teachernamePane = new StackPane();

        teachernamePane.setLayoutX(120);
        teachernamePane.setLayoutY(80);

        Staff stff = ss.getStaff();
        Label teachernameLabel = new Label(stff.getFullName() + " is Teaching");
        teachernameLabel.setStyle("-fx-text-fill:#00026b;-fx-font-weight: bold;-fx-font-size:12pt");

        teachernamePane.getChildren().add(teachernameLabel);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //------------------------------- Subject Name ---------------------------------------------------------------//
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        StackPane subjectnamePane = new StackPane();
        subjectnamePane.setLayoutX(25);
        subjectnamePane.setLayoutY(30);

        Label subjectnameLabel = new Label(ss.getCourse().getUserName());
        subjectnameLabel.setStyle("-fx-text-fill:#ffffff;-fx-font-weight: bold;-fx-font-size:19pt");

        subjectnamePane.getChildren().addAll(subjectnameLabel);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //------------------------------ Table View ------------------------------------------------------------------//
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        AttendTableView = new TableView<>();
        AttendTableView.setPrefSize(350, 400);

        TableColumn<Student, String> AttendedColumn = new TableColumn<>("Attended Student");
        AttendedColumn.setMinWidth(350);
        AttendedColumn.setMaxWidth(350);
        AttendedColumn.setCellValueFactory(new PropertyValueFactory<>("FullName"));

        AttendTableView.getColumns().addAll(AttendedColumn);
        AttendTableView.setLayoutX(475);
        AttendTableView.setLayoutY(130);

        AttendTableView.setItems(Attended_Student);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //------------------------------ Student List table view -----------------------------------------------------//
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        StudentListTableView = new TableView<>();
        StudentListTableView.setPrefSize(350, 400);

        TableColumn<Student, String> StudentListColumn = new TableColumn<>("Student List");
        StudentListColumn.setMinWidth(350);
        StudentListColumn.setMaxWidth(350);
        StudentListColumn.setCellValueFactory(new PropertyValueFactory<>("FullName"));//------------ Here --------------------//

        StudentListTableView.getColumns().addAll(StudentListColumn);
        StudentListTableView.setLayoutX(475);
        StudentListTableView.setLayoutY(130);

        StudentListTableView.setItems(List_Student);

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
        Button AttendedButton = new Button("Attending");
        AttendedButton.setPrefSize(300, 50);
        AttendedButton.setLayoutX(480);
        AttendedButton.setLayoutY(550);
        boolean isAttend = false;
        if (nowSession.getAttended_Students() != null) {
            for (Student st : nowSession.getAttended_Students()) {
                if (st.toString().equals(Myself.toString())) {
                    isAttend = true;
                    AttendedButton.setStyle("-fx-background-color:#34cd33");
                    AttendedButton.setText("You're Attended");
                    AttendedButton.setOnAction(null);
                }
            }
        }
        AttendedButton.setOnAction(eh -> {
            nowSession = Session.updateLocalSession(nowSession);
            nowSession.addAtended_Student(Myself);
            API.saveToDatabase(nowSession);
            AttendedButton.setText("You're Attended");
            AttendedButton.setStyle("-fx-background-color:#34cd33");
            AttendedButton.setOnAction(null);
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Attended student Button ------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        Button AttendedListButton = new Button("Attended Student");
        AttendedListButton.setPrefSize(130, 30);
        AttendedListButton.setLayoutX(505);
        AttendedListButton.setLayoutY(80);

        AttendedListButton.setOnAction(e -> {
            AttendTableView.setVisible(true);
            StudentListTableView.setVisible(false);

        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Student list Button ----------------------------------------------//
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        Button StudentListButton = new Button("List Student");
        StudentListButton.setPrefSize(130, 30);
        StudentListButton.setLayoutX(645);
        StudentListButton.setLayoutY(80);

        StudentListButton.setOnAction(e -> {
            AttendTableView.setVisible(false);
            StudentListTableView.setVisible(true);
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
        rootPane.setStyle("-fx-background-color:linear-gradient(#ffffff 40%,#3e437d 80%)");
        rootPane.getChildren().addAll(DecoratePane, BGClassdescriptionPane, subjectnamePane, teachernamePane,
                AttendTableView, AttendedButton, Backtosessionslist, forClassdescriptionPane, SimulationArea,
                StudentListButton, AttendedListButton, StudentListTableView);

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        //-------------------------------- Scene ----------------------------------------------------------//
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        Scene OngoingScene = new Scene(rootPane, 900, 650);
        joinedStage.setScene(OngoingScene);
        joinedStage.showAndWait();
    }

}
