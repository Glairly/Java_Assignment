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

    private transient Database db = new Database("courses");
    private transient ArrayList<Course> cs;
    final String role = "Staff";
    private ArrayList<Course> courses;

    public Staff() {
    }

    public Staff(String name, String id, String password) {
        super(name, id, password);
    }

    public void addCourses(Course course) {
        this.courses.add(course);
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

    public void submitCourse(Course c) {
        if (db.read()) {
            cs = (ArrayList<Course>) db.get();
            cs.add(c);
            if (!db.write(cs)) {
                System.out.println("Submit Falied.");
                return;
            }
        } else {
            System.out.println("Submit Falied.");
            return;
        }
    }

    public void findCourse(){
      
    }
    
    @Override
    public String toString() {
        String s = super.toString();
        return "Staff" + s; //To change body of generated methods, choose Tools | Templates.
    }
}
