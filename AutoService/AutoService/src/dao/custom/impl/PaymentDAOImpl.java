package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.PaymentDAO;
import entity.Customer;
import entity.Payment;
import entity.ServiceCard;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment payment) throws Exception {
        return CrudUtil.execute
                ("INSERT INTO Payment VALUES(?,?,?,?,?,?)",
                        payment.getPID(),payment.getScardID(),
                        payment.getCID(),payment.getVnum(),payment.getUseCode(),payment.getTotal());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(Payment payment) throws Exception {
        return false;
    }

    @Override
    public List<Payment> getAll() throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT * FROM Payment");
        ArrayList<Payment> list=new ArrayList<>();
        while (rst.next()){
            list.add(new Payment(rst.getString(1),
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
    public Payment get(String s) throws Exception {
        return null;
    }

//    public ServiceCard get(String ScardID) throws Exception {
//        ResultSet rst = CrudUtil.execute("SELECT * FROM servicecard WHERE ScardID=?",ScardID);
//        if(rst.next()){
//            return new ServiceCard(
//                    rst.getString(1),
//                    rst.getString(4),
//                    rst.getString(5));
//
//        }else{
//            return new ServiceCard("C001","S829","734821");
//        }
    }


