/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.BackEnd;

import java.util.ArrayList;
import java.util.Date;
import javafx.util.Pair;

/**
 *
 * @author USER
 */
public class Session extends Person {

    public static void main(String[] args) {
        ArrayList allC = API.getAllCourse();
        var c = API.getAllCourse().get(2);
        System.out.println(Person.search(c, allC));

    }

    private Course course;
    private String sessionId = null;
    private Date date;
    private ArrayList<Student> attended_Student = new ArrayList();

    public Session(Course c) {
        this.course = c;
        this.sessionId = c.getUserName() + date;
    }

    public static boolean start_Session(Session ss) {
        ss.date = new Date();
        if (ss.sessionId == null) {
            ss.sessionId = ss.course.getUserName() + ss.date;
        }
        return API.saveToCustom("Sessions", ss);
    }

    public static boolean end_Session(Session ss) {
        return API.RemoveFromDatabase("Sessions", ss);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<Student> getAttended_Students() {
        return attended_Student;
    }

    public boolean addAtended_Student(Student st) {
        return this.attended_Student.add(st);
    }

    public void setAttended_Student(ArrayList<Student> attended_Student) {
        this.attended_Student = attended_Student;
    }

    @Override
    public String toString() {
        return "Session : " + this.course.toString() + " Attend at : " + this.date; //To change body of generated methods, choose Tools | Templates.
    }

}
