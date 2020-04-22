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
public class API {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        API._INIT_DATABASE_();
//        Authority.registor(new Staff("Glairly","g12345"));
//        Authority.registor(new Staff("Gai","g12345"));
//        System.out.println(Authority.login("Gai", "g12345"));
//        System.out.println(API.getCustom("Register"));
//        Staff user = (Staff) Authority.login("Staff1", "12345");
//        System.out.println(API.getAllCourse().get(1).getStudents());
        for (Course c : API.getAllCourse()) {
            System.out.println(c.getStudents());
        }
        //      System.out.println(API.getAllCourse().get(0).getStudents());
        //       API._INIT_DATABASE_();
        //       System.out.println(API.getAllSession());
//        Database db = new Database("Sessions");
//        db.write(null);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean _INIT_DATABASE_() {
        Database db = new Database();
        return db._init_();
    }

    public static boolean _NEW_DATABASE_(String fileName) {
        Database db = new Database();
        return db.newDatabase(fileName, null);
    }

    public static ArrayList<ArrayList<Object>> _GET_DATABASE_() {
        Database db = new Database("Lists");
        ArrayList<ArrayList<Object>> arr = new ArrayList<>();
        ArrayList<String> list = (ArrayList<String>) db.get();
        for (String file : list) {
            db.setPath_custom(file);
            arr.add((ArrayList<Object>) db.get());
        }
        return arr;
    }

    /**
     * Get all Account
     *
     * @return
     */
    public static ArrayList<Student> getAllStudent() {
        Person st = new Student();
        Database db = st.getDbPath();
        return (ArrayList<Student>) db.get();
    }

    public static ArrayList<Staff> getAllStaff() {
        Person st = new Staff();
        Database db = st.getDbPath();
        return (ArrayList<Staff>) db.get();
    }

    public static ArrayList<Admin> getAllAdmin() {
        Person st = new Admin();
        Database db = st.getDbPath();
        return (ArrayList<Admin>) db.get();
    }

    public static ArrayList<Course> getAllCourse() {
        Person st = new Course();
        Database db = st.getDbPath();
        return (ArrayList<Course>) db.get();
    }

    public static ArrayList<Person> getAllRegister() {
        Database db = new Database();
        db.setPath_Register();
        return (ArrayList<Person>) db.get();
    }

    public static ArrayList<Session> getAllSession() {
        Database db = new Database();
        db.setPath_Sessions();
        return (ArrayList<Session>) db.get();
    }

    public static ArrayList<Object> getCustom(String file) {
        Database db = new Database(file);
        return (ArrayList<Object>) db.get();
    }

    /**
     * Return an Index from Object Database
     *
     * @param Person and it's Child
     * @param o
     * @return Index
     */
    public static <E extends Person> boolean saveToDatabase(E... o) {
        return E.submit(o);
    }

    public static <E extends Person> boolean saveToDatabase(boolean checkDuplicate, E... o) {
        if (checkDuplicate) {
            return E.submit(o);
        } else {
            return E.submit(false, o);
        }
    }

    public static <E> boolean RemoveFromDatabase(String file, E data) {
        Database db = new Database(file);
        ArrayList<E> arr = (ArrayList<E>) db.get();
        if (arr != null) {
            for (E i : arr) {
                if (i.toString().equals(data.toString())) {
                    arr.remove(i);
                    return db.write(arr);
                }
            }
        }
        return false;
    }

    /**
     * Pass file name and your data
     *
     * @param <E>
     * @param file
     * @param data
     * @return boolean
     */
    public static <E extends Person> boolean saveToCustom(String file, E... data) {
        Database db = new Database(file);
        ArrayList<Person> cs;
        if (data != null) {
            for (Person c : data) {
                cs = (ArrayList<Person>) db.get();
                int isExist = Person.search(null, c.getUserName(), cs);
                if (isExist != -1) {
                    cs.set(isExist, c);
                } else {
                    if (cs == null) {
                        cs = new ArrayList<>();
                    }
                    cs.add(c);
                }
                if (!db.write(cs)) {
                    System.out.println("Submit Falied.");
                    return false;
                }
            }
        }
        return true;
    }

    public static <E extends Person> boolean saveToCustom(boolean checkDuplicate, String file, E... data) {
        Database db = new Database(file);
        ArrayList<Person> cs;
        if (data != null) {
            for (Person c : data) {
                cs = (ArrayList<Person>) db.get();
                if (checkDuplicate) {
                    int isExist = Person.search(null, c.getUserName(), cs);
                    if (isExist != -1) {
                        cs.set(isExist, c);
                    } else {
                        if (cs == null) {
                            cs = new ArrayList<>();
                        }
                        cs.add(c);
                    }

                } else {
                    if (cs == null) {
                        cs = new ArrayList<>();
                    }
                    cs.add(c);
                }
                if (!db.write(cs)) {
                    System.out.println("Submit Falied.");
                    return false;
                }
            }
        }
        return true;
    }
}
