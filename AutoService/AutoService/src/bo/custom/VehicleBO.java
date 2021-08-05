package bo.custom;


import dto.CustomerDTO;
import dto.VehicleDTO;

import java.util.ArrayList;

public interface VehicleBO {
    public boolean saveVehicle(VehicleDTO dto)throws Exception;
    public ArrayList<VehicleDTO> getAllVehicle()throws Exception;
    public boolean deleteVehicle(String Vnum)throws Exception;
    public boolean UpdateVehicle(VehicleDTO dto) throws Exception;
    public VehicleDTO getVehicle(String vnum) throws Exception;
}
