package dao;

import dao.custom.impl.*;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return (daoFactory == null) ?
                (daoFactory = new DaoFactory()) : (daoFactory);
    }

    public enum DAOType {
        CUSTOMER,QUERY,ITEM,VEHICLE,SERVICECARD,SERVICETYPE,USES,PAYMENT
        
    }


    public <T> T getDao(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case QUERY:
                return (T) new QueryDaoImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case VEHICLE:
                return (T) new VehicleDAOImpl();
            case SERVICECARD:
                return (T) new ServiceCardDAOImpl();
            case SERVICETYPE:
                return (T) new ServiceTypeDAOImpl();
            case USES:
                return (T) new UsesDAOImpl();
            case PAYMENT:
                return (T)new PaymentDAOImpl();


            default:
                return null;
        }
    }

}

