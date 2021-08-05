package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.UsesDAO;
import entity.Item;
import entity.Uses;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsesDAOImpl implements UsesDAO {

    @Override
    public boolean save(Uses uses) throws Exception {
        System.out.println(uses);
        return CrudUtil.execute
                ("INSERT INTO Uses VALUES(?,?,?,?,?,?)",
                        uses.getUseCode(), uses.getCode(),
                        uses.getDiscription(), uses.getUnitPrice(),
                        uses.getQty(), uses.getTotal());

    }

    @Override
    public boolean delete(String UseCode) throws Exception {
        return CrudUtil.execute("DELETE FROM Uses WHERE UseCode=?", UseCode);
    }

    @Override
    public boolean update(Uses uses) throws Exception {
        return CrudUtil.execute("UPDATE Uses SET code=?, discription=?, unitprice=?,qty=?,total=? WHERE UseCode=?", uses.getCode(), uses.getDiscription(), uses.getUnitPrice(), uses.getQty(), uses.getTotal(), uses.getUseCode());
    }

    @Override
    public List<Uses> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Uses");
        ArrayList<Uses> list = new ArrayList<>();
        while ((rst.next())
        ) {
            list.add(new Uses(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)));

        }
        return list;
    }



    public Item get(String code) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE Code=?", code);
        if (rst.next()) {
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
        } else {
            return null;
        }
    }


    @Override
    public Uses getUses ( String useCode ) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Uses WHERE UseCode=?", useCode);
        if (rst.next()) {
            return new Uses(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6));
        } else {
            return null;
        }
    }
}
