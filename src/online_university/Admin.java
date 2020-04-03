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
public class Admin extends Person {

    private static Database dbPath = new Database("admins");
    final String role = "Admin";

    public Admin() {
    }

    public Admin(String name, String id, String password) {
        super(name, id, password);
    }

    public static Database getDbPath() {
        return dbPath;
    }

    public String getRole() {
        return role;
    }

    public static Admin getById(String id) {
        return (Admin) Person.getById(id);
    }

    public static int getIndex(String id) {
        return Person.getIndex(id, new Admin(), new Database("admins"));
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "Admin" + s; //To change body of generated methods, choose Tools | Templates.
    }
}
