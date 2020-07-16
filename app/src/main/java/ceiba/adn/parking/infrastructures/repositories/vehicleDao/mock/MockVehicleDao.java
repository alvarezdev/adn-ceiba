package ceiba.adn.parking.infrastructures.repositories.vehicleDao.mock;

import java.util.ArrayList;
import java.util.List;

import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.IVehicleDao;

public class MockVehicleDao implements IVehicleDao {

    List<VehicleDto> vehicleList;

    public MockVehicleDao() {
        vehicleList = new ArrayList<>();
    }

    @Override
    public void addVehicle(VehicleDto vehicleDto) {
        vehicleList.add(vehicleDto);
    }

    @Override
    public List<VehicleDto> GetListVehicle() {
        return vehicleList;
    }

    @Override
    public void deleteVehicle(VehicleDto vehicleDto) {
        vehicleList.remove(vehicleDto);
    }

    @Override
    public void deleteAll() {
        vehicleList.clear();
    }
}
