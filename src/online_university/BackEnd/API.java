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
        Database db = new Database();
        db._READ_();
    }

    /**
     * Get all Account 
     * @return
     */
    public static ArrayList<Person> getAll() {
        return Database.getPerson(); 
    }

    public static ArrayList<Person> getAllStudent() {
        Person st = new Student();
        Database db = st.getDbPath();
        return (ArrayList<Person>) db.get();
    }

    public static ArrayList<Person> getAllStaff() {
        Person st = new Staff();
        Database db = st.getDbPath();
        return (ArrayList<Person>) db.get();
    }

    public static ArrayList<Person> getAllAdmin() {
        Person st = new Admin();
        Database db = st.getDbPath();
        return (ArrayList<Person>) db.get();
    }

    public static ArrayList<Person> getAllCourse() {
        Person st = new Course();
        Database db = st.getDbPath();
        return (ArrayList<Person>) db.get();
    }

    /**
     * Return an Index from Object Database
     *
     * @param Person and it's Child
     * @param o
     * @return Index
     */
    public static <E extends Person> int searchByObject(E object) {
        Database db = object.getDbPath();
        return Person.search(object.getFirstName(), object.getUsername(), (ArrayList<Person>) db.get());
    }

    public static Person searchById(String id) {
        ArrayList<Person> arr = Database.getPerson();
        if (arr != null) {
            int res = Person.search("", id, arr);
            if (res != -1) {
                return arr.get(res);
            } else {
                return null;
            }
        } else {
            System.out.println("Get Data Falied.");
            return null;
        }
    }

    
    public static <E extends Person> boolean saveToDatabase(E o) {
        return E.submit(o);
    }
}
