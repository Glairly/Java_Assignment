package online_university.FrontEnd;

import java.util.ArrayList;
import java.util.Arrays;
import online_university.BackEnd.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class TestGUI {

    public static void main(String[] args) {

        //Authority.registor(new Staff("Staff1", "12345"));
//        Staff st = (Staff) Authority.login("Staff1", "12345");
//        System.out.println(st.getCourses().get(0).getStudents());
//        System.out.println();
//    API.saveToCustom("Sessions", new Session(new Course("test3",null,null)));
        //           Session.end_Session( new Session(new Course("test",null,null)));
        API._INIT_DATABASE_();
        Admin am = new Admin("admin", "admin");
        API.saveToDatabase(am);
        Staff st = new Staff("Staff1", "12345");
        st.setFullName("Glairly");
        Staff st2 = new Staff("Charoen", "c12345");
        st2.setFullName("AJ Charoen");
        Course c, c1;
        c = new Course("Calculus", null, null);
        c.setClassDescription("Cal for your mom");
        c.setFirstName("Calculus");
        c1 = new Course("CalculusII", null, null);
        c1.setClassDescription("CalII for your mom");
        c1.setFirstName("CalculusII");
        var arrC = st.getCourses();
        arrC = new ArrayList<>();
        arrC.addAll(Arrays.asList(c, c1));
        st.setCourses(arrC);
        c.addStaffs(st);
        c1.addStaffs(st);
        Student s, s1, s2;
        Student foy = new Student("Foy", "yoF", "10", "62010884", "FoyFoy", "yoFyoF", "FoyFoy@hotmail.com");
        API.saveToDatabase(foy);
        s = new Student("Drgn", "1", "20", "62010001", "Drgn", "123", "test@mail");
        s1 = new Student("Drgn", "2", "21", "62010002", "Drgn2", "123", "test@mail");
        s2 = new Student("Drgn", "3", "22", "62010003", "Drgn3", "123", "test@mail");
        API.saveToDatabase(c, c1, s, s1, s2);
        API.saveToDatabase(st, st2);
    }
}
