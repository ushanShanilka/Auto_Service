package bo.custom.impl;

import bo.custom.VehicleBO;
import dao.DaoFactory;
import dao.QueryDAO;
import dao.custom.CustomerDAO;
import dao.custom.VehicleDAO;
import dto.CustomerDTO;
import dto.VehicleDTO;
import entity.Customer;
import entity.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleBoImpl implements VehicleBO {

    private VehicleDAO dao = DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.VEHICLE);



    @Override
    public boolean saveVehicle(VehicleDTO dto) throws Exception {
        return dao.save(new Vehicle(dto.getVnum(),
                dto.getModel(),dto.getMilage(),dto.getYEAR()));
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicle() throws Exception {
        List<Vehicle> list = dao.getAll();
        ArrayList<VehicleDTO> arrayList = new ArrayList<>();
        for (Vehicle vehicle: list){
            arrayList.add(new VehicleDTO(
                    vehicle.getVnum(),
                    vehicle.getModel(),
                    vehicle.getMilage(),
                    vehicle.getYEAR()));
        }
        return arrayList;
    }

    @Override
    public boolean deleteVehicle(String Vnum) throws Exception {
        return dao.delete(Vnum);
    }

    @Override
    public boolean UpdateVehicle(VehicleDTO dto) throws Exception {
        return dao.update(new Vehicle(dto.getVnum(),dto.getModel(),dto.getMilage(),dto.getYEAR()));
    }

    @Override
    public VehicleDTO getVehicle(String vnum) throws Exception {
        Vehicle vehicle=dao.get(vnum);
        return new VehicleDTO(vehicle.getVnum(),vehicle.getModel(),vehicle.getMilage(),vehicle.getYEAR());

    }
}
