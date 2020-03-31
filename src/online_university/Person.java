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
    tranSkip ts = new tranSkip();

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
        byName = new ArrayList(); byId = new ArrayList();
        for (int i = 0; i < source.size(); i++) {
            if (source.get(i).getName().equals(name)) {
                byName.add(i);
            }
            if (source.get(i).getId().equals(id)) {
                byId.add(i);
            }
        }
        result = (ArrayList<Integer>) Person.intersection(byId, byName);
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

    @Override
    public String toString() {
        return "{Name : " + this.name + " Id : " + this.id + "}";
    }

}
