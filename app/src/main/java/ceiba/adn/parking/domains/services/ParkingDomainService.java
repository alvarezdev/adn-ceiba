package ceiba.adn.parking.domains.services;

import java.util.ArrayList;
import java.util.List;

import ceiba.adn.parking.domains.features.BaseDomain;
import ceiba.adn.parking.domains.features.parking.ParkingDomain;
import ceiba.adn.parking.domains.models.VehicleModel;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.IVehicleDao;

public class ParkingDomainService extends BaseDomain {

    IVehicleDao iVehicleDao;
    ParkingDomain parkingDomain;

    public ParkingDomainService() {
        this.parkingDomain = parkingDomain;
    }

    public void deleteAll() {
        parkingDomain.deleteAll();
    }

    public void enterParking(VehicleDto vehicleDto) {
        parkingDomain.enterParking(new VehicleModel(vehicleDto));
    }

    public List<VehicleDto> getListVehicle() {
        return parkingDomain.GetListVehicle();
    }

    public void leaveParking(VehicleDto vehicleDto) {
        parkingDomain.leaveVehicle(vehicleDto);
    }
}
