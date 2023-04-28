package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.EmploymentHistoryDaoInter;
import entity.EmploymentHistory;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {
    public EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception{
        Integer id = rs.getInt("id");
        String header = rs.getString("header");
        Date beginDate = rs.getDate("begin_date");
        Date endDate = rs.getDate("end_date");
        int userId = rs.getInt("user_id");

        User user = new User(userId);
        return new EmploymentHistory(id,header,beginDate,endDate,user);
    }
    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryByUserId(int userId) {
        List<EmploymentHistory> userEmploymentHistory = new ArrayList<>();
        try (Connection c = connect()) {
            Statement ps = c.createStatement();
            ps.execute("select eh.*, u.name from employment_history eh "+
                    "left join `user` u on u.id = eh.user_id " +
                    "where eh.user_id =" +userId);
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                userEmploymentHistory.add(getEmploymentHistory(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userEmploymentHistory;    }
}
