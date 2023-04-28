package dao.inter;

import entity.EmploymentHistory;
import entity.User;
import entity.UserSkill;

import java.util.List;

public interface EmploymentHistoryDaoInter {
    List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId);
}
