package ceiba.adn.parking.infrastructures.repositories.vehicleDao;

import java.util.List;

import javax.inject.Inject;

import ceiba.adn.parking.contracts.VehicleDaoImpl;
import ceiba.adn.parking.dtos.VehicleDto;

public class VehicleDaoContext {

    private VehicleDaoImpl vehicle;

    @Inject
    public VehicleDaoContext() {
    }

    public void setVehicleDaoContext(VehicleDaoImpl vehicle) {
        this.vehicle = vehicle;
    }

    public void addVehicle(VehicleDto vehicleDto) {
        vehicle.addVehicle(vehicleDto);
    }

    public VehicleDto getVehicle(String plate) {
        return vehicle.getVehicle(plate);
    }

    public List<VehicleDto> getListVehicle() {
        return vehicle.getListVehicle();
    }

    public void deleteVehicle(VehicleDto vehicleDto) {
        vehicle.deleteVehicle(vehicleDto);
    }

    public void deleteAll() {
        vehicle.deleteAll();
    }
}
