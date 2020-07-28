package ceiba.adn.parking.infrastructures.repositories.vehicleDao.mock;

import java.util.ArrayList;
import java.util.List;

import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.contracts.VehicleDaoImpl;

public class VehicleDaoDaoMock implements VehicleDaoImpl {

    List<VehicleDto> vehicleList;

    public VehicleDaoDaoMock() {
        vehicleList = new ArrayList<>();
    }

    @Override
    public void addVehicle(VehicleDto vehicleDto) {
        vehicleList.add(vehicleDto);
    }

    @Override
    public VehicleDto getVehicle(String plate) {
        for (int i = 0; i < vehicleList.size(); i++) {
            VehicleDto vehicleDto = vehicleList.get(i);
            if (vehicleDto.getPlate().equals(plate)){
                return vehicleDto;
            }
        }
        return null;
    }

    @Override
    public List<VehicleDto> getListVehicle() {
        return vehicleList;
    }

    @Override
    public void deleteVehicle(VehicleDto vehicleDto) {
        if (getVehicle(vehicleDto.getPlate()) != null){
            vehicleList.remove(vehicleDto);
        }
    }

    @Override
    public void deleteAll() {
        if(vehicleList.size() > 0){
            vehicleList.clear();
        }
    }
}
