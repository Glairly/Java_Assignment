/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.BackEnd;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class Grading implements Serializable {

    private String Grade = "X", Other_score = "0", MidExam = "0", FinalExam = "0";

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String Grade) {
        this.Grade = Grade;
    }

    public String getOther_score() {
        return Other_score;
    }

    public void setOther_score(String Other_score) {
        this.Other_score = Other_score;
    }

    public String getMidExam() {
        return MidExam;
    }

    public void setMidExam(String MidExam) {
        this.MidExam = MidExam;
    }

    public String getFinalExam() {
        return FinalExam;
    }

    public void setFinalExam(String FinalExam) {
        this.FinalExam = FinalExam;
    }

    @Override
    public String toString() {
        return "Grading{Score : " + this.Other_score + " Grade : " + this.Grade + "}"; //To change body of generated methods, choose Tools | Templates.
    }

    public String getScore() {
        return Other_score;
    }

    public void setScore(String Score) {
        this.Other_score = Score;
    }
}
