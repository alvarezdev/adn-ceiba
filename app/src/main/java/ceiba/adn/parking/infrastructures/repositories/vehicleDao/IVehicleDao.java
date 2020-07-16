package ceiba.adn.parking.infrastructures.repositories.vehicleDao;

import java.util.List;

import ceiba.adn.parking.dtos.VehicleDto;

public interface IVehicleDao {
    void addVehicle(VehicleDto vehicleDto);
    List<VehicleDto> GetListVehicle();
    void deleteVehicle(VehicleDto vehicleDto);
    void deleteAll();
}
