package bo.custom;

import dto.CustomerDTO;
import dto.ItemDTO;
import dto.VehicleDTO;

import java.util.ArrayList;

public interface ItemBO {
    public boolean saveItem(ItemDTO dto)throws Exception;
    public ArrayList<ItemDTO> getAllItem()throws Exception;
    public boolean deleteItem(String code)throws Exception;
    public ItemDTO getItem(String code) throws Exception;
    public String getCode() throws Exception;

}
