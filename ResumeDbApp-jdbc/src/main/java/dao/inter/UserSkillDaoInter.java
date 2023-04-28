package dao.inter;

import entity.UserSkill;

import java.sql.ResultSet;
import java.util.List;

public interface UserSkillDaoInter {
    List<UserSkill> getAllSkillByUserId(int userId);

    boolean removeUserSkill(int id);
    boolean addUserSkill(UserSkill us);

}
