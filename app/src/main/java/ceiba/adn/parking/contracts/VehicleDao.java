package ceiba.adn.parking.contracts;

import java.util.List;

import ceiba.adn.parking.dtos.VehicleDto;

public interface VehicleDao {
    void addVehicle(VehicleDto vehicleDto);
    VehicleDto getVehicle(String plate);
    List<VehicleDto> getListVehicle();
    void deleteVehicle(VehicleDto vehicleDto);
    void deleteAll();
}
