/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.BackEnd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class Person implements Serializable {

    private Database dbPath = new Database();
    private String FirstName = "";
    private String LastName = "";
    private String UserName = "";
    private String PassWord = "";
    private String Age = "";
    private String StudentId = "";

    public Person() {
    }

    public Person(String name,String lname,String age,String stuId,String id, String password) {
        this.FirstName = name;
        this.UserName = id;
        this.PassWord = password;
        this.Age = age;
        this.StudentId = stuId;
        this.LastName = lname;
    }

    public Database getDbPath() {
        return dbPath;
    }

    public void setDbPath(Database dbPath) {
        this.dbPath = dbPath;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getUsername() {
        return UserName;
    }

    public String getPassword() {
        return PassWord;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setPassword(String password) {
        this.PassWord = password;
    }

    public static int search(String name, String id, ArrayList<Person> source) {
        ArrayList<Integer> byName, byId, result;
        byName = new ArrayList<>();
        byId = new ArrayList<>();
        if (source != null) {
            for (int i = 0; i < source.size(); i++) {
                if (source.get(i).getFirstName().equals(name)) {
                    byName.add(i);
                }
                if (source.get(i).getUsername().equals(id)) {
                    byId.add(i);
                }
            }
        }
        if (byName.size() > 0 && byId.size() > 0) {
            result = (ArrayList<Integer>) Person.intersection(byId, byName);
        } else {
            result = byName.size() > 0 ? byName : byId;
        }
        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return -1;
        }

    }

    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<>();
        list1.stream().filter((t) -> (list2.contains(t))).forEachOrdered((t) -> {
            list.add(t);
        });
        return list;
    }

    protected static Person getById(String id) {
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

    protected static int getIndex(String id, Person sample, Database d) {
        Database db = d;
        ArrayList<Person> arr;
        if (db.check()) {
            arr = (ArrayList<Person>) db.get();
            int res = Person.search("", id, arr);
            return res;
        } else {
            System.out.println("Get Data Falied.");
            return -1;
        }
    }

    protected static boolean submit(Person... C) {
        ArrayList<Person> cs;
        Database db;
        for (Person c : C) {
            db = c.getDbPath();
            cs = (ArrayList<Person>) db.get();
            int isExist = Person.search(c.getFirstName(), c.getUsername(), cs);
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
        return true;
    }


    
    @Override
    public String toString() {
        return "{Name : " + this.FirstName + " Id : " + this.UserName + "}";
    }

}
