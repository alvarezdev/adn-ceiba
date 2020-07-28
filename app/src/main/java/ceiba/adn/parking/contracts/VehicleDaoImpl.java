package ceiba.adn.parking.contracts;

import java.util.List;

import ceiba.adn.parking.dtos.VehicleDto;

public interface VehicleDaoImpl {
    void addVehicle(VehicleDto vehicleDto);
    VehicleDto getVehicle(String plate);
    List<VehicleDto> getListVehicle();
    void deleteVehicle(VehicleDto vehicleDto);
    void deleteAll();
}
