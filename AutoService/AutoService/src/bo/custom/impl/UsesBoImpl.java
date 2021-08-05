package bo.custom.impl;

import bo.custom.UsesBO;
import dao.DaoFactory;
import dao.custom.UsesDAO;
import dto.CustomerDTO;
import dto.UsesDTO;
import entity.Customer;
import entity.Uses;
import entity.Vehicle;


import java.util.ArrayList;
import java.util.List;

public class UsesBoImpl implements UsesBO {

    private UsesDAO dao = DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.USES);

    @Override
    public boolean saveUses(UsesDTO dto) throws Exception {
        System.out.println(dto);
        return dao.save(new Uses(dto.getUseCode(),
                dto.getCode(),dto.getDiscription(),dto.getUnitPrice(),dto.getQty(),dto.getTotal()));
    }

    @Override
    public ArrayList<UsesDTO> getAllUses() throws Exception {
        List<Uses> list = dao.getAll();
        ArrayList<UsesDTO> arrayList = new ArrayList<>();
        for (Uses uses: list){
            arrayList.add(new UsesDTO(
                    uses.getUseCode(),
                    uses.getCode(),
                    uses.getDiscription(),
                    uses.getUnitPrice(),
                    uses.getQty(),
                    uses.getTotal()));
            System.out.println(list);
        }
        return arrayList;
    }

    @Override
    public boolean deleteUses(String UseCode) throws Exception {
        return dao.delete(UseCode);
    }

    @Override
    public boolean UpdateUses(UsesDTO dto) throws Exception {
        return dao.update(new Uses(dto.getUseCode(),dto.getCode(),dto.getDiscription(),dto.getUnitPrice(),dto.getQty(),dto.getTotal()));
    }

    @Override
    public UsesDTO getUses(String UseCode) throws Exception {
        Uses uses=dao.getUses ( UseCode);
        return new UsesDTO ( uses.getUseCode (), uses.getCode (), uses.getDiscription (), uses.getUnitPrice (),uses.getQty (),uses.getTotal ());
    }
    //get Total
    public double getItemTotal() {
       /* double total;
        int qty = Integer.parseInt(txtQty.getText());
        double uPrice = Double.parseDouble(lblUPrice.getText());
        double dis = Double.parseDouble(txtDis.getText());
        total = (uPrice-(uPrice*dis/100.0)*qty);
        fullPayment+=total;
        lblTot.setText(fullPayment+"");
        return total;*/
        return 0;
    }
}
