package bo.custom;

import dto.PaymentDTO;
import dto.UsesDTO;
import entity.Payment;

import java.util.ArrayList;

public interface PaymentBO {
    public boolean savePayment(PaymentDTO dto)throws Exception;
    public ArrayList<PaymentDTO> getAllpayment()throws Exception;
    public boolean deletePayment(String UsesCode)throws Exception;
    public boolean UpdatePayments(PaymentDTO dto) throws Exception;
    public PaymentDTO getPayment(String id) throws Exception;
    //public PaymentDTO getSCard(String id) throws Exception;
}

