/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.BackEnd;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.util.Pair;

/**
 *
 * @author USER
 */
public class Course extends Person {

    public static void main(String[] args) {
        Course c = new Course("Calculus", null, null);
        Course c1 = new Course("CalculusII", null, null);
        Course c2 = new Course("Circuit", null, null);
        API.saveToDatabase(c, c1, c2);
    }

    final String role = "Course";
    private ArrayList<Pair<Student, Grading>> students = new ArrayList<Pair<Student, Grading>>();
    private String classDescription = "";
    private Staff staffs = new Staff();
    private ArrayList<Session> sessions;
    private String teacher_Name = "";
    private String Mark = "X";

    public Course() {
        super();
    }

    public String getMark() {
        return Mark;
    }

    public void setMark(String Mark) {
        this.Mark = Mark;
    }

    public Course(String className, Staff stffs, ArrayList<Pair<Student, Grading>> students) {
        this.students = students;
        this.classDescription = className + " Description is Empty";
        this.setUserName(className);
        this.staffs = stffs;
        if (this.staffs != null) {
            this.teacher_Name = this.staffs.getFullName();
        }
    }

    public Course(String des) {
        this.classDescription = des;
    }

    public void addStudent(Student... student) {
        if (this.students == null) {
            this.setStudents(new ArrayList<Pair<Student, Grading>>());
        }
        if (student != null) {
            for (Student st : student) {
                if (st != null) {
                    this.students.add(new Pair<>(st, new Grading()));
                    this.setAge("" + this.students.size());
                }
            }
        }
    }

    public void AttendingStudent() {
        if (sessions != null && sessions.size() > 0) {
            for (Student i : this.getLastestSession().getAttended_Students()) {
                System.out.println(this.getStudent(i));
                this.getStudent(i).getValue().Attending();
            }
        }
    }

    public void addStaffs(Staff staff) {
        if (this.staffs == null) {
            this.staffs = new Staff();
        }
        this.staffs = staff;
        this.teacher_Name = this.staffs.getFullName();
    }

    public ArrayList<Pair<Student, Grading>> getStudents() {
        return students;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public Staff getStaffs() {
        return staffs;
    }

    public void setStudents(ArrayList<Pair<Student, Grading>> students) {
        this.students = students;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public void setStaffs(Staff staffs) {
        this.staffs = staffs;
        if (this.staffs != null) {
            this.teacher_Name = this.staffs.getFullName();
        }
    }

    public static Course getById(String id) {
        return (Course) Person.getById(id);
    }

    public static int getIndex(String id) {
        return Person.getIndex(id, new Student(), new Database("courses"));
    }

    public Grading getStudentGrading(Student st) {
        ArrayList<Student> arr = new ArrayList<>();
        for (Pair<Student, Grading> s : this.students) {
            if (s.getKey().getUserName().equals(st.getUserName())) {
                return s.getValue();
            }
        }
        return null;
    }

    public boolean isStudentExist(Student st) {
        if (this.students != null && this.students.size() > 0) {
            ArrayList<Student> arr = new ArrayList<>();
            for (Pair<Student, Grading> s : this.students) {
                if (s.getKey().getUserName().equals(st.getUserName())) {
                    return true;
                }
            }
        }

        return false;
    }

    public String getTeacher_Name() {
        return teacher_Name;
    }

    public void setTeacher_Name(String teacher_Name) {
        this.teacher_Name = teacher_Name;
    }

    public void upDateLastestSession() {
        Session local_ss = null;
        var allSS = API.getAllSession();
        Session ss = this.getLastestSession();
        if (ss != null) {
            for (Session SS : allSS) {
                if (SS.toString().equals(ss.toString())) {
                    this.setLastestSession(SS);
                }
            }
        }
    }

    public Pair<Student, Grading> getStudent(Student st) {
        ArrayList<Student> arr = new ArrayList<>();
        for (Pair<Student, Grading> s : this.students) {
            if (s.getKey().getUserName().equals(st.getUserName())) {
                return s;
            }
        }
        return null;
    }

    public Pair<Student, Grading> getStudent(String stuId) {
        ArrayList<Student> arr = new ArrayList<>();
        for (Pair<Student, Grading> s : this.students) {
            if (s.getKey().getStudentId().equals(stuId)) {
                return s;
            }
        }
        return null;
    }

    public static ArrayList<Course> getCouresByStudent(Student st) {
        var arrC = API.getAllCourse();
        ArrayList<Course> result = new ArrayList<>();
        for (var i : arrC) {
            if (i.getStudent(st) != null) {
                result.add(i);
            }
        }
        return result;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    public void setLastestSession(Session sessions) {
        this.sessions.set(this.sessions.size() - 1, sessions);
    }

    public Session getLastestSession() {
        if (this.sessions != null && this.sessions.size() > 0) {
            return this.sessions.get(this.sessions.size() - 1);
        } else {
            return null;
        }
    }

    public void addSession(Session ss) {
        if (this.sessions == null) {
            this.sessions = new ArrayList();
        }
        this.sessions.add(ss);
    }

    public void removeStudent(Student stu) {
        for (Pair<Student, Grading> c : this.students) {
            if (stu.toString().equals(c.getKey().toString())) {
                var arr = this.students;
                arr.remove(c);
                this.setStudents(arr);
                this.setAge("" + this.students.size());
                return;
            }
        }
    }

    public void upDateStudent() {
        var allSt = API.getAllStudent();
        ArrayList<Pair<Student, Grading>> arr = new ArrayList();
        ArrayList<Pair<Student, Grading>> arrA = new ArrayList();
        if (this.students != null) {
            for (Pair<Student, Grading> s : this.students) {
                if (allSt != null) {
                    for (Student ss : allSt) {
                        if (s.getKey().toString().equals(ss.toString())) {
                            arr.add(s);
                            arrA.add(new Pair<>(ss, s.getValue()));
                        }
                    }
                }
            }
        }
        if (arr != null) {
            for (var i : arr) {
                this.students.remove(i);
            }
        }
        if (arrA != null) {
            for (var i : arrA) {
                this.students.add(i);
            }
        }
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "Course" + s; //To change body of generated methods, choose Tools | Templates.
    }
}
