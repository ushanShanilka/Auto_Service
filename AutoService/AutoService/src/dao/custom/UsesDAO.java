package dao.custom;

import dao.CrudDAO;
import entity.Uses;

import java.sql.SQLException;

public interface UsesDAO extends CrudDAO <Uses,String> {
    public Uses getUses(String useCode) throws SQLException, ClassNotFoundException;
}
