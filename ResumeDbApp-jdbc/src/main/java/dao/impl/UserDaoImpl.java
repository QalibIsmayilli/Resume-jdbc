package dao.impl;

import entity.Nationality;
import entity.User;
import dao.inter.AbstractDAO;
import dao.inter.UserDaoInter;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    public User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String profileDescription = rs.getString("profile_description");
        String address = rs.getString("address");
        Date birthdate = rs.getDate("birthdate");
        int birthplaceId = rs.getInt("birthplace_id");
        int nationalityId = rs.getInt("nationality_id");
        String birthplaceStr = rs.getString("country_name");
        String nationalityStr = rs.getString("nationality_name");

        Nationality birthplace = new Nationality(birthplaceId, birthplaceStr, null);
        Nationality nationality = new Nationality(nationalityId, null, nationalityStr);

        return new User(id, name, surname, email, phone, profileDescription, address, birthdate, birthplace, nationality);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select u.* , n.country_name, c.nationality_name from `user` u\n" +
                    "left join nationality n  on n.id = u.birthplace_id\n" +
                    "left join nationality c  on c.id =  u.nationality_id");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                users.add(getUser(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> getSearch(String name, String surname) {
        List<User> users = new ArrayList<>();
        try (Connection c = connect()) {
            String sql = "select u.* , n.country_name, c.nationality_name from `user` u\n" +
                    "                    left join nationality n  on n.id = u.birthplace_id\n" +
                    "                    left join nationality c  on c.id =  u.nationality_id\n" +
                    "                    where 1=1 " ;

            if (name != null && !name.trim().isEmpty() ) {
                sql += "and u.name=?";
            }
            if (surname != null && !surname.trim().isEmpty() ) {
                sql += "and u.surname=?";
            }
            PreparedStatement stmt = c.prepareStatement(sql);
            int i = 1;
            if (name != null && !name.trim().isEmpty() ) {
                stmt.setString(i, name);
                i++;
            }
            if (surname != null && !surname.trim().isEmpty() ) {
                stmt.setString(i, surname);
            }
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                users.add(getUser(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean updateUser(User u) {
        boolean b;
        try (Connection c = connect()) {
            PreparedStatement stmt = c
                    .prepareStatement("update user set name = ?, surname = ?, email = ?, phone =?" +
                            ", profile_description =?, address=?, birthdate=?, birthplace_id=?, nationality_id=?  where id = ?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getProfileDescription());
            stmt.setString(6, u.getAddress());
            stmt.setDate(7, (java.sql.Date) u.getBirthdate());
            stmt.setInt(8, u.getBirthplace().getId());
            stmt.setInt(9, u.getNationality().getId());
            stmt.setInt(10, u.getId());

            b = stmt.execute();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                u.setId(generatedKeys.getInt(1));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            Statement stmt = connect().createStatement();
            return stmt.execute("delete from user where id =" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User u = null;
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("select u.*,n.country_name,c.nationality_name from user u \n" +
                    "LEFT JOIN nationality n on u.birthplace_id=n.id\n" +
                    "left join nationality c on u.nationality_id=c.id\n" +
                    " where email=? and password=?");
            stmt.setString(1,email);
            stmt.setString(2,password);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            rs.next();
            u = getUser(rs);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return u;
    }

    public User getUserById(int i) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select u.*, n.country_name, c.nationality_name from `user` u\n" +
                    "LEFT JOIN nationality  n on n.id = u.birthplace_id\n" +
                    "LEFT JOIN nationality c on c.id = u.nationality_id\n" +
                    "where u.id="+i);
            ResultSet rs = stmt.getResultSet();

            rs.next();
            return getUser(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into `user`(id,`name`, surname , email, phone, profile_description,address, birthdate) " +
                    "VALUES (?,?,?,?,?,?,?,?)");
            stmt.setInt(1, u.getId());
            stmt.setString(2, u.getName());
            stmt.setString(3, u.getSurname());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getPhone());
            stmt.setString(6, u.getProfileDescription());
            stmt.setString(7, u.getAddress());
            stmt.setDate(7, (java.sql.Date) u.getBirthdate());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
