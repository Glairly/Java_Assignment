/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author USER
 */
public class Online_University {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Database db = new Database("courses");
//        Staff staff = new Staff();
//        ArrayList<Course> s = new ArrayList<Course>();
//        Course c = new Course("Test", "A123", "123");
//        staff.submitCourse(c);
        ArrayList<Course> s = new ArrayList<Course>();
        Course c = new Course("Test", "A1", "10");
        Course c1 = new Course("Test2", "A2", "123");
        Course c2 = new Course("Test3", "A3", "1255");
        s.add(c);
        s.add(c1);
        s.add(c2);
        var t = Person.search("Test", "A1", s );
        System.out.println(t);
    }

}
