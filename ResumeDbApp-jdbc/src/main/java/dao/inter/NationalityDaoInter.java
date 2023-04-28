package dao.inter;

import entity.Nationality;
import entity.UserSkill;

import java.sql.ResultSet;
import java.util.List;

public interface NationalityDaoInter {
    List<Nationality> getAllCountryName();
}
