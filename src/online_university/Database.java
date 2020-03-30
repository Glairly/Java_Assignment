/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Database {

    private String p;

    public Database() {
        Path path = Paths.get(System.getProperty("user.dir"));
        p = new String(path.toString());
        p = p.replace("\\", "\\\\");
        p += "\\\\data\\\\database\\\\test.dat";
    }

    public Database(String file) {
        Path path = Paths.get(System.getProperty("user.dir"));
        p = new String(path.toString());
        p = p.replace("\\", "\\\\");
        p += "\\\\data\\\\database\\\\" + file;
    }

    public void setPath(String path) {
        p = path;
    }

    public <E> boolean write(ArrayList<E> data) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(p));
            for (int i = 0; i < data.size(); i++) {
                out.writeObject(data.indexOf(i));
            }
            out.close();
            System.out.println("Writing Successful");
        } catch (Exception ex) {
            System.out.println("Writing Fails with logs : " + ex.toString());
            return false;
        }
        return true;
    }

    public void read() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(p));

            System.out.println(in.readObject());

//          data = in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println("Reading File is Error with logs : " + e.toString());
        }
    }

    public ArrayList<Object> get() {
        ArrayList<Object> data = new ArrayList<Object>();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(p));
            while (in.available() != 0) {
                System.out.println(in.read());
            }
//          data = in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println("Reading File is Error with logs : " + e.toString());
        }
        return data;
    }
}
