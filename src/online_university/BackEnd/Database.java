/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.BackEnd;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author USER
 */
public class Database implements Serializable {

    private String p;
    private String file = "test.dat";

    public Database() {
        Path path = Paths.get(System.getProperty("user.dir"));
        p = new String(path.toString());
        p = p.replace("\\", "\\\\");
        p += "\\\\data\\\\database\\\\" + file;
    }

    public Database(String file) {
        Path path = Paths.get(System.getProperty("user.dir"));
        p = new String(path.toString());
        p = p.replace("\\", "\\\\");
        p += "\\\\data\\\\database\\\\" + file + ".dat";
        this.file = file;
    }

    public void _init_() {
        this.setPath_Admins();
        this.write(null);
        this.setPath_Staffs();
        this.write(null);
        this.setPath_Students();
        this.write(null);
        this.setPath_Courses();
        this.write(null);
    }

    public void _READ_() {
        this.setPath_Admins();
        this.read();
        this.setPath_Staffs();
        this.read();
        this.setPath_Students();
        this.read();
        this.setPath_Courses();
        this.read();
    }

    public void setPath(String path) {
        p = path;
    }

    public void setFile(String file) {
        Path path = Paths.get(p);
        p = path.getParent().toString() + "\\" + file + ".dat";
    }

    public void setPath_Admins() {
        Path path = Paths.get(p);
        p = path.getParent().toString() + "\\admins.dat";
    }

    public void setPath_Students() {
        Path path = Paths.get(p);
        p = path.getParent().toString() + "\\students.dat";
    }

    public void setPath_Staffs() {
        Path path = Paths.get(p);
        p = path.getParent().toString() + "\\staffs.dat";
    }

    public void setPath_Courses() {
        Path path = Paths.get(p);
        p = path.getParent().toString() + "\\courses.dat";
    }

    public <E> boolean write(E data) {
        ObjectOutputStream out;
        // write backup
        try {
            Path path = Paths.get(p);
            out = new ObjectOutputStream(new FileOutputStream(path.getParent().toString() + "\\" + "backup" + "\\" + file + Calendar.getInstance().getTimeInMillis() + ".dat"));
            E backup = (E) this.get();
            out.writeObject(backup);
        } catch (Exception ex) {
            System.out.println("Writing Backup File is Error with logs : " + ex.toString());
        }
        // write file
        try {
            out = new ObjectOutputStream(new FileOutputStream(p));
            out.writeObject(data);
            System.out.println("Writing Successful");
        } catch (Exception ex) {
            System.out.println("Writing File is Error with logs : " + ex.toString());
            return false;
        }

        try {
            out.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return true;
    }

    public boolean read() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(p));
            System.out.println(in.readObject());
            in.close();
            return true;
        } catch (Exception e) {
            System.out.println("Reading File is Error with logs : " + e.toString());
            return false;
        }
    }

    public boolean check() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(p));
            in.close();
            return true;
        } catch (Exception e) {
            System.out.println("Reading File is Error with logs : " + e.toString());
            return false;
        }
    }

    public Object get() {
        Object data;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(p));
            data = in.readObject();
            in.close();
            return data;
        } catch (Exception e) {
            System.out.println("Getting data from File is Error with logs : " + e.toString());
            return null;
        }
    }

    public static ArrayList<Person> getPerson() {
        ArrayList<Person> arr = new ArrayList<>();
        Database db = new Database("students");
        var t = db.get();
        if (t != null) {
            arr.addAll((ArrayList<Person>) t);
        }
        db.setPath_Admins();
        t = db.get();
        if (t != null) {
            arr.addAll((ArrayList<Person>) t);
        }
        db.setPath_Staffs();
        t = db.get();
        if (t != null) {
            arr.addAll((ArrayList<Person>) t);
        }
        return arr;
    }

    @Override
    public String toString() {
        return "Database{" + "p=" + p + ", file=" + file + '}';
    }

}
/*
  Manual 
    public static void main(String[] args) {
        // Create writer and reader
        Database db = new Database();
        // sample Object
        ArrayList<A> t = new ArrayList<A>();
        for (int i = 0; i < 10; i++) {
            t.add(new A());
        }
        // write to defaule file which is test.dat
        db.write(t);
        // read for show only
        db.read();
        
        // test keep value in another variable
        ArrayList<A> t1;
        // get data 
        t1 = (ArrayList<A>) db.get();
        for(A i : t1) System.out.println(i);
        
        // print data's class
        System.out.println(db.get().getClass());
    }
    // Tester class
    public static class A implements Serializable {
        static int count = 0;

        public A() {
            count++;
        }
        @Override
        public String toString() {
            return "Class A" + count; //To change body of generated methods, choose Tools | Templates.
        }
    }
 */
