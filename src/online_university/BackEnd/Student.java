/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.BackEnd;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Student extends Person {

    final String role = "Student";
    private char Mark = 'X';
    protected ArrayList<Course> course = new ArrayList<Course>();

    public Student() {
        super.setDbPath(new Database("students"));
    }

    public Student(String name, String id, String password) {
        super(name, id, password);
        super.setDbPath(new Database("students"));
    }

    public char getMark() {
        return Mark;
    }

    public ArrayList<Course> getCourse() {
        return course;
    }

    public void setMark(char Mark) {
        this.Mark = Mark;
    }

    public void setCourses(ArrayList<Course> course) {
        this.course = course;
    }

    public void addCourse(Course course) {
        this.course.add(course);
    }

    public String getRole() {
        return role;
    }

    public static Student getById(String id) {
        return   (Student) Person.getById(id);
    }

    public static int getIndex(String id) {
        return Person.getIndex(id, new Student(), new Database("students"));
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "Student" + s; //To change body of generated methods, choose Tools | Templates.
    }

}
