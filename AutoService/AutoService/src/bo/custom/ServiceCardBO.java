package bo.custom;

import dto.CustomerDTO;
import dto.ServiceCardDTO;
import dto.UsesDTO;

import java.util.ArrayList;

public interface ServiceCardBO {
    public String getNewServiceCardId() throws Exception;
    public ArrayList<ServiceCardDTO> getAllServiceCard()throws Exception;
    public boolean saveServiceCard(ServiceCardDTO dto)throws Exception;
    public boolean deleteServiceCrad(String ScardID)throws Exception;
    public ServiceCardDTO getServiceCard(String ServiceCard) throws Exception;



}
