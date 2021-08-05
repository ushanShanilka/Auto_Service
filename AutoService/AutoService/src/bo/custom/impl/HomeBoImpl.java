package bo.custom.impl;

import bo.custom.HomeBO;
import dao.DaoFactory;
import dao.QueryDAO;

public class HomeBoImpl implements HomeBO {
    private QueryDAO qDao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);
    @Override
    public int getTotalVehicle() throws Exception {

        return qDao.getTotalVehicle();
    }

    @Override
    public int getTotCustomer() throws Exception {
        return qDao.getTotalCustomer();
    }

    @Override
    public int getTotService() throws Exception {

        return qDao.getTotalService();
    }
}
