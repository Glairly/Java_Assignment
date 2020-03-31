/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    
    public void setFile(String file){
        Path path = Paths.get(p);
        p = path.getParent().toString() + "\\" + file + ".dat";
    }
    
    public <E> boolean write(E data) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(p));
            out.writeObject(data);
            out.close();
            System.out.println("Writing Successful");
        } catch (Exception ex) {
            System.out.println("Writing File is Error with logs : " + ex.toString());
            return false;
        }
        return true;
    }

    public void read() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(p));
            System.out.println(in.readObject());
            in.close();
        } catch (Exception e) {
            System.out.println("Reading File is Error with logs : " + e.toString());
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
            System.out.println("Reading File is Error with logs : " + e.toString());
            return null;
        }
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