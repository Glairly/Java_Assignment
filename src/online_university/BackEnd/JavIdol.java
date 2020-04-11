/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_university.BackEnd;

/**
 *
 * @author USER
 */
class JavIdol extends Person {

    public JavIdol() {
    }

    public JavIdol(String username, String password) {
        super(username, password);
    }

    public JavIdol(String name, String lname, String age, String stuId, String id, String password, String email) {
        super(name, lname, age, stuId, id, password, email);
    }
    
}
