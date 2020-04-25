/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.FrontEnd;

import java.util.ArrayList;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import online_university.BackEnd.*;

/**
 *
 * @author UNS_CT
 */
public class AdminUI extends Application {

    // SideBar Elements //
    TextField textSearch = new TextField();

    // View Register Element //
    Button acceptRegisterButton = new Button("Accept");
    Button declineRegisterButton = new Button("Decline");

    // Add User Element //
    TextField textFirstName = new TextField();
    TextField textLastName = new TextField();
    TextField textAge = new TextField();
    TextField textStudentID = new TextField();
    TextField textUserName = new TextField();
    PasswordField textPassWord = new PasswordField();
    TextField textEmail = new TextField();
    ComboBox<String> genderSelector = new ComboBox<>();
    ComboBox<String> roleSelector = new ComboBox<>();
    Button clearAddUserButton = new Button("Clear");
    Button okAddUserButton = new Button("OK");

    // Add Course Element //
    TextField textCourseName = new TextField();
    TextArea textCourseDescription = new TextArea();
    Button clearAddCourseButton = new Button("Clear");
    Button okAddCourseButton = new Button("OK");

    // Not Use //
    Button addNewButton = new Button("Add New");
    Button editButton = new Button("Edit");

    // State Value //
    int stateForOKButton = 1; // 1:Add New , 2:Edit
    int indexForEdit = 0;
    int pageState = 1; // 1:UserList , 2:ViewRegister , 3: AddUser , 4:AddCourse , 5:DashBoard
    int tableState = 1; // 1:studentDataTable , 2:teacherDataTable

    Label userListLabel = new Label("User List");
    Label refreshLabel = new Label("Refresh");
    Label deleteLabel = new Label("Delete");
    Label viewRegistorLabel = new Label("View Register");
    Label addUserLabel = new Label("Add User");
    Label addCourseLabel = new Label("Add Course");
    Label dashBoardLabel = new Label("Dashboard");
    Label logOutLabel = new Label("Log Out");

    Label studentGenderLabel = new Label("Student Gender");
    Label teacherGenderLabel = new Label("Teacher Gender");
    Label classRankingLabel = new Label("Class Ranking");

    TableView<Person> studentDataTable = new TableView();
    TableView<Person> teacherDataTable = new TableView();
    TableView<Person> registerDataTable = new TableView();
    TableView<Person> courseDataTable = new TableView();

    // Creaete studentDataTable's Column //
    TableColumn<Person, String> firstNameCol = new TableColumn("First Name");
    TableColumn<Person, String> lastNameCol = new TableColumn("Last Name");
    TableColumn<Person, String> ageCol = new TableColumn("Age");
    TableColumn<Person, String> studentIDCol = new TableColumn("Student ID");
    TableColumn<Person, String> userNameCol = new TableColumn("Username");
    TableColumn<Person, String> passWordCol = new TableColumn("Password");
    TableColumn<Person, String> emailCol = new TableColumn("E-Mail");
    TableColumn<Person, String> genderCol = new TableColumn("Gender");

    // Creaete teacherDataTable's Column //
    TableColumn<Person, String> firstNameCol_t = new TableColumn("First Name");
    TableColumn<Person, String> lastNameCol_t = new TableColumn("Last Name");
    TableColumn<Person, String> ageCol_t = new TableColumn("Age");
    TableColumn<Person, String> userNameCol_t = new TableColumn("Username");
    TableColumn<Person, String> passWordCol_t = new TableColumn("Password");
    TableColumn<Person, String> emailCol_t = new TableColumn("E-Mail");
    TableColumn<Person, String> genderCol_t = new TableColumn("Gender");

    // Creaete registerDataTable's Column //
    TableColumn<Person, String> roleCol_r = new TableColumn("Role");
    TableColumn<Person, String> firstNameCol_r = new TableColumn("First Name");
    TableColumn<Person, String> lastNameCol_r = new TableColumn("Last Name");
    TableColumn<Person, String> ageCol_r = new TableColumn("Age");
    TableColumn<Person, String> userNameCol_r = new TableColumn("Username");
    TableColumn<Person, String> passWordCol_r = new TableColumn("Password");
    TableColumn<Person, String> emailCol_r = new TableColumn("E-Mail");
    TableColumn<Person, String> genderCol_r = new TableColumn("Gender");

    // Creaete registerDataTable's Column //
    TableColumn<Person, String> courseNameCol_c = new TableColumn("Course Name");
    TableColumn<Person, String> memberCol_c = new TableColumn("Member");

    /// Create ConfirmBoxs ///
    ConfirmBox confirmBox = new ConfirmBox();
    /// Create AlertBoxs ///
    AlertBox alertBox = new AlertBox();

    /// Create ArrayList of Teacher //
    ObservableList<Person> studentData = FXCollections.observableArrayList();
    ObservableList<Person> teacherData = FXCollections.observableArrayList();
    ObservableList<Person> registerData = FXCollections.observableArrayList();
    ObservableList<Person> courseData = FXCollections.observableArrayList();

