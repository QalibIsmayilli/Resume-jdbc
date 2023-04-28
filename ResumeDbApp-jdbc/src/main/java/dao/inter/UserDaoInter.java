package dao.inter;

import entity.User;

import java.util.List;

public interface UserDaoInter {
    List<User> getAll();
    List<User> getSearch(String name,String surname);
    User getUserById(int i);

    boolean addUser(User u);
    boolean updateUser(User u);

    boolean removeUser(int id);

    User findByEmailAndPassword(String email,String password);

}
