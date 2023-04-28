package dao.impl;

import dao.inter.UserSkillDaoInter;
import entity.Skill;
import entity.User;
import entity.UserSkill;
import dao.inter.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    public UserSkill getUserSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("userSkill_id");
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        int power = rs.getInt("power");
        String skillName = rs.getString("skill_name");

        Skill skill = new Skill(skillId, skillName);
        User user = new User(userId);
        return new UserSkill(id, user, skill, power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> userSkills = new ArrayList<>();
        try (Connection c = connect()) {
            Statement ps = c.createStatement();
            ps.execute("select u.*, us.id AS userSkill_id, us.skill_id, s.name as skill_name, us.power from user_skills us\n" +
                    "left join `user` u  on u.id = us.user_id\n" +
                    "left join skills s on s.id = us.skill_id\n" +
                    "where u.id=" + userId);
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                userSkills.add(getUserSkill(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userSkills;
    }

    @Override
    public boolean removeUserSkill(int id) {
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("delete from user_skills where id=? ");
            stmt.setInt(1,id);
            return stmt.execute();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUserSkill(UserSkill uk) {
        boolean b;
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("insert into user_skills(user_id,skill_id,power) VALUES (?,?,?)");
            stmt.setInt(1,uk.getUser().getId());
            stmt.setInt(2,uk.getSkill().getId());
            stmt.setInt(3,uk.getPower());
            b = stmt.execute();

        }catch (Exception ex){
            ex.printStackTrace();
            b=false;
        }
        return b;
    }

}
