package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.NationalityDaoInter;
import entity.Nationality;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NationalityDaoImpl extends AbstractDAO implements NationalityDaoInter {
    @Override
    public List<Nationality> getAllCountryName() {
        List<Nationality> list = new ArrayList<>();
        try(Connection c = connect()){
            Statement stmt = c.createStatement();
            stmt.execute("select *from nationality");
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                int id = rs.getInt("id");
                String countryName = rs.getString("country_name");
                String nationalityName = rs.getString("nationality_name");
                list.add(new Nationality(id, countryName,nationalityName));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
