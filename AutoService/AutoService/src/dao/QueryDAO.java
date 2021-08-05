package dao;

import dao.custom.SuperDAO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    public String getCID() throws Exception;
    public String getCode() throws Exception;
    public int getTotalVehicle() throws Exception;
    public int getTotalCustomer() throws Exception;
    public int getTotalService() throws Exception;
    public String getLastScardId() throws SQLException, ClassNotFoundException;


}
