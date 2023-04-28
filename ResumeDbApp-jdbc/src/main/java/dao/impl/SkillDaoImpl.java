package dao.impl;

import com.google.protobuf.GeneratedMessage;
import dao.inter.AbstractDAO;
import dao.inter.SkillDaoInter;
import entity.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {
    @Override
    public List<Skill> getAllSkill() {
        List<Skill> list = new ArrayList<>();
        try(Connection c = connect()){
            Statement stmt = c.createStatement();
            stmt.execute("select *from skills");
            ResultSet rs = stmt.getResultSet();

            while(rs.next()){
                int id = rs.getInt("id");
                String skillName = rs.getString("name");
                list.add(new Skill(id,skillName));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    public boolean addSkill(Skill skill){
        boolean b;
        try(Connection c = connect()){
            PreparedStatement stmt = c.prepareStatement("insert into skills(name) value (?)",Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,skill.getName());
            b =  stmt.execute();

            ResultSet generatedKeys = stmt.getGeneratedKeys();

            if(generatedKeys.next()){
                skill.setId(generatedKeys.getInt(1));
            }
        }catch (Exception ex){
            ex.printStackTrace();
            b = false;
        }
        return b;
    }
}
