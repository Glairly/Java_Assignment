/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university;

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
       ArrayList<String> t = new ArrayList<String>();
       for(int i = 0; i<10;i++) t.add(""+i);
       db.write(t);
       db.read();
    }

}
