/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Online_University {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database db = new Database();
        ArrayList<A> t = new ArrayList<A>();
        for(int i = 0 ; i<10;i++)
        t.add(new A());
        db.write(t);
        db.read();
        t = (ArrayList<A>)db.get();
        for(A i : t) System.out.println(i);
    }
    public static class A implements Serializable{
        static int count = 0;

        public A() {
            count++;
        }
        @Override
        public String toString() {
            return "Class A"+count; //To change body of generated methods, choose Tools | Templates.
        }
         
    }
}
