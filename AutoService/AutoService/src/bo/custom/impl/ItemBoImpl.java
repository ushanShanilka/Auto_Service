package bo.custom.impl;


import bo.custom.ItemBO;
import dao.DaoFactory;
import dao.QueryDAO;

import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;


import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBO {

        private ItemDAO dao = DaoFactory.getInstance()
                .getDao(DaoFactory.DAOType.ITEM);
        private QueryDAO qDao = DaoFactory.getInstance()
                .getDao(DaoFactory.DAOType.QUERY);

    @Override
    public boolean saveItem(ItemDTO dto) throws Exception {
        return dao.save(new Item(dto.getCode(),
                dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws Exception {
        List<Item> list = dao.getAll();
        ArrayList<ItemDTO> arrayList = new ArrayList<>();
        for (Item item : list) {
            arrayList.add(new ItemDTO(
                    item.getCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQtyOnHand()));
        }
        return arrayList;
    }



    @Override
    public boolean deleteItem(String code) throws Exception {
        return dao.delete(code);
    }

    @Override
    public ItemDTO getItem(String code) throws Exception {
        Item item=dao.get(code);
        System.out.println(item);
        return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Override
    public String getCode() throws Exception {
        return qDao.getCode();
    }



}
