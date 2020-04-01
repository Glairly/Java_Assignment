/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
class Course extends Person {

    private ArrayList<Student> students;
    private String classDescription = "";
    private ArrayList<Staff> staffs;

    public Course() {
    }

    public Course(String name, String id, String password) {
        super(name, id, password);
    }

    public Course(String des) {
        this.classDescription = des;
    }

    public void addStaff(Staff staff) {
        this.staffs.add(staff);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public ArrayList<Staff> getTeachers() {
        return staffs;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public void setTeachers(ArrayList<Staff> staffs) {
        this.staffs = staffs;
    }

    public static Course getById(String id) {
        return Person.getById(id, new Course(), new Database("courses"));
    }

    public static int getIndex(String id) {
        return Person.getIndex(id, new Student(), new Database("courses"));
    }

    public static void submit(Course c) {
        Person.submit(c, new Database("courses"));
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "Course" + s; //To change body of generated methods, choose Tools | Templates.
    }

}
