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
public class Online_University {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database db = new Database("courses");
//        Staff staff = new Staff();
//        ArrayList<Course> s = new ArrayList<Course>();
//        Course c = new Course("Test", "A123", "123");
//        staff.submitCourse(c);
//        ArrayList<Course> s = new ArrayList<Course>();
//       Course c = new Course("Test", "A1", "10");
//        Course c1 = new Course("Test2", "A2", "123");
//        Course c2 = new Course("Test3", "A3", "1255");
//        s.add(c);
//        s.add(c1);
//        s.add(c2);
//        db.write(s);
//         ArrayList <Course> t ;
//         t = (ArrayList<Course>) db.get();
//         System.out.println(t.get(0).getClassDescription());
//        Course test ;
//        test = new Course("555");
//        Course.submit(test);
          db.read();
    }

}
