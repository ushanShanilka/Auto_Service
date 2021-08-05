package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ServiceCardDAO;
import entity.ServiceCard;
import entity.ServiceType;
import entity.Uses;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiceCardDAOImpl implements ServiceCardDAO {

   /* public String getLastServiceCardId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `servicecard` ORDER BY ScardID DESC LIMIT 1");
        if (!rst.next()) {
            return null;
        } else {
            return rst.getString(1);
        }
    }*/

    @Override
    public boolean save(ServiceCard serviceCard) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO servicecard VALUES(?,?,?,?,?,?)",
                        serviceCard.getScardID(),serviceCard.getDate (),serviceCard.getCID(),serviceCard.getVnum(),serviceCard.getUsesCode(),serviceCard.getTypeID());
    }

    @Override
    public boolean delete(String code) throws Exception {
        return false;
    }

    @Override
    public boolean update(ServiceCard serviceCard) throws Exception {
        return false;
    }

    @Override
    public List<ServiceCard> getAll() throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT * FROM servicecard");
        ArrayList<ServiceCard> list=new ArrayList<>();
        while ((rst.next())
        ) {
            list.add(new ServiceCard(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)

                ));
        }
        return list;
    }

    @Override
    public ServiceCard get(String s) throws Exception {
        return null;
    }


}
