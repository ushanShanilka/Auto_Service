package bo;

import bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }
    public static BoFactory getInstance(){
        return (boFactory==null) ? (boFactory=new BoFactory()) : (boFactory);
    }

    public enum BOType{
        CUSTOMER,ITEM,VEHICLE,DEFAULT,SERVICECARD,SERVICETYPE,USES,PAYMENT;

    }

    public <T> T getBo(BOType type){
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            case ITEM:
                return (T)new ItemBoImpl();
            case VEHICLE:
                return (T)new VehicleBoImpl();
            case DEFAULT:
                return (T)new HomeBoImpl();
            case SERVICECARD:
                return (T)new ServiceCardBoImpl();
            case SERVICETYPE:
                return (T)new ServiceTypeBoImpl();
            case USES:
                return (T)new UsesBoImpl();
            case PAYMENT:
                return (T)new PaymentBoImpl();


            default:
                return null;
        }

    }
}
