/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author USER
 */
public class Person implements Serializable {

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

    public static <E extends Person> ArrayList<Integer> search(String name, String id, ArrayList<E> source) {
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
        return result;

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

    protected static <E extends Person> E getById(String id, E sample, Database d) {
        Database db = d;
        ArrayList<E> arr;
        if (db.check()) {
            arr = (ArrayList<E>) db.get();
            ArrayList<Integer> res = Person.search("", id, arr);
            if (res.size() < 2 && res.size() != 0) {
                return arr.get(res.get(0));
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
            ArrayList<Integer> res = Person.search("", id, arr);
            if (res.size() < 2 && res.size() != 0) {
                return res.get(0);
            } else {
                return -1;
            }
        } else {
            System.out.println("Get Data Falied.");
            return -1;
        }
    }

    protected static <E extends Person> void submit(Database d, E... C) {
        Database db = d;
        ArrayList<E> cs;
        if (db.check()) {
            cs = (ArrayList<E>) db.get();
            for (E c : C) {
                ArrayList<Integer> isExist = Person.search(c.getName(), c.getId(), cs);
                if (isExist.size() != 0 && isExist != null) {
                    cs.set(isExist.get(0), c);
                } else {
                    if (cs == null) {
                        cs = new ArrayList<E>();
                    }
                    cs.add(c);

                }
            }
            if (!db.write(cs)) {
                System.out.println("Submit Falied.");
                return;
            }
        } else {
            System.out.println("Submit Falied.");
            return;
        }
    }

    @Override
    public String toString() {
        return "{Name : " + this.name + " Id : " + this.id + "}";
    }

}
