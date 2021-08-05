package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO Customer VALUES(?,?,?,?)",
                        customer.getCID(),customer.getName(),
                        customer.getAddress(),customer.getContact());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtil.execute("UPDATE Customer SET name=?, address=?, contact=? WHERE CID=?",customer.getName(),customer.getAddress(),customer.getContact(),customer.getCID());

    }

   @Override
    public List<Customer> getAll() throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> list=new ArrayList<>();
        while (rst.next()){
            list.add(new Customer(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }
        return list;
    }

    @Override
    public Customer get(String cid) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer WHERE CID=?",cid);
        if(rst.next()){
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
        }else{
            return null;
        }
    }

}
