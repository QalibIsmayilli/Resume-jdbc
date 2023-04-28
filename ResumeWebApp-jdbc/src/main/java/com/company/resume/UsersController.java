package com.company.resume;

import dao.inter.UserDaoInter;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.Context;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersController", value = "/users")
public class UsersController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoInter userDao = Context.instanceUserDao();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        List<User> list = userDao.getSearch(name, surname);

        request.setAttribute("userList",list);
        request.getRequestDispatcher("users.jsp").forward(request,response);
    }

}
