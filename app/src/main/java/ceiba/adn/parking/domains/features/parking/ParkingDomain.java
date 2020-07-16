package ceiba.adn.parking.domains.features.parking;

import java.util.ArrayList;
import java.util.List;

import ceiba.adn.parking.domains.models.VehicleModel;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.IVehicleDao;

public class ParkingDomain {

    private IVehicleDao iVehicleDao;

    public ParkingDomain(IVehicleDao iVehicleDao) {
        this.iVehicleDao = iVehicleDao;
    }

    public void deleteAll() {
        iVehicleDao.deleteAll();

    }

    public void enterParking(VehicleModel vehicleModel) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate(vehicleModel.getPlate());
        vehicleDto.setVehicleType(vehicleModel.getVehicleType());
        vehicleDto.setCylinderCapacity(vehicleModel.getCylinderCapacity());
        vehicleDto.setVehicleEntryTime(vehicleModel.getVehicleEntryTime());
        iVehicleDao.addVehicle(vehicleDto);
    }

    public List<VehicleDto> GetListVehicle() {
        return iVehicleDao.GetListVehicle();
    }

    public void leaveVehicle(VehicleDto vehicleDto) {
        iVehicleDao.deleteVehicle(vehicleDto);
    }
}
