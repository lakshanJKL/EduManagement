package com.developerstack.edumanage.DB;

import com.developerstack.edumanage.model.Student;
import com.developerstack.edumanage.model.UserModel;
import com.developerstack.edumanage.util.security.PasswordManger;

import java.util.ArrayList;

public class Databse {

    public static ArrayList<UserModel> usertable = new ArrayList<>();
    public static ArrayList<Student> studenttable = new ArrayList<>();


    static {
        usertable.add(
                new UserModel("Janith","Lakshan","jana@gmail.com",new PasswordManger().encrypt("123"))
        );

    }

}
