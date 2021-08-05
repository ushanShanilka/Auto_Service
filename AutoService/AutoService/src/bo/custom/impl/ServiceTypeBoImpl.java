package bo.custom.impl;

import bo.custom.ServiceTypeBO;
import dao.DaoFactory;
import dao.custom.ServiceTypeDAO;
import dao.custom.VehicleDAO;
import dto.CustomerDTO;
import dto.ServiceCardDTO;
import dto.ServiceTypeDTO;
import entity.Customer;
import entity.ServiceCard;
import entity.ServiceType;
import entity.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ServiceTypeBoImpl implements ServiceTypeBO {

    private ServiceTypeDAO dao = DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.SERVICETYPE);


    @Override
    public boolean saveServiceType(ServiceTypeDTO dto) throws Exception {
        return dao.save(new ServiceType(dto.getTypeId(),
                dto.getName(),dto.getDetails()));
    }

    @Override
    public ArrayList<ServiceTypeDTO> getAllServiceType() throws Exception {
        List<ServiceType> list = dao.getAll();
        ArrayList<ServiceTypeDTO> arrayList = new ArrayList<>();
        for (ServiceType serviceType : list) {
            arrayList.add(new ServiceTypeDTO(
                    serviceType.getTypeId(),
                    serviceType.getName(),
                    serviceType.getDetails()));
        }
        return arrayList;
    }

    @Override
    public boolean deleteServiceType(String TypeID) throws Exception {
        return dao.delete(TypeID);
    }

    @Override
    public boolean UpdateServiceType(ServiceTypeDTO dto) throws Exception {
        return dao.update(new ServiceType(dto.getTypeId(),dto.getName(),dto.getDetails()));
    }

    @Override
    public ServiceTypeDTO getServiceType(String TypeID) throws Exception {
        ServiceType serviceType=dao.get(TypeID);
        return new ServiceTypeDTO(serviceType.getTypeId(),serviceType.getName(),serviceType.getDetails());
    }
}
