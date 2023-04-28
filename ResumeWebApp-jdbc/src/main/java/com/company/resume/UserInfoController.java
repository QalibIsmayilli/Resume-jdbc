
package com.company.resume;

import dao.inter.NationalityDaoInter;
import dao.inter.UserDaoInter;
import entity.Nationality;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author USER
 */
@WebServlet(name = "UserController", urlPatterns = {"/userinfo"})
public class UserInfoController extends HttpServlet {
    UserDaoInter userDao = main.Context.instanceUserDao();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        String action = request.getParameter("action");

        try {
            if (action.equals("update")) {
                NationalityDaoInter nationalityDao = Context.instanceNationalityDao();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String profileDescription = request.getParameter("description");
                String address = request.getParameter("address");
                java.sql.Date birthdate = new java.sql.Date(sdf.parse(request.getParameter("birthdate").replace('.', '-')).getTime());
                String birthplaceSrt = request.getParameter("slctCountryName");
                String nationalityStr = request.getParameter("slctNationality");
                Nationality birthplace = null, nationality = null;
                List<Nationality> list = nationalityDao.getAllCountryName();

                User user = userDao.getUserById(id);
                for (Nationality n : list) {
                    if (birthplaceSrt.equals(n.getCountryName())) {
                        user.setBirthplace(n);
                    }
                    if (nationalityStr.equals(n.getNationalityName())) {
                        user.setNationality(n);
                    }
                }
                user.setName(name);
                user.setSurname(surname);
                user.setEmail(email);
                user.setPhone(phone);
                user.setProfileDescription(profileDescription);
                user.setAddress(address);
                user.setBirthdate(birthdate);

                userDao.updateUser(user);
            }else if(action.equals("delete")){
                userDao.removeUser(id);
            }

            response.sendRedirect("users");
        } catch (Exception ex) {
            throw new FileNotFoundException("update error babyy");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = null;
        try {
            String userIdStr = request.getParameter("id");
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("id is not specified...");
            }
            Integer userId = Integer.parseInt(userIdStr);
            UserDaoInter userDao = Context.instanceUserDao();
            u = userDao.getUserById(userId);
            if (u == null) {
                throw new IllegalArgumentException("There is no User with this id");
            }

            request.setAttribute("User", u);
            request.getRequestDispatcher("userinfo.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }
}