    public Stage Start(Stage stage, Admin user) {
        /// Read Data Base //
        readDataBaseStudents();
        readDataBaseTeachers();
        readDataBaseRegisters();
        readDataBaseCourses();

        /// Create Combo Box ///
        ComboBox<String> themeSelector = new ComboBox<>();
        themeSelector.getItems().addAll("Bright", "Dark");
        themeSelector.setValue("Bright");

        /// Main Pane ///
        HBox mainPane = new HBox();
        //mainPane.setPadding(new Insets(5,5,5,5));
        mainPane.setPrefSize(1280, 720);

        /// Create VBox,StackPane and Add them to mainPane ///
        VBox vBox_1 = new VBox();
        VBox vBox_2_UserList = new VBox();
        VBox vBox_2_ViewRegister = new VBox();
        VBox vBox_2_AddUser = new VBox();
        VBox vBox_2_AddCourse = new VBox();
        VBox vBox_2_DashBoard = new VBox();
        StackPane stackPane_2 = new StackPane();
        stackPane_2.getChildren().add(vBox_2_UserList);
        vBox_1.setPrefSize(400, 720);
        vBox_2_UserList.setPrefSize(880, 720);
        vBox_1.getStyleClass().add("sidebar");
        mainPane.getChildren().addAll(vBox_1, stackPane_2);

        /// Create Panes in vBox_1 ///
        Pane paneForAdminLabel = new Pane();
        Pane paneForUserList = new Pane();
        Pane paneForSearch = new Pane();
        Pane paneForRefresh = new Pane();
        Pane paneForDelete = new Pane();
        Pane paneForViewRegister = new Pane();
        Pane paneForAddUser = new Pane();
        Pane paneForAddCourse = new Pane();
        Pane paneForDashBoard = new Pane();
        Pane paneForLogOut = new Pane();

        // Zero [ Admin Label] //
        ImageView adminIcon = new ImageView(new Image("/Images/admin_icon.png"));
        adminIcon.setFitHeight(50);
        adminIcon.setFitWidth(50);
        Label adminLabel = new Label("Admin Panel");
        paneForAdminLabel.setPrefSize(400, 100);
        paneForAdminLabel.getStyleClass().add("admin-label-background");
        adminLabel.getStyleClass().add("admin-label");
        paneForAdminLabel.getChildren().addAll(adminLabel, adminIcon);
        // Set LayOut //
        adminLabel.setLayoutX(15);
        adminLabel.setLayoutY(25);
        adminIcon.setLayoutX(300);
        adminIcon.setLayoutY(25);

        // First [ User List ] //
        ImageView userListIcon = new ImageView(new Image("/Images/data_icon.png"));
        userListIcon.setFitHeight(50);
        userListIcon.setFitWidth(50);
        paneForRefresh.setPrefSize(400, 75);
        userListLabel.setPrefSize(400, 75);
        userListLabel.getStyleClass().add("sidebar-button");
        paneForUserList.getStyleClass().add("sidebar-hbox");
        paneForUserList.getChildren().addAll(userListIcon, userListLabel);

        // Set LayOut //
        userListIcon.setLayoutX(20);
        userListIcon.setLayoutY(12.5);
        userListLabel.setLayoutX(80);

        // Second [ Search ] //
        ImageView searchIcon = new ImageView(new Image("/Images/search_icon.png"));
        searchIcon.setFitHeight(50);
        searchIcon.setFitWidth(50);
        paneForSearch.setPrefSize(400, 75);
        paneForSearch.getStyleClass().add("sidebar");
        textSearch.setPromptText("By FirstName");
        textSearch.setPrefSize(180, 30);
        textSearch.getStyleClass().add("sidebar-textfield");
        // searchStudentChoice //
        ComboBox<String> searchStudentChoice = new ComboBox<>();
        searchStudentChoice.getItems().addAll("FirstName", "LastName", "Age", "StudentID", "UserName", "PassWord", "E-Mail", "Gender");
        searchStudentChoice.setValue("FirstName");
        searchStudentChoice.setPrefSize(100, 40);
        searchStudentChoice.setStyle("-fx-background-color: #43474E; -fx-alignment: center; -fx-text-fill: #FFFFFF;");
        paneForSearch.getChildren().addAll(searchIcon, textSearch, searchStudentChoice);
        // searchTeacherChoice //
        ComboBox<String> searchTeacherChoice = new ComboBox<>();
        searchTeacherChoice.getItems().addAll("FirstName", "LastName", "Age", "UserName", "PassWord", "E-Mail", "Gender");
        searchTeacherChoice.setValue("FirstName");
        searchTeacherChoice.setPrefSize(100, 40);
        searchTeacherChoice.setStyle("-fx-background-color: #43474E; -fx-alignment: center; -fx-text-fill: #FFFFFF;");
        // searchCourseChoice //
        ComboBox<String> searchCourseChoice = new ComboBox<>();
        searchCourseChoice.getItems().addAll("CourseName", "Member");
        searchCourseChoice.setValue("CourseName");
        searchCourseChoice.setPrefSize(100, 40);
        searchCourseChoice.setStyle("-fx-background-color: #43474E; -fx-alignment: center; -fx-text-fill: #FFFFFF;");
        // searchRegisterChoice //
        ComboBox<String> searchRegisterChoice = new ComboBox<>();
        searchRegisterChoice.getItems().addAll("Role", "FirstName", "LastName", "Age", "UserName", "PassWord", "E-Mail", "Gender");
        searchRegisterChoice.setValue("Role");
        searchRegisterChoice.setPrefSize(100, 40);
        searchRegisterChoice.setStyle("-fx-background-color: #43474E; -fx-alignment: center; -fx-text-fill: #FFFFFF;");

        // Set LayOut //
        searchIcon.setLayoutX(50);
        searchIcon.setLayoutY(12.5);
        textSearch.setLayoutX(110);
        textSearch.setLayoutY(18);
        searchStudentChoice.setLayoutX(295);
        searchStudentChoice.setLayoutY(18);
        searchTeacherChoice.setLayoutX(295);
        searchTeacherChoice.setLayoutY(18);
        searchCourseChoice.setLayoutX(295);
        searchCourseChoice.setLayoutY(18);
        searchRegisterChoice.setLayoutX(295);
        searchRegisterChoice.setLayoutY(18);

        // Three [ Refresh ] //
        ImageView refreshIcon = new ImageView(new Image("/Images/refresh_icon.png"));
        refreshIcon.setFitHeight(50);
        refreshIcon.setFitWidth(50);
        paneForRefresh.setPrefSize(400, 75);
        refreshLabel.setPrefSize(400, 75);
        refreshLabel.getStyleClass().add("sidebar-button");
        paneForRefresh.getStyleClass().add("sidebar-hbox");
        paneForRefresh.getChildren().addAll(refreshIcon, refreshLabel);
        // Set LayOut //
        refreshIcon.setLayoutX(50);
        refreshIcon.setLayoutY(12.5);
        refreshLabel.setLayoutX(110);

        // Four [ Delete ] //
        ImageView deleteIcon = new ImageView(new Image("/Images/delete_icon.png"));
        deleteIcon.setFitHeight(50);
        deleteIcon.setFitWidth(50);
        paneForDelete.setPrefSize(400, 75);
        deleteLabel.setPrefSize(400, 75);
        deleteLabel.getStyleClass().add("sidebar-button");
        paneForDelete.getStyleClass().add("sidebar-hbox");
        paneForDelete.getChildren().addAll(deleteIcon, deleteLabel);
        // Set LayOut //
        deleteIcon.setLayoutX(50);
        deleteIcon.setLayoutY(12.5);
        deleteLabel.setLayoutX(110);

        // Five [ View Registor ] //
        ImageView viewRegistorIcon = new ImageView(new Image("/Images/view_icon.png"));
        viewRegistorIcon.setFitHeight(50);
        viewRegistorIcon.setFitWidth(50);
        paneForViewRegister.setPrefSize(400, 75);
        viewRegistorLabel.setPrefSize(400, 75);
        viewRegistorLabel.getStyleClass().add("sidebar-button");
        paneForViewRegister.getStyleClass().add("sidebar-hbox");
        paneForViewRegister.getChildren().addAll(viewRegistorIcon, viewRegistorLabel);
        // Set LayOut //
        viewRegistorIcon.setLayoutX(20);
        viewRegistorIcon.setLayoutY(12.5);
        viewRegistorLabel.setLayoutX(80);

        // Six [ Add User ] //
        ImageView addUserIcon = new ImageView(new Image("/Images/add_user_icon.png"));
        addUserIcon.setFitHeight(50);
        addUserIcon.setFitWidth(50);
        paneForAddUser.setPrefSize(400, 75);
        addUserLabel.setPrefSize(400, 75);
        addUserLabel.getStyleClass().add("sidebar-button");
        paneForAddUser.getStyleClass().add("sidebar-hbox");
        paneForAddUser.getChildren().addAll(addUserIcon, addUserLabel);
        // Set LayOut //
        addUserIcon.setLayoutX(20);
        addUserIcon.setLayoutY(12.5);
        addUserLabel.setLayoutX(80);

        // Seven [ Add Course ] //
        ImageView addCourseIcon = new ImageView(new Image("/Images/add_course_icon.png"));
        addCourseIcon.setFitHeight(50);
        addCourseIcon.setFitWidth(50);
        paneForAddCourse.setPrefSize(400, 75);
        addCourseLabel.setPrefSize(400, 75);
        addCourseLabel.getStyleClass().add("sidebar-button");
        paneForAddCourse.getStyleClass().add("sidebar-hbox");
        paneForAddCourse.getChildren().addAll(addCourseIcon, addCourseLabel);
        // Set LayOut //
        addCourseIcon.setLayoutX(20);
        addCourseIcon.setLayoutY(12.5);
        addCourseLabel.setLayoutX(80);

        // Eight [ DashBoard ] //
        ImageView dashBoardIcon = new ImageView(new Image("/Images/dashboard_icon.png"));
        dashBoardIcon.setFitHeight(50);
        dashBoardIcon.setFitWidth(50);
        paneForDashBoard.setPrefSize(400, 75);
        dashBoardLabel.setPrefSize(400, 75);
        dashBoardLabel.getStyleClass().add("sidebar-button");
        paneForDashBoard.getStyleClass().add("sidebar-hbox");
        paneForDashBoard.getChildren().addAll(dashBoardIcon, dashBoardLabel);
        // Set LayOut //
        dashBoardIcon.setLayoutX(20);
        dashBoardIcon.setLayoutY(12.5);
        dashBoardLabel.setLayoutX(80);

        // Nine [ LogOut ] //
        logOutLabel.getStyleClass().add("log-out-label");
        paneForLogOut.getChildren().addAll(logOutLabel);
        // Set LayOut //
        logOutLabel.setLayoutX(330);

        // Put all pane to vBox_1 //
        vBox_1.getChildren().addAll(paneForAdminLabel, paneForUserList, paneForSearch, paneForRefresh, paneForDelete, paneForViewRegister, paneForAddUser, paneForAddCourse, paneForDashBoard, paneForLogOut);

        /// Create panes in vBox_2_NameList ///
        HBox paneForSelectTable = new HBox();

        // First [ PaneForSelectTable ] //
        Pane paneForStudentChoice = new Pane();
        Pane paneForTeacherChoice = new Pane();
        Pane paneForCourseChoice = new Pane();
        Label studentLabel = new Label("Student");
        Label teacherLabel = new Label("Teacher");
        Label courseLabel = new Label("Course");

        // Set up Label and Pane //
        paneForStudentChoice.setPrefSize(293, 100);
        paneForTeacherChoice.setPrefSize(293, 100);
        paneForCourseChoice.setPrefSize(294, 100);
        paneForStudentChoice.getStyleClass().add("table-choice-background");
        paneForTeacherChoice.getStyleClass().add("table-choice-background");
        paneForCourseChoice.getStyleClass().add("table-choice-background");

        studentLabel.getStyleClass().add("table-choice");
        teacherLabel.getStyleClass().add("table-choice");
        courseLabel.getStyleClass().add("table-choice");
        studentLabel.setLayoutX(100);
        studentLabel.setLayoutY(25);
        teacherLabel.setLayoutX(100);
        teacherLabel.setLayoutY(25);
        courseLabel.setLayoutX(100);
        courseLabel.setLayoutY(25);

        // Add Label to Pane //
        paneForStudentChoice.getChildren().add(studentLabel);
        paneForTeacherChoice.getChildren().add(teacherLabel);
        paneForCourseChoice.getChildren().add(courseLabel);

        // Add pane to HBox //
        paneForSelectTable.getChildren().addAll(paneForStudentChoice, paneForTeacherChoice, paneForCourseChoice);

        /// Set up Student Student Table ///
        studentDataTable.setPrefSize(880, 620);
        studentDataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Column Style //
        firstNameCol.getStyleClass().add("table-column");
        lastNameCol.getStyleClass().add("table-column");
        ageCol.getStyleClass().add("table-column");
        studentIDCol.getStyleClass().add("table-column");
        userNameCol.getStyleClass().add("table-column");
        passWordCol.getStyleClass().add("table-column");
        emailCol.getStyleClass().add("table-column");
        genderCol.getStyleClass().add("table-column");
        // Column Width //
        firstNameCol.setPrefWidth(150);
        lastNameCol.setPrefWidth(150);
        ageCol.setPrefWidth(100);
        studentIDCol.setPrefWidth(150);
        userNameCol.setPrefWidth(150);
        passWordCol.setPrefWidth(150);
        emailCol.setPrefWidth(300);
        genderCol.setPrefWidth(150);

        // Set studentData into each Column //
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("Age"));
        studentIDCol.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        passWordCol.setCellValueFactory(new PropertyValueFactory<>("PassWord"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("Gender"));

        // Pass data into FilteredList //
        FilteredList<Person> studentFilteredData = new FilteredList(studentData, p -> true);
        // Set studentData into studentDataTable //
        studentDataTable.setItems(studentFilteredData);

        // Add Created column into Table //
        studentDataTable.getColumns().addAll(firstNameCol, lastNameCol, ageCol, studentIDCol, userNameCol, passWordCol, emailCol, genderCol);
        // Set standard choice of studentDataTable //
        if (!studentDataTable.getItems().isEmpty()) {
            studentDataTable.getSelectionModel().select(0);
        }

        /// Set up Teacher Table ///
        teacherDataTable.setPrefSize(880, 620);
        teacherDataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Column Style //
        firstNameCol_t.getStyleClass().add("table-column");
        lastNameCol_t.getStyleClass().add("table-column");
        ageCol_t.getStyleClass().add("table-column");
        userNameCol_t.getStyleClass().add("table-column");
        passWordCol_t.getStyleClass().add("table-column");
        emailCol_t.getStyleClass().add("table-column");
        genderCol_t.getStyleClass().add("table-column");
        // Column Size //
        firstNameCol_t.setPrefWidth(150);
        lastNameCol_t.setPrefWidth(150);
        ageCol_t.setPrefWidth(100);
        userNameCol_t.setPrefWidth(150);
        passWordCol_t.setPrefWidth(150);
        emailCol_t.setPrefWidth(300);
        genderCol_t.setPrefWidth(150);

        // Set teacherData into each Column //
        firstNameCol_t.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastNameCol_t.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        ageCol_t.setCellValueFactory(new PropertyValueFactory<>("Age"));
        userNameCol_t.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        passWordCol_t.setCellValueFactory(new PropertyValueFactory<>("PassWord"));
        emailCol_t.setCellValueFactory(new PropertyValueFactory<>("Email"));
        genderCol_t.setCellValueFactory(new PropertyValueFactory<>("Gender"));

        // Pass data into FilteredList //
        FilteredList<Person> teacherFilteredData = new FilteredList(teacherData, p -> true);
        // Set teacherData into teacherDataTable //
        teacherDataTable.setItems(teacherFilteredData);

        // Add Created column into Table //
        teacherDataTable.getColumns().addAll(firstNameCol_t, lastNameCol_t, ageCol_t, userNameCol_t, passWordCol_t, emailCol_t, genderCol_t);
        // Set standard choice of teacherDataTable //
        if (!teacherDataTable.getItems().isEmpty()) {
            teacherDataTable.getSelectionModel().select(0);
        }

        /// Set up Course Table ///
        courseDataTable.setPrefSize(880, 620);
        courseDataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Column Style //
        courseNameCol_c.getStyleClass().add("table-column");
        memberCol_c.getStyleClass().add("table-column");
        // Column Size //
        courseNameCol_c.setPrefWidth(300);
        memberCol_c.setPrefWidth(150);

        // Set courseData into each Column //
        courseNameCol_c.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        memberCol_c.setCellValueFactory(new PropertyValueFactory<>("Age"));

        // Pass data into FilteredList //
        FilteredList<Person> courseFilteredData = new FilteredList(courseData, p -> true);
        // Set courseData into teacherDataTable //
        courseDataTable.setItems(courseFilteredData);

        // Add Created column into Table //
        courseDataTable.getColumns().addAll(courseNameCol_c, memberCol_c);
        // Set standard choice of courseDataTable //
        if (!courseDataTable.getItems().isEmpty()) {
            courseDataTable.getSelectionModel().select(0);
        }

        // Put all pane to vBox_2_NameList //
        vBox_2_UserList.getChildren().addAll(paneForSelectTable, studentDataTable);

        /// Create panes in vBox_2_ViewRegister ///
        Pane paneForViewRegisterInterface = new Pane();
        Label viewRegisterTopic = new Label("Register List");
        Rectangle viewRegisterRect = new Rectangle(700, 680);

        // Set up Label and Pane //
        paneForViewRegisterInterface.setPrefSize(880, 720);

        viewRegisterTopic.getStyleClass().add("add-user-topic");
        viewRegisterRect.setArcHeight(20);
        viewRegisterRect.setArcWidth(30);
        viewRegisterRect.getStyleClass().add("add-user-rect");
        viewRegisterRect.setFill(Paint.valueOf("#FFFFFF"));
        acceptRegisterButton.setPrefSize(150, 50);
        declineRegisterButton.setPrefSize(150, 50);

        /// Set up Register Student Table ///
        registerDataTable.setPrefSize(640, 450);
        registerDataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Column Style //
        roleCol_r.getStyleClass().add("table-column");
        firstNameCol_r.getStyleClass().add("table-column");
        lastNameCol_r.getStyleClass().add("table-column");
        ageCol_r.getStyleClass().add("table-column");
        userNameCol_r.getStyleClass().add("table-column");
        passWordCol_r.getStyleClass().add("table-column");
        emailCol_r.getStyleClass().add("table-column");
        genderCol_r.getStyleClass().add("table-column");
        // Column Width //
        roleCol_r.setPrefWidth(150);
        firstNameCol_r.setPrefWidth(150);
        lastNameCol_r.setPrefWidth(150);
        ageCol_r.setPrefWidth(100);
        userNameCol_r.setPrefWidth(150);
        passWordCol_r.setPrefWidth(150);
        emailCol_r.setPrefWidth(300);
        genderCol_r.setPrefWidth(150);

        // Set registerData into each Column //
        roleCol_r.setCellValueFactory(new PropertyValueFactory<>("role"));
        firstNameCol_r.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastNameCol_r.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        ageCol_r.setCellValueFactory(new PropertyValueFactory<>("Age"));
        userNameCol_r.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        passWordCol_r.setCellValueFactory(new PropertyValueFactory<>("PassWord"));
        emailCol_r.setCellValueFactory(new PropertyValueFactory<>("Email"));
        genderCol_r.setCellValueFactory(new PropertyValueFactory<>("Gender"));

        // Pass data into FilteredList //
        FilteredList<Person> registerFilteredData = new FilteredList(registerData, p -> true);
        // Set registerData into studentDataTable //
        registerDataTable.setItems(registerFilteredData);

        // Add Created column into Table //
        registerDataTable.getColumns().addAll(roleCol_r, firstNameCol_r, lastNameCol_r, ageCol_r, userNameCol_r, passWordCol_r, emailCol_r, genderCol_r);
        // Set standard choice of registerDataTable //
        if (!registerDataTable.getItems().isEmpty()) {
            registerDataTable.getSelectionModel().select(0);
        }

        paneForViewRegisterInterface.getChildren().add(viewRegisterRect);
        paneForViewRegisterInterface.getChildren().add(viewRegisterTopic);
        paneForViewRegisterInterface.getChildren().add(registerDataTable);
        paneForViewRegisterInterface.getChildren().add(acceptRegisterButton);
        paneForViewRegisterInterface.getChildren().add(declineRegisterButton);

        // Set Layout //
        viewRegisterTopic.setLayoutX(300);
        viewRegisterTopic.setLayoutY(40);
        registerDataTable.setLayoutX(120);
        registerDataTable.setLayoutY(130);
        acceptRegisterButton.setLayoutX(240);
        acceptRegisterButton.setLayoutY(620);
        declineRegisterButton.setLayoutX(490);
        declineRegisterButton.setLayoutY(620);
        viewRegisterRect.setLayoutX(90);
        viewRegisterRect.setLayoutY(20);

        // Put all pane to vBox_2_ViewRegister //
        vBox_2_ViewRegister.getStyleClass().add("add-user-background");
        vBox_2_ViewRegister.getChildren().add(paneForViewRegisterInterface);

        /// Create panes in vBox_2_AddUser ///
        // First [ PaneForAddUser ] //
        Pane paneForAddUserInterface = new Pane();
        Label addUserTopic = new Label("Add User");
        Rectangle addUserRect = new Rectangle(700, 680);
        genderSelector.setValue("Select Your Gender");
        genderSelector.getItems().addAll("Female", "Male");
        roleSelector.setValue("Select Your Role");
        roleSelector.getItems().addAll("Student", "Teacher");

        // Set up Label and Pane //
        paneForAddUserInterface.setPrefSize(880, 720);

        textFirstName.setPromptText("Input First Name...");
        textLastName.setPromptText("Input LastName...");
        textAge.setPromptText("Input Age...");
        textStudentID.setPromptText("Input StudentID...");
        textUserName.setPromptText("Input UserName...");
        textPassWord.setPromptText("Input PassWord...");
        textEmail.setPromptText("Input Email...");

        textFirstName.setPrefSize(250, 40);
        textLastName.setPrefSize(250, 40);
        textAge.setPrefSize(250, 40);
        textStudentID.setPrefSize(250, 40);
        textUserName.setPrefSize(250, 40);
        textPassWord.setPrefSize(250, 40);
        textEmail.setPrefSize(250, 40);

        addUserTopic.getStyleClass().add("add-user-topic");
        textFirstName.getStyleClass().add("add-user-text-field");
        textLastName.getStyleClass().add("add-user-text-field");
        textAge.getStyleClass().add("add-user-text-field");
        textAge.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textAge.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }

        }); 
        textStudentID.getStyleClass().add("add-user-text-field");
        textUserName.getStyleClass().add("add-user-text-field");
        textPassWord.getStyleClass().add("add-user-text-field");
        textEmail.getStyleClass().add("add-user-text-field");
        addUserRect.setFill(Paint.valueOf("#FFFFFF"));
        addUserRect.setArcHeight(20);
        addUserRect.setArcWidth(30);
        addUserRect.getStyleClass().add("add-user-rect");
        clearAddUserButton.setPrefSize(150, 50);
        okAddUserButton.setPrefSize(150, 50);
        genderSelector.setPrefSize(150, 20);
        roleSelector.setPrefSize(150, 20);

        paneForAddUserInterface.getChildren().add(addUserRect);
        paneForAddUserInterface.getChildren().add(addUserTopic);
        paneForAddUserInterface.getChildren().add(textFirstName);
        paneForAddUserInterface.getChildren().add(textLastName);
        paneForAddUserInterface.getChildren().add(textAge);
        paneForAddUserInterface.getChildren().add(textStudentID);
        paneForAddUserInterface.getChildren().add(textUserName);
        paneForAddUserInterface.getChildren().add(textPassWord);
        paneForAddUserInterface.getChildren().add(textEmail);
        paneForAddUserInterface.getChildren().add(clearAddUserButton);
        paneForAddUserInterface.getChildren().add(okAddUserButton);
        paneForAddUserInterface.getChildren().add(genderSelector);
        paneForAddUserInterface.getChildren().add(roleSelector);

        // Set Layout //
        addUserTopic.setLayoutX(340);
        addUserTopic.setLayoutY(40);
        textFirstName.setLayoutX(315);
        textFirstName.setLayoutY(130);
        textLastName.setLayoutX(315);
        textLastName.setLayoutY(190);
        textAge.setLayoutX(315);
        textAge.setLayoutY(250);
        textStudentID.setLayoutX(315);
        textStudentID.setLayoutY(310);
        textUserName.setLayoutX(315);
        textUserName.setLayoutY(370);
        textPassWord.setLayoutX(315);
        textPassWord.setLayoutY(430);
        textEmail.setLayoutX(315);
        textEmail.setLayoutY(490);
        clearAddUserButton.setLayoutX(240);
        clearAddUserButton.setLayoutY(620);
        okAddUserButton.setLayoutX(490);
        okAddUserButton.setLayoutY(620);
        genderSelector.setLayoutX(490);
        genderSelector.setLayoutY(540);
        roleSelector.setLayoutX(240);
        roleSelector.setLayoutY(540);
        addUserRect.setLayoutX(90);
        addUserRect.setLayoutY(20);

        // Put all pane to vBox_2_AddUser //
        vBox_2_AddUser.getStyleClass().add("add-user-background");
        vBox_2_AddUser.getChildren().add(paneForAddUserInterface);

        /// Create panes in vBox_2_AddCourse ///
        // First [ PaneForAddCourse ] //
        Pane paneForAddCourseInterface = new Pane();
        Label addCourseTopic = new Label("Add Course");
        Rectangle addCourseRect = new Rectangle(700, 680);

        // Set up Label and Pane //
        paneForAddCourseInterface.setPrefSize(880, 720);

        textCourseName.setPromptText("Input Course Name...");
        textCourseDescription.setPromptText("Input Course Description...");

        textCourseName.setPrefSize(250, 60);
        textCourseDescription.setPrefSize(250, 200);

        addCourseTopic.getStyleClass().add("add-user-topic");
        addCourseRect.setArcHeight(20);
        addCourseRect.setArcWidth(30);
        addCourseRect.getStyleClass().add("add-user-rect");
        textCourseName.getStyleClass().add("add-user-text-field");
        textCourseDescription.getStyleClass().add("add-user-text-field");
        addCourseRect.setFill(Paint.valueOf("#FFFFFF"));
        clearAddCourseButton.setPrefSize(150, 50);
        okAddCourseButton.setPrefSize(150, 50);

        paneForAddCourseInterface.getChildren().add(addCourseRect);
        paneForAddCourseInterface.getChildren().add(addCourseTopic);
        paneForAddCourseInterface.getChildren().add(textCourseName);
        paneForAddCourseInterface.getChildren().add(textCourseDescription);
        paneForAddCourseInterface.getChildren().add(clearAddCourseButton);
        paneForAddCourseInterface.getChildren().add(okAddCourseButton);

        // Set Layout //
        addCourseTopic.setLayoutX(315);
        addCourseTopic.setLayoutY(40);
        textCourseName.setLayoutX(315);
        textCourseName.setLayoutY(130);
        textCourseDescription.setLayoutX(315);
        textCourseDescription.setLayoutY(290);
        clearAddCourseButton.setLayoutX(240);
        clearAddCourseButton.setLayoutY(620);
        okAddCourseButton.setLayoutX(490);
        okAddCourseButton.setLayoutY(620);
        addCourseRect.setLayoutX(90);
        addCourseRect.setLayoutY(20);

        // Put all pane to vBox_2_AddCourse //
        vBox_2_AddCourse.getStyleClass().add("add-user-background");
        vBox_2_AddCourse.getChildren().add(paneForAddCourseInterface);

        /// Create panes in vBox_2_DashBoard ///
        Pane paneForDashBoardInterface = new Pane();
        Label dashBoardTopic = new Label("Dashboard");
        Rectangle dashBoardRect = new Rectangle(700, 680);

        dashBoardRect.setArcHeight(20);
        dashBoardRect.setArcWidth(30);
        dashBoardRect.getStyleClass().add("add-user-rect");
        dashBoardRect.setFill(Paint.valueOf("#FFFFFF"));

        // Student Gender PieChart
        ObservableList<PieChart.Data> studentGenderData = FXCollections.observableArrayList();
        studentGenderData.add(new PieChart.Data("Male", getCount(studentData)[1]));
        studentGenderData.add(new PieChart.Data("Female", getCount(studentData)[2]));
        PieChart studentGenderChart = new PieChart(studentGenderData);
        studentGenderLabel.getStyleClass().add("chart-topic");
        studentGenderChart.setPrefSize(250, 250);
        studentGenderChart.setLabelsVisible(false);
        // Teacher Gender PieChart
        ObservableList<PieChart.Data> teacherGenderData = FXCollections.observableArrayList();
        teacherGenderData.add(new PieChart.Data("Male", getCount(teacherData)[1]));
        teacherGenderData.add(new PieChart.Data("Female", getCount(teacherData)[2]));
        PieChart teacherGenderChart = new PieChart(teacherGenderData);
        teacherGenderLabel.getStyleClass().add("chart-topic");
        teacherGenderChart.setPrefSize(250, 250);
        teacherGenderChart.setLabelsVisible(false);
        // Class ranking Chart //
        CategoryAxis classRankingName = new CategoryAxis();
        classRankingName.setLabel("Class Name");
        NumberAxis classRankingStudent = new NumberAxis();
        classRankingStudent.setLabel("Student");
        BarChart classRankingBarChart = new BarChart(classRankingName, classRankingStudent);
        XYChart.Series classRankingSeries = new XYChart.Series();
        Person[] classRanking = getRanking(courseData);
        classRankingSeries.getData().add(new XYChart.Data(classRanking[0].getUserName(), Integer.parseInt(classRanking[0].getAge())));
        classRankingSeries.getData().add(new XYChart.Data(classRanking[1].getUserName(), Integer.parseInt(classRanking[1].getAge())));
        classRankingSeries.getData().add(new XYChart.Data(classRanking[2].getUserName(), Integer.parseInt(classRanking[2].getAge())));
        classRankingLabel.getStyleClass().add("chart-topic");
        classRankingBarChart.setPrefSize(250, 250);
        classRankingBarChart.setLegendVisible(false);
        classRankingBarChart.getData().add(classRankingSeries);

        // Set up Label and Pane //
        paneForDashBoardInterface.setPrefSize(880, 720);
        paneForDashBoardInterface.getChildren().add(dashBoardRect);

        // Set Layout //
        dashBoardRect.setLayoutX(90);
        dashBoardRect.setLayoutY(20);

        // Put all pane to vBox_2_DashBoard //
        vBox_2_DashBoard.getStyleClass().add("add-user-background");
        vBox_2_DashBoard.getChildren().add(paneForDashBoardInterface);

        dashBoardTopic.getStyleClass().add("add-user-topic");

        // Add Elements to pane //
        paneForDashBoardInterface.getChildren().add(dashBoardTopic);
        paneForDashBoardInterface.getChildren().add(studentGenderLabel);
        paneForDashBoardInterface.getChildren().add(studentGenderChart);
        paneForDashBoardInterface.getChildren().add(teacherGenderLabel);
        paneForDashBoardInterface.getChildren().add(teacherGenderChart);
        paneForDashBoardInterface.getChildren().add(classRankingLabel);
        paneForDashBoardInterface.getChildren().add(classRankingBarChart);

        // Set Layout //
        dashBoardTopic.setLayoutX(315);
        dashBoardTopic.setLayoutY(40);
        studentGenderLabel.setLayoutX(125);
        studentGenderLabel.setLayoutY(110);
        studentGenderChart.setLayoutX(115);
        studentGenderChart.setLayoutY(145);
        teacherGenderLabel.setLayoutX(500);
        teacherGenderLabel.setLayoutY(110);
        teacherGenderChart.setLayoutX(500);
        teacherGenderChart.setLayoutY(145);
        classRankingLabel.setLayoutX(350);
        classRankingLabel.setLayoutY(415);
        classRankingBarChart.setLayoutX(300);
        classRankingBarChart.setLayoutY(450);

        /// adminScene Set Style Class ///
        Scene adminScene = new Scene(mainPane);
        adminScene.getStylesheets().add("css/brightside.css");

        searchStudentChoice.setOnAction(e -> {
            textSearch.setPromptText("By " + searchStudentChoice.getValue());
            search(studentFilteredData, searchStudentChoice.getValue(), studentDataTable);
        });
        searchTeacherChoice.setOnAction(e -> {
            textSearch.setPromptText("By " + searchTeacherChoice.getValue());
            search(teacherFilteredData, searchTeacherChoice.getValue(), teacherDataTable);
        });
        searchCourseChoice.setOnAction(e -> {
            textSearch.setPromptText("By " + searchCourseChoice.getValue());
            search(courseFilteredData, searchCourseChoice.getValue(), courseDataTable);
        });
        searchRegisterChoice.setOnAction(e -> {
            textSearch.setPromptText("By " + searchRegisterChoice.getValue());
            search(registerFilteredData, searchRegisterChoice.getValue(), registerDataTable);
        });

        /// Panes Interaction ///       
        // User List //
        userListLabel.setOnMouseClicked(e -> {
            if (pageState != 1 && pageState != 0) {
                pageState = 0;
                vBox_2_UserList.getChildren().remove(1);
                vBox_2_UserList.getChildren().add(studentDataTable);
                tableState = 1;
                paneForSearch.getChildren().remove(2);
                paneForSearch.getChildren().add(searchStudentChoice);
                searchStudentChoice.setValue("FirstName");
                textSearch.setPromptText("By FirstName");
                stackPane_2.getChildren().add(vBox_2_UserList);
                vBox_2_UserList.translateYProperty().set(720);
                changePage(stackPane_2, vBox_2_UserList, 1);
            }
        });

        // Search //
        textSearch.setOnKeyReleased(e -> {
            if (tableState == 1) { // Student Table //
                search(studentFilteredData, searchStudentChoice.getValue(), studentDataTable);
            } else if (tableState == 2) { // Teacher Table //
                search(teacherFilteredData, searchTeacherChoice.getValue(), teacherDataTable);
            } else if (tableState == 3) { // Course Table //
                search(courseFilteredData, searchCourseChoice.getValue(), courseDataTable);
            } else if (tableState == 4) { // Register Table //
                search(registerFilteredData, searchRegisterChoice.getValue(), registerDataTable);
            }
        });

        // Refresh //
        paneForRefresh.setOnMouseClicked(e -> {
            textSearch.clear();
            if (tableState == 1) { // Student Table //
                studentFilteredData.setPredicate(p -> p.getFirstName().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                studentDataTable.getSelectionModel().select(0);
            } else if (tableState == 2) { // Teacher Table //
                teacherFilteredData.setPredicate(p -> p.getFirstName().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                studentDataTable.getSelectionModel().select(0);
            } else if (tableState == 3) { // Course Table //
                courseFilteredData.setPredicate(p -> p.getUserName().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                courseDataTable.getSelectionModel().select(0);
            } else if (tableState == 4) { // Register Table //
                registerFilteredData.setPredicate(p -> p.getFirstName().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                registerDataTable.getSelectionModel().select(0);
            }
        });

        // Delete Button //
        paneForDelete.setOnMouseClicked(e -> {
            if (tableState == 1) {
                if (!studentDataTable.getItems().isEmpty()) {
                    if (confirmBox.display("Confirm Please...", "Are you sure to Delete This Student")) {
                        // Remove from studentData //
                        studentData.removeAll(studentDataTable.getSelectionModel().getSelectedItems());

                        // Set deleteConfirm back to FALSE //
                        confirmBox.setAnswer(false);
                    }
                }
            } else if (tableState == 2) {
                if (!teacherDataTable.getItems().isEmpty()) {
                    if (confirmBox.display("Confirm Please...", "Are you sure to Delete This Teacher")) {
                        // Remove from teacherData //
                        teacherData.removeAll(teacherDataTable.getSelectionModel().getSelectedItems());

                        // Set deleteConfirm back to FALSE //
                        confirmBox.setAnswer(false);
                    }
                }
            } else if (tableState == 3) {
                if (!courseDataTable.getItems().isEmpty()) {
                    if (confirmBox.display("Confirm Please...", "Are you sure to Delete This Course")) {
                        // Remove from teacherData //
                        courseData.removeAll(courseDataTable.getSelectionModel().getSelectedItems());

                        // Set deleteConfirm back to FALSE //
                        confirmBox.setAnswer(false);
                    }
                }
            }
        });

        // viewRegistor Button //
        paneForViewRegister.setOnMouseClicked(e -> {
            if (pageState != 2 && pageState != 0) {
                pageState = 0;
                stackPane_2.getChildren().add(vBox_2_ViewRegister);
                vBox_2_ViewRegister.translateYProperty().set(720);
                changePage(stackPane_2, vBox_2_ViewRegister, 2);
                tableState = 4;
                paneForSearch.getChildren().remove(2);
                paneForSearch.getChildren().add(searchRegisterChoice);
                searchRegisterChoice.setValue("Role");
                textSearch.setPromptText("By Role");
            }
        });

        // Add User //
        paneForAddUser.setOnMouseClicked(e -> {
            if (pageState != 3 && pageState != 0) {
                pageState = 0;
                roleSelector.setValue("Student");
                genderSelector.setValue("Female");
                stackPane_2.getChildren().add(vBox_2_AddUser);
                vBox_2_AddUser.translateYProperty().set(720);
                changePage(stackPane_2, vBox_2_AddUser, 3);
            }
        });

        // Add Course //
        paneForAddCourse.setOnMouseClicked(e -> {
            if (pageState != 4 && pageState != 0) {
                pageState = 0;
                stackPane_2.getChildren().add(vBox_2_AddCourse);
                vBox_2_AddCourse.translateYProperty().set(720);
                changePage(stackPane_2, vBox_2_AddCourse, 4);
            }
        });

        // DashBoard //
        paneForDashBoard.setOnMouseClicked(e -> {
            if (pageState != 5 && pageState != 0) {
                pageState = 0;
                updateDashBoard(studentGenderChart, teacherGenderChart, classRankingBarChart);
                stackPane_2.getChildren().add(vBox_2_DashBoard);
                vBox_2_DashBoard.translateYProperty().set(720);
                changePage(stackPane_2, vBox_2_DashBoard, 5);
            }
        });

        logOutLabel.setOnMouseClicked(e -> {
            ArrayList<Person> tempStudentData = new ArrayList<>();
            ArrayList<Person> tempTeacherData = new ArrayList<>();
            ArrayList<Person> tempCourseData = new ArrayList<>();

            studentData.forEach((student) -> {
                tempStudentData.add(student);
            });

            teacherData.forEach((teacher) -> {
                tempTeacherData.add(teacher);
            });

            courseData.forEach((course) -> {
                tempCourseData.add(course);
            });

            Database db = new Database();
            db.setPath_Students();
            db.write(tempStudentData);

            db.setPath_Staffs();
            db.write(tempTeacherData);

            db.setPath_Courses();
            db.write(tempCourseData);

            System.out.println("Saved");
            if (confirmBox.display("Log Out", "Do you want to Log Out ?")) {
                LoginAndSignUp loginAndSignUp = new LoginAndSignUp(stage);
                //cancelTimer();
                stage.show();
            }
        });

        // Student Choice //
        paneForStudentChoice.setOnMouseClicked(e -> {
            if (tableState != 1) {
                vBox_2_UserList.getChildren().remove(1);
                vBox_2_UserList.getChildren().add(studentDataTable);
                tableState = 1;
                paneForSearch.getChildren().remove(2);
                paneForSearch.getChildren().add(searchStudentChoice);
                searchStudentChoice.setValue("FirstName");
                textSearch.setPromptText("By FirstName");
            }
        });

        // Teacher Choice //
        paneForTeacherChoice.setOnMouseClicked(e -> {
            if (tableState != 2) {
                vBox_2_UserList.getChildren().remove(1);
                vBox_2_UserList.getChildren().add(teacherDataTable);
                tableState = 2;
                paneForSearch.getChildren().remove(2);
                paneForSearch.getChildren().add(searchTeacherChoice);
                searchTeacherChoice.setValue("FirstName");
                textSearch.setPromptText("By FirstName");
            }
        });

        // Course Choice //
        paneForCourseChoice.setOnMouseClicked(e -> {
            if (tableState != 3) {
                vBox_2_UserList.getChildren().remove(1);
                vBox_2_UserList.getChildren().add(courseDataTable);
                tableState = 3;
                paneForSearch.getChildren().remove(2);
                paneForSearch.getChildren().add(searchCourseChoice);
                searchCourseChoice.setValue("CourseName");
                textSearch.setPromptText("By CourseName");
            }
        });

        /// Buttons Interaction ///
        // Add New Button //
        addNewButton.setOnAction(e -> {
            stateForOKButton = 1;
            //showState.setText("  State : Add New");
            textFieldAddUserClear();
        });

        // Accept Register //
        acceptRegisterButton.setOnAction(e -> {
            // Add to their role's data //
            for (Person register : registerDataTable.getSelectionModel().getSelectedItems()) {
                System.out.println(register.getRole());
                if (register.getRole() == "Student") {
                    Student s = (Student) register;
                    s.setStudentId(s.getUserName());
                    studentData.add(s);
                    API.RemoveFromDatabase("Registers", s);
                } else if (register.getRole() == "Staff") {
                    teacherData.add((Staff) register);
                    API.RemoveFromDatabase("Registers", (Staff) register);
                }
            }
            registerData.removeAll(registerDataTable.getSelectionModel().getSelectedItems());
        });

        // Decline Register //
        declineRegisterButton.setOnAction(e -> {
            registerData.removeAll(registerDataTable.getSelectionModel().getSelectedItems());
        });

        // Change role for Add User //
        roleSelector.setOnAction(e -> {
            if (roleSelector.getValue() == "Teacher") {
                textStudentID.clear();
                textStudentID.setDisable(true);
            } else {
                textStudentID.setDisable(false);
            }
        });

        // Clear AddUser Button //
        clearAddUserButton.setOnAction(e -> {
            // Clear Text //
            textFieldAddUserClear();
        });

        // OK AddUser Button //
        okAddUserButton.setOnAction(e -> {
            int countUserTexted = 0;
            boolean[] userTextError = new boolean[7];
            for (int i = 0; i < 7; i++) {
                userTextError[i] = false;
            }

            if (textFirstName.getText().length() == 0) {
                countUserTexted++;
                userTextError[0] = true;
            }
            if (textLastName.getText().length() == 0) {
                countUserTexted++;
                userTextError[1] = true;
            }
            if (textAge.getText().length() == 0) {
                countUserTexted++;
                userTextError[2] = true;
            }
            if (textUserName.getText().length() == 0) {
                countUserTexted++;
                userTextError[4] = true;
            }
            if (textPassWord.getText().length() == 0) {
                countUserTexted++;
                userTextError[5] = true;
            }
            if (textEmail.getText().length() == 0) {
                countUserTexted++;
                userTextError[6] = true;
            }

            if (roleSelector.getValue() == "Teacher") {
                if (countUserTexted == 0) {
                    teacherData.add(new Staff(
                            textFirstName.getText(), textLastName.getText(),
                            textAge.getText(), textStudentID.getText(),
                            textUserName.getText(), textPassWord.getText(),
                            textEmail.getText()
                    ));
                    teacherData.get(teacherData.size() - 1).setGender(genderSelector.getValue());
                    textFieldAddUserClear();
                    setAddUserTextError(userTextError);
                } else {
                    alertBox.display("Alert Box", "Please enter all textfield.");
                    setAddUserTextError(userTextError);
                }
            } else {
                if (textStudentID.getText().length() == 0) {
                    countUserTexted++;
                    userTextError[3] = true;
                }
                if (countUserTexted == 0) {
                    studentData.add(new Student(
                            textFirstName.getText(), textLastName.getText(),
                            textAge.getText(), textStudentID.getText(),
                            textUserName.getText(), textPassWord.getText(),
                            textEmail.getText()
                    ));
                    studentData.get(studentData.size() - 1).setGender(genderSelector.getValue());
                    textFieldAddUserClear();
                    setAddUserTextError(userTextError);
                } else {
                    alertBox.display("Alert Box", "Please enter all textfield.");
                    setAddUserTextError(userTextError);
                }
            }
        });

        // Clear AddCourse Button //
        clearAddCourseButton.setOnAction(e -> {
            // Clear Text //
            textFieldAddCourseClear();
        });

        // OK AddCourse Button //
        okAddCourseButton.setOnAction(e -> {
            int countCorseTexted = 0;
            boolean[] courseTextError = new boolean[2];
            for (int i = 0; i < 2; i++) {
                courseTextError[i] = false;
            }

            // Check text Empty? //
            if (textCourseName.getText().length() == 0) {
                countCorseTexted++;
                courseTextError[0] = true;
            }
            if (textCourseDescription.getText().length() == 0) {
                countCorseTexted++;
                courseTextError[1] = true;
            }

            if (countCorseTexted == 0) {
                Course tempCourse = new Course(textCourseName.getText(), null, null);
                tempCourse.setClassDescription(textCourseDescription.getText());
                tempCourse.setAge("0");
                courseData.add(tempCourse);
                API.saveToDatabase(tempCourse);
                textFieldAddCourseClear();
                setAddCourseTextError(courseTextError);
            } else {
                alertBox.display("Alert Box", "Please enter all textfield.");
                setAddCourseTextError(courseTextError);
            }
        });

        /// Close Program ///
        stage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(stage);
        });

        /// Set Stage & Scene ///
        stage.setTitle("Admin Label");
        stage.setScene(adminScene);
        stage.show();
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
    }

    /// Main Function ///
    public static void main(String[] args) {
        Database db = new Database();
        db._READ_();

        launch(args);
    }

    /// TextField Add User Clear ///
    void textFieldAddUserClear() {
        textFirstName.clear();
        textLastName.clear();
        textAge.clear();
        textStudentID.clear();
        textUserName.clear();
        textPassWord.clear();
        textEmail.clear();
        /*roleSelector.setValue("Select Your Role");
        genderSelector.setValue("Select Your Gender");*/
        System.out.println("Cleared");
    }

    /// TextField Add Course Clear ///
    void textFieldAddCourseClear() {
        textCourseName.clear();
        textCourseDescription.clear();
    }

    /// Change Page Function ///
    void changePage(StackPane stackPane, VBox nextPage, int endState) {
        /// Change Page Animation ///
        Timeline changePage = new Timeline();
        KeyValue kv = new KeyValue(nextPage.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        changePage.getKeyFrames().add(kf);
        changePage.setOnFinished(e -> {
            stackPane.getChildren().remove(0);
            pageState = endState;
        });
        changePage.play();
    }

    /// ConfirmBox ///
    class ConfirmBox {

        boolean answer = false;

        boolean display(String windowTitle, String confirmLabel) {
            Stage confirmWindow = new Stage();
            confirmWindow.setTitle(windowTitle);
            confirmWindow.initModality(Modality.APPLICATION_MODAL);

            VBox vBox = new VBox(50);
            GridPane gridPane = new GridPane();
            Button yesButton = new Button("Yes");
            Button noButton = new Button("No");
            Label label = new Label(confirmLabel);

            yesButton.setPrefSize(75, 50);
            noButton.setPrefSize(75, 50);

            vBox.setPrefSize(260, 260);
            vBox.setAlignment(Pos.CENTER);
            vBox.setPadding(new Insets(10, 10, 10, 10));

            gridPane.setHgap(75);
            gridPane.setPadding(new Insets(10, 10, 10, 10));

            // Add buttons into gridPane //
            gridPane.add(yesButton, 0, 0);
            gridPane.add(noButton, 1, 0);

            // Add gridPane into vBox //
            vBox.getChildren().addAll(label, gridPane);

            // Add vBox into confirmScene //
            Scene confirmScene = new Scene(vBox);

            // Click Yes //
            yesButton.setOnAction(e -> {
                answer = true;
                confirmWindow.close();
            });

            // Click No //
            noButton.setOnAction(e -> {
                answer = false;
                confirmWindow.close();
            });

            confirmWindow.setScene(confirmScene);
            confirmWindow.showAndWait();

            return answer;
        }

        void setAnswer(boolean answer) {
            this.answer = answer;
        }
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

    /// Close Program Function ///
    void closeProgram(Stage stage) {
        boolean answer = confirmBox.display("Exit Confirm...", "Are you want to Exit ?");
        if (answer) {
            ArrayList<Person> tempStudentData = new ArrayList<>();
            ArrayList<Person> tempTeacherData = new ArrayList<>();
            ArrayList<Person> tempCourseData = new ArrayList<>();

            studentData.forEach((student) -> {
                tempStudentData.add(student);
            });

            teacherData.forEach((teacher) -> {
                tempTeacherData.add(teacher);
            });

            courseData.forEach((course) -> {
                tempCourseData.add(course);
            });

            Database db = new Database();
            db.setPath_Students();
            db.write(tempStudentData);

            db.setPath_Staffs();
            db.write(tempTeacherData);

            db.setPath_Courses();
            db.write(tempCourseData);

            System.out.println("Saved");
            stage.close();
        }
    }

    /// Read Students From DataBase Function ///
    void readDataBaseStudents() {
        ArrayList<Student> tempStudentData = new ArrayList<>();

        tempStudentData = API.getAllStudent();

        if (tempStudentData != null) {
            for (Student student : tempStudentData) {
                studentData.add(student);
            }
        }

        System.out.println("Read Students");
    }

    /// Read Teachers From DataBase Function ///
    void readDataBaseTeachers() {
        ArrayList<Staff> tempTeacherData = new ArrayList<>();

        tempTeacherData = API.getAllStaff();

        if (tempTeacherData != null) {
            for (Staff teacher : tempTeacherData) {
                teacherData.add(teacher);
            }
        }

        System.out.println("Read Teachers");
    }

    /// Read Courses From DataBase Function ///
    void readDataBaseCourses() {
        ArrayList<Course> tempCourseData = new ArrayList<>();

        tempCourseData = API.getAllCourse();

        if (tempCourseData != null) {
            for (Course course : tempCourseData) {
                courseData.add(course);
            }
        }

        System.out.println("Read Courses");
    }

    /// Read Registers From DataBase Function ///
    void readDataBaseRegisters() {
        ArrayList<Person> tempRegisterData = new ArrayList<>();

        tempRegisterData = API.getAllRegister();

        if (tempRegisterData != null) {
            for (Person register : tempRegisterData) {
                registerData.add(register);
            }
        }

        System.out.println("Read Registers");
    }

    /// Count Teacher [All] [Male] [Female] ///
    Integer[] getCount(ObservableList<Person> persons) {
        Integer[] count = new Integer[3];
        count[0] = 0;
        count[1] = 0;
        count[2] = 0;
        for (Person person : persons) {
            count[0] += 1;
            if ("Male".equals(person.getGender())) {
                count[1] += 1;
            } else if ("Female".equals(person.getGender())) {
                count[2] += 1;
            }
        }
        return count;
    }

    void updateDashBoard(PieChart studentGenderChart, PieChart teacherGenderChart, BarChart classRankingChart) {
        // Update Student PieChart //
        studentGenderChart.getData().get(0).setPieValue(getCount(studentData)[1]);
        studentGenderChart.getData().get(1).setPieValue(getCount(studentData)[2]);
        // Update Teacher PieChart //
        teacherGenderChart.getData().get(0).setPieValue(getCount(teacherData)[1]);
        teacherGenderChart.getData().get(1).setPieValue(getCount(teacherData)[2]);
        // Update ClassRanking BarChart //
        classRankingChart.getData().remove(0);
        XYChart.Series classRankingSeries = new XYChart.Series();
        Person[] classRanking = getRanking(courseData);
        classRankingSeries.getData().add(new XYChart.Data(classRanking[0].getUserName(), Integer.parseInt(classRanking[0].getAge())));
        classRankingSeries.getData().add(new XYChart.Data(classRanking[1].getUserName(), Integer.parseInt(classRanking[1].getAge())));
        classRankingSeries.getData().add(new XYChart.Data(classRanking[2].getUserName(), Integer.parseInt(classRanking[2].getAge())));
        classRankingChart.getData().add(classRankingSeries);
    }

    /// Search Function ///
    void search(FilteredList<Person> filteredData, String choice, TableView dataTable) {
        switch (choice) {
            case "FirstName":
                filteredData.setPredicate(p -> p.getFirstName().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                break;
            case "LastName":
                filteredData.setPredicate(p -> p.getLastName().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                break;
            case "Age":
                filteredData.setPredicate(p -> p.getAge().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                break;
            case "StudentID":
                filteredData.setPredicate(p -> p.getStudentId().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                break;
            case "UserName":
                filteredData.setPredicate(p -> p.getUserName().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                break;
            case "PassWord":
                filteredData.setPredicate(p -> p.getPassWord().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                break;
            case "E-Mail":
                filteredData.setPredicate(p -> p.getEmail().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                break;
            case "Gender":
                filteredData.setPredicate(p -> p.getGender().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                break;
            case "CourseName":
                filteredData.setPredicate(p -> p.getUserName().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                break;
            case "Role":
                filteredData.setPredicate(p -> p.getRole().toLowerCase().contains(textSearch.getText().toLowerCase().trim()));
                break;
        }
        dataTable.getSelectionModel().select(0);
    }

    /// SortFilteredList ///
    Person[] getRanking(ObservableList<Person> courseData) {
        ObservableList<Person> tempCourseData = courseData;
        Person[] courseRanking = new Person[3];
        for (int i = 0; i < tempCourseData.size(); i++) {
            for (int j = 0; j < tempCourseData.size() - 1; j++) {
                if (Integer.parseInt(tempCourseData.get(j).getAge()) < Integer.parseInt(tempCourseData.get(j + 1).getAge())) {
                    Person tempPerson = tempCourseData.get(j);
                    tempCourseData.remove(j);
                    System.out.println(j + 1);
                    tempCourseData.add(j, tempCourseData.get(j));
                    tempCourseData.remove(j + 1);
                    tempCourseData.add(j + 1, tempPerson);
                }
            }
        }
        // Check if List size less than three //
        if (tempCourseData.size() >= 3) {
            courseRanking[0] = tempCourseData.get(0);
            courseRanking[1] = tempCourseData.get(1);
            courseRanking[2] = tempCourseData.get(2);
        } else if (tempCourseData.size() == 2) {
            courseRanking[0] = tempCourseData.get(0);
            courseRanking[1] = tempCourseData.get(1);
            courseRanking[2] = new Person("", "", "0", "", "none", "", "");
        } else if (tempCourseData.size() == 1) {
            courseRanking[0] = tempCourseData.get(0);
            courseRanking[1] = new Person("", "", "0", "", "none", "", "");
            courseRanking[2] = new Person("", "", "0", "", "none", "", "");
        } else if (tempCourseData.size() == 0) {
            courseRanking[0] = new Person("", "", "0", "", "none", "", "");
            courseRanking[1] = new Person("", "", "0", "", "none", "", "");
            courseRanking[2] = new Person("", "", "0", "", "none", "", "");
        }

        return courseRanking;
    }

    /// Set Add User TextError ///
    void setAddUserTextError(boolean[] textError) {
        // Set all wrong to be red //
        if (textError[0] == true) {
            textFirstName.getStyleClass().remove(2);
            textFirstName.getStyleClass().add("add-user-text-field-error");
        } else {
            textFirstName.getStyleClass().remove(2);
            textFirstName.getStyleClass().add("add-user-text-field");
        }
        if (textError[1] == true) {
            textLastName.getStyleClass().remove(2);
            textLastName.getStyleClass().add("add-user-text-field-error");
        } else {
            textLastName.getStyleClass().remove(2);
            textLastName.getStyleClass().add("add-user-text-field");
        }
        if (textError[2] == true) {
            textAge.getStyleClass().remove(2);
            textAge.getStyleClass().add("add-user-text-field-error");
        } else {
            textAge.getStyleClass().remove(2);
            textAge.getStyleClass().add("add-user-text-field");
        }
        if (textError[3] == true) {
            textStudentID.getStyleClass().remove(2);
            textStudentID.getStyleClass().add("add-user-text-field-error");
        } else {
            textStudentID.getStyleClass().remove(2);
            textStudentID.getStyleClass().add("add-user-text-field");
        }
        if (textError[4] == true) {
            textUserName.getStyleClass().remove(2);
            textUserName.getStyleClass().add("add-user-text-field-error");
        } else {
            textUserName.getStyleClass().remove(2);
            textUserName.getStyleClass().add("add-user-text-field");
        }
        if (textError[5] == true) {
            textPassWord.getStyleClass().remove(3);
            textPassWord.getStyleClass().add("add-user-text-field-error");
        } else {
            textPassWord.getStyleClass().remove(3);
            textPassWord.getStyleClass().add("add-user-text-field");
        }
        if (textError[6] == true) {
            textEmail.getStyleClass().remove(2);
            textEmail.getStyleClass().add("add-user-text-field-error");
        } else {
            textEmail.getStyleClass().remove(2);
            textEmail.getStyleClass().add("add-user-text-field");
        }
    }

    /// Set Add Course TextError ///
    void setAddCourseTextError(boolean[] textError) {
        // Set all wrong to be red //
        if (textError[0] == true) {
            textCourseName.getStyleClass().remove(2);
            textCourseName.getStyleClass().add("add-user-text-field-error");
        } else {
            textCourseName.getStyleClass().remove(2);
            textCourseName.getStyleClass().add("add-user-text-field");
        }
        if (textError[1] == true) {
            textCourseDescription.getStyleClass().remove(2);
            textCourseDescription.getStyleClass().add("add-user-text-field-error");
        } else {
            textCourseDescription.getStyleClass().remove(2);
            textCourseDescription.getStyleClass().add("add-user-text-field");
        }
    }
}
