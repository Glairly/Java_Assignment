package online_university.FrontEnd;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class Persons {

    private final SimpleStringProperty num;
    private final SimpleStringProperty FullName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty midterm;
    private final SimpleStringProperty finals;
    private final SimpleStringProperty grade;
    private final SimpleStringProperty totalScore;
    private final SimpleStringProperty totalAttend;

    private CheckBox select;

    Persons(String Numname, String fName, String emails, String totalScore, String mid, String fi, String gra, String totalAttend) {
        this.num = new SimpleStringProperty(Numname);
        this.FullName = new SimpleStringProperty(fName);
        this.email = new SimpleStringProperty(emails);
        this.totalScore = new SimpleStringProperty(totalScore);
        this.midterm = new SimpleStringProperty(mid);
        this.finals = new SimpleStringProperty(fi);
        this.grade = new SimpleStringProperty(gra);
        this.totalAttend = new SimpleStringProperty(totalAttend);
        this.select = new CheckBox();
     //   this.addselect = new CheckBox();
    }

    Persons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNum() {
        return num.get();
    }

    public void setNum(String Numname) {
        num.set(Numname);
    }

    public String getFullName() {
        return FullName.get();
    }

    public void setFullName(String lName) {
        FullName.set(lName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String emails) {
        email.set(emails);
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public String getMidterm() {
        return midterm.get();
    }

    public void setMidterm(String mid) {
        midterm.set(mid);
    }

    public String getFinals() {
        return finals.get();
    }

    public void setFinals(String fi) {
        finals.set(fi);
    }

    public String getGrade() {
        return grade.get();
    }

    public void setGrade(String gra) {
        grade.set(gra);
    }

    public String getTotalScore() {
        return totalScore.get();
    }

    public void setTotalScore(String sc) {
        totalScore.set(sc);
    }

    public String getTotalAttend() {
        return totalAttend.get();
    }

    public void setTotalAttend(String sc) {
        totalAttend.set(sc);
    }
    
//    public CheckBox getAddSelect() {
//        return addselect;
//    }
//
//    public void setAddSelect(CheckBox addselect) {
//        this.addselect = addselect;
//    }
}
