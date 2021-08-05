package dao.custom.impl;

import dao.CrudUtil;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.ItemDAO;


import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDaoImpl implements QueryDAO {

    @Override
    public String getCID() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");

        String CID="C001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("C");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId>10){
                CID="C0"+(selectedId+1);
            }
        }
        return CID;
    }

    @Override
    public String getCode() throws Exception {
        return null;
    }


    @Override
    public int getTotalVehicle() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT count(Model) from Vehicle");
        if(set.next()){
            return set.getInt(1);
        }
        return 0;
    }

    @Override
    public int getTotalCustomer() throws Exception {
        ResultSet set=CrudUtil.execute("SELECT count(CID) from Customer");
        if (set.next()){
            return set.getInt(1);
        }
        return 0;
    }

    @Override
    public int getTotalService() throws Exception {
        //ResultSet set=CrudUtil.execute("SELECT count(")
        return 0;
    }
    public String getLastScardId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT ScardID FROM servicecard ORDER BY ScardID DESC LIMIT 1");
        return rst.next() ? rst.getString("ScardId") : null;
    }


}
