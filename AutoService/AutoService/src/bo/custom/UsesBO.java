package bo.custom;

import dto.UsesDTO;
import dto.VehicleDTO;

import java.util.ArrayList;

public interface UsesBO {
    public boolean saveUses(UsesDTO dto)throws Exception;
    public ArrayList<UsesDTO> getAllUses()throws Exception;
    public boolean deleteUses(String UsesCode)throws Exception;
    public boolean UpdateUses(UsesDTO dto) throws Exception;
    public UsesDTO getUses(String UsesCode) throws Exception;
    public double getItemTotal();
}
