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
        ArrayList<Person> arr = new ArrayList<>();
        Student st = new Student("a","A1","12345");
        Staff sf = new Staff("b","B1","67890");
        arr.add(sf);
        arr.add(st);
        Staff.submit(sf);
        Student.submit(st);
        

    }

}
