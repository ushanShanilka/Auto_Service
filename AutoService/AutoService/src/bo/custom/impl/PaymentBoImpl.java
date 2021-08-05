package bo.custom.impl;

import bo.custom.PaymentBO;
import dao.DaoFactory;
import dao.custom.PaymentDAO;
import dao.custom.ServiceCardDAO;
import dao.custom.ServiceTypeDAO;
import dto.CustomerDTO;
import dto.PaymentDTO;
import dto.ServiceCardDTO;
import dto.UsesDTO;
import entity.Customer;
import entity.Payment;
import entity.ServiceCard;
import entity.ServiceType;

import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBO {
    private PaymentDAO dao = DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.PAYMENT);
    private ServiceCardDAO dao1 = DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.SERVICECARD);

    @Override
    public boolean savePayment(PaymentDTO dto) throws Exception {
        return dao.save(new Payment(dto.getPID(),
                dto.getScardID(), dto.getCID(), dto.getVnum(), dto.getUseCode(), dto.getTotal()));
    }

    @Override
    public ArrayList<PaymentDTO> getAllpayment() throws Exception {
        List<Payment> list = dao.getAll();
        ArrayList<PaymentDTO> arrayList = new ArrayList<>();
        for (Payment payment : list) {
            arrayList.add(new PaymentDTO (
                    payment.getPID (),
                    payment.getScardID (),
                    payment.getCID (),
                    payment.getVnum (),
                    payment.getUseCode (),
                    payment.getTotal ())
            );
        }
        return arrayList;
    }

    @Override
    public boolean deletePayment(String UsesCode) throws Exception {
        return false;
    }

    @Override
    public boolean UpdatePayments(PaymentDTO dto) throws Exception {
        return false;
    }

    @Override
    public PaymentDTO getPayment(String id) throws Exception {
        Payment payment = dao.get(id);
        return new PaymentDTO(payment.getPID(), payment.getScardID(), payment.getCID(), payment.getVnum(), payment.getUseCode(), payment.getTotal());
    }

    public ServiceCardDTO getSCard(String sid) throws Exception {
//        ServiceCard serviceCard= dao1.get(sid);
//        return new ServiceCardDTO(serviceCard.getScardID(), serviceCard.getCID(), serviceCard.getVnum());

        return null;
    }
}