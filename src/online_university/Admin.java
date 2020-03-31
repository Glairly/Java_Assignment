/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university;

/**
 *
 * @author USER
 */
public class Admin extends Person{
    
    final String role = "Admin";
    public Admin() {
    }

    public Admin(String name, String id, String password) {
        super(name, id, password);
    }
    
    public String getRole() {
        return role;
    }
    @Override
    public String toString() {
        String s = super.toString();
        return "Admin"+s; //To change body of generated methods, choose Tools | Templates.
    }
}
