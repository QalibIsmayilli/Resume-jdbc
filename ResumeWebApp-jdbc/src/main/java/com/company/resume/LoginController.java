package com.company.resume;

import com.company.resume.util.ControllerUtil;
import dao.inter.UserDaoInter;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.Context;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserDaoInter userDao = Context.instanceUserDao();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User u = userDao.findByEmailAndPassword(email, password);
            if (u == null) {
                throw new IllegalArgumentException("email or password is incorrect !!!");
            }
            request.getSession().setAttribute("loggedInUser", u);
            response.sendRedirect("users");
        }catch (Exception ex){
            ControllerUtil.errorPage(response,ex);
        }
    }
}
