package ceiba.adn.parking.infrastructures.repositories.vehicleDao;

import java.util.List;

import ceiba.adn.parking.dtos.VehicleDto;

public interface VehicleImpl {
    void addVehicle(VehicleDto vehicleDto);
    VehicleDto getVehicle(String plate);
    List<VehicleDto> getListVehicle();
    void deleteVehicle(VehicleDto vehicleDto);
    void deleteAll();
}
