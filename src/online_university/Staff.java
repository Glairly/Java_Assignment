/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author USER
 */
public class Staff extends Person {

    private Database dbPath = new Database("staffs");
    final String role = "Staff";
    private ArrayList<Course> courses = new ArrayList<Course>();

    public Staff() {
        super.setDbPath(new Database("students"));
    }

    public Staff(String name, String id, String password) {
        super(name, id, password);
        super.setDbPath(new Database("staffs"));
    }

    public Database getDbPath() {
        return dbPath;
    }

    public void addCourses(Course... course) {
        for (Course i : course) {
            this.courses.add(i);
        }
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String getRole() {
        return role;
    }

    public static Staff getById(String id) {
        return (Staff) Person.getById(id);
    }

    public static int getIndex(String id) {
        return Person.getIndex(id, new Staff(), new Database("staffs"));
    }

    public void findCourse() {

    }

    @Override
    public String toString() {
        String s = super.toString();
        return "Staff" + s; //To change body of generated methods, choose Tools | Templates.
    }
}
