package bo.custom;

import dto.CustomerDTO;


import java.util.ArrayList;

public interface CustomerBO {
    public boolean saveCustomer(CustomerDTO dto)throws Exception;
    public ArrayList<CustomerDTO> getAllCustomers()throws Exception;
    public boolean deleteCustomer(String id)throws Exception;
    public boolean UpdateCustomer(CustomerDTO dto) throws Exception;
    public String getCID() throws Exception;
    public CustomerDTO getCustomer(String cid) throws Exception;

}
