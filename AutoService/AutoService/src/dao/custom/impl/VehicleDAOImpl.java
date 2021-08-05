package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.VehicleDAO;
import entity.Vehicle;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {

    @Override
    public boolean save(Vehicle vehicle) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO Vehicle VALUES(?,?,?,?)",vehicle.getVnum(),
                        vehicle.getModel(),vehicle.getMilage(),vehicle.getYEAR());
    }

    @Override
    public boolean delete(String Vnum) throws Exception {
        return CrudUtil.execute("DELETE FROM Vehicle WHERE Vnum=?",Vnum);
    }

    @Override
    public boolean update(Vehicle vehicle) throws Exception {
        return CrudUtil.execute("UPDATE Vehicle SET model=?, milage=?, year=? WHERE Vnum=?",vehicle.getModel(),vehicle.getMilage(),vehicle.getYEAR(),vehicle.getVnum());
    }

    @Override
    public List<Vehicle> getAll() throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT * FROM Vehicle");
        ArrayList<Vehicle>list=new ArrayList<>();
        while ((rst.next())
        ) {
            list.add(new Vehicle(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }
        return list;
    }

    @Override
    public Vehicle get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Vehicle WHERE Vnum=?",s);
        if(rst.next()){
            return new Vehicle(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
        }else{
            return null;
        }
    }
}
