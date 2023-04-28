package dao.inter;

import entity.Skill;

import java.util.List;

public interface SkillDaoInter {
    List<Skill> getAllSkill();
    boolean addSkill(Skill skill);
}
