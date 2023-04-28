package main;


import dao.impl.UserDaoImpl;
import dao.inter.*;
import entity.EmploymentHistory;
import entity.Nationality;
import entity.Skill;
import entity.User;

import java.util.List;

import static main.Context.instanceNationalityDao;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDaoInter daoInter = Context.instanceUserDao();

        User  u = daoInter.findByEmailAndPassword("qalibismayilli8@gmail.com","12345");
        System.out.println(u);
    }
}
