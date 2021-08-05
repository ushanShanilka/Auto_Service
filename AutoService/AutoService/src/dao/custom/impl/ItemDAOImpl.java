package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item item) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO item VALUES(?,?,?,?)", item.getCode(),
                        item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
    }

    @Override
    public boolean delete(String code) throws Exception {
        return CrudUtil.execute("DELETE FROM Item WHERE Code=?", code);
    }

    @Override
    public boolean update(Item item) throws Exception {
        return false;
    }

    @Override
    public List<Item> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Item(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }
        System.out.println(list);
        return list;
    }

    @Override
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
}
