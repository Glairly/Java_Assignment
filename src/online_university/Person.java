/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class Person implements Serializable {

    private Database dbPath = new Database();
    private String name = "";
    private String id = "";
    private String password = "";

    public Person() {
    }

    public Person(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public Database getDbPath() {
        return dbPath;
    }

    public void setDbPath(Database dbPath) {
        this.dbPath = dbPath;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static <E extends Person> int search(String name, String id, ArrayList<E> source) {
        ArrayList<Integer> byName, byId, result;
        byName = new ArrayList();
        byId = new ArrayList();
        if (source != null) {
            for (int i = 0; i < source.size(); i++) {
                if (source.get(i).getName().equals(name)) {
                    byName.add(i);
                }
                if (source.get(i).getId().equals(id)) {
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
        List<T> list = new ArrayList<T>();
        for (T t : list1) {
            if (list2.contains(t)) {
                list.add(t);
            }
        }
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

    protected static <E extends Person> int getIndex(String id, E sample, Database d) {
        Database db = d;
        ArrayList<E> arr;
        if (db.check()) {
            arr = (ArrayList<E>) db.get();
            int res = Person.search("", id, arr);
            return res;
        } else {
            System.out.println("Get Data Falied.");
            return -1;
        }
    }

    protected static void submit(Person... C) {
        ArrayList<Person> cs;
        Database db = new Database();
        for (Person c : C) {
            db = c.getDbPath();
            cs = (ArrayList<Person>) db.get();
            int isExist = Person.search(c.getName(), c.getId(), cs);
            if (isExist != -1) {
                cs.set(isExist, c);
            } else {
                if (cs == null) {
                    cs = new ArrayList<Person>();
                }
                cs.add(c);
            }
            if (!db.write(cs)) {
                System.out.println("Submit Falied.");
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "{Name : " + this.name + " Id : " + this.id + "}";
    }

}
