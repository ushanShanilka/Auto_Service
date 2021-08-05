package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBO {
    private CustomerDAO dao = DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.CUSTOMER);
    private QueryDAO qDao = DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        return dao.save(new Customer(dto.getCID(), dto.getName(),
                dto.getAddress(), dto.getContact()));
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        List<Customer> list = dao.getAll();
        ArrayList<CustomerDTO> arrayList = new ArrayList<>();
        for (Customer customer : list) {
            arrayList.add(new CustomerDTO(
                    customer.getCID(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getContact()));
        }
        return arrayList;
    }

    @Override
    public boolean deleteCustomer(String CID) throws Exception {
        return dao.delete(CID);
    }

    @Override
    public boolean UpdateCustomer(CustomerDTO dto) throws Exception {
        return dao.update(new Customer(dto.getCID(),dto.getName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public String getCID() throws Exception {
        return null;
    }

    @Override
    public CustomerDTO getCustomer(String cid) throws Exception {
        Customer customer=dao.get(cid);
        return new CustomerDTO(customer.getCID(),customer.getName(),customer.getAddress(),customer.getContact());
    }

    public String getId() throws Exception {
        return qDao.getCID();
    }
}
