package bo.custom.impl;

import bo.custom.ServiceCardBO;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.CustomerDAO;
import dao.custom.ServiceCardDAO;
import dto.CustomerDTO;
import dto.ServiceCardDTO;
import entity.Customer;
import entity.ServiceCard;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

public class ServiceCardBoImpl implements ServiceCardBO {

    private ServiceCardDAO dao = DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.SERVICECARD);

    private ServiceCardDAO serviceCardDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.SERVICECARD);;


    @Override
      public String getNewServiceCardId() throws Exception {
//        String lastServiceCardId = serviceCardDAO.getLastServiceCardId();
//
//        if (lastServiceCardId == null) {
//            return "SCID001";
//        } else {
//            int maxSCId = Integer.parseInt(lastServiceCardId.replace("SCI", ""));
//            maxSCId = maxSCId + 1;
//            String Scid = "";
//            if (maxSCId < 10) {
//                Scid = "SCI00" + maxSCId;
//            } else if (maxSCId < 100) {
//                Scid = "SI0" + maxSCId;
//            } else {
//                Scid = "SCI" + maxSCId;
//            }
//            return Scid;
//        }
        return null;
    }


    public ArrayList<ServiceCardDTO> getAllServiceCard() throws Exception {
        List<ServiceCard> list = dao.getAll();
        ArrayList<ServiceCardDTO> arrayList = new ArrayList<>();
        for (ServiceCard serviceCard : list) {
            arrayList.add(new ServiceCardDTO(
                    serviceCard.getScardID(),
                    serviceCard.getDate (),
                    serviceCard.getCID(),
                    serviceCard.getVnum(),
                    serviceCard.getUsesCode(),
                    serviceCard.getTypeID()));
        }
        return arrayList;
    }

    @Override
    public boolean saveServiceCard(ServiceCardDTO dto) throws Exception {
        return dao.save(new ServiceCard(dto.getScardID(),dto.getDate (), dto.getCID(),dto.getVnum(),dto.getUsesCode(),dto.getTypeID()));

    }

    @Override
    public boolean deleteServiceCrad(String ScardID) throws Exception {
        return dao.delete(ScardID);
    }

    @Override
    public ServiceCardDTO getServiceCard(String ServiceCard) throws Exception {
        return null;
    }

}
