package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ServiceTypeDAO;
import entity.ServiceType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiceTypeDAOImpl implements ServiceTypeDAO {

    @Override
    public boolean save(ServiceType serviceType) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO servicetype VALUES(?,?,?)",serviceType.getTypeId(),
                        serviceType.getName(),serviceType.getDetails());
    }

    @Override
    public boolean delete(String TypeID) throws Exception {
        return CrudUtil.execute("DELETE FROM servicetype WHERE TypeID=?",TypeID);
    }

    @Override
    public boolean update(ServiceType serviceType) throws Exception {
        return CrudUtil.execute("UPDATE servicetype SET name=?, details=? WHERE TypeID=?",serviceType.getName(),serviceType.getDetails(),serviceType.getTypeId());
    }

    @Override
    public List<ServiceType> getAll() throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT * FROM servicetype");
        ArrayList<ServiceType> list=new ArrayList<>();
        while ((rst.next())
        ) {
            list.add(new ServiceType(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)));
        }
        return list;
    }

    @Override
    public ServiceType get(String TypeID) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM servicetype WHERE TypeID=?",TypeID);
        if(rst.next()){
            return new ServiceType(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
        }else{
            return null;
    }

    }
}
