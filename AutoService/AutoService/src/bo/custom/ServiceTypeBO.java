package bo.custom;

import dto.ServiceTypeDTO;
import dto.VehicleDTO;

import java.util.ArrayList;

public interface ServiceTypeBO {
    public boolean saveServiceType(ServiceTypeDTO dto)throws Exception;
    public ArrayList<ServiceTypeDTO> getAllServiceType()throws Exception;
    public boolean deleteServiceType(String TypeID)throws Exception;
    public boolean UpdateServiceType(ServiceTypeDTO dto) throws Exception;
    public ServiceTypeDTO getServiceType(String TypeID) throws Exception;
}
