/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.BackEnd;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author USER
 */
class Course extends Person {
    private Database dbPath = new Database("courses");
    private ArrayList<Student> students = new ArrayList<Student>();
    private String classDescription = "";
    private ArrayList<Staff> staffs = new ArrayList<Staff>();

    public Course() {
    }

    public Database getDbPath() {
        return dbPath;
    }

    public Course(String name, String id, String password) {
        super(name, id, password);
    }

    public Course(String des) {
        this.classDescription = des;
    }

    public void addStaff(Staff... staff) {
        for (Staff i : staff) {
            this.staffs.add(i);
        }
    }

    public void addStudent(Student... student) {
        for (Student i : student) {
            this.students.add(i);
        }
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
        return (Course)Person.getById(id);
    }

    public static int getIndex(String id) {
        return Person.getIndex(id, new Student(), new Database("courses"));
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "Course" + s; //To change body of generated methods, choose Tools | Templates.
    }

}
