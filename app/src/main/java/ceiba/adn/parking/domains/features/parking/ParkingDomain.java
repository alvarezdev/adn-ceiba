package ceiba.adn.parking.domains.features.parking;

import java.util.List;

import javax.inject.Inject;

import ceiba.adn.parking.BuildConfig;
import ceiba.adn.parking.domains.dependencyInjections.DaggerParkingDomainComponent;
import ceiba.adn.parking.domains.dependencyInjections.ParkingDomainComponent;
import ceiba.adn.parking.domains.dependencyInjections.ParkingDomainModule;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.VehicleDaoContext;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.VehicleImpl;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.mock.VehicleDaoMock;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.real.VehicleDaoReal;

public class ParkingDomain {

    @Inject
    public VehicleDaoContext vehicleDaoContext;
    public ParkingDomainComponent parkingDomainComponent;
    private VehicleImpl vehicle;

    @Inject
    public ParkingDomain() {

        if (BuildConfig.FLAVOR.equals("mocks")){
            vehicle = new VehicleDaoMock();
        }else {
            vehicle = new VehicleDaoReal();
        }

        parkingDomainComponent = DaggerParkingDomainComponent.builder().parkingDomainModule(new ParkingDomainModule()).build();
        parkingDomainComponent.inject(this);

        vehicleDaoContext = new VehicleDaoContext();
        vehicleDaoContext.setVehicleDaoContext(vehicle);
    }

    public void deleteAll() {
        vehicleDaoContext.deleteAll();
    }

    public void enterParking(VehicleDto vehicleDto) {
        vehicleDaoContext.addVehicle(vehicleDto);
    }

    public List<VehicleDto> getListVehicle() {
        return vehicleDaoContext.getListVehicle();
    }

    public void leaveVehicle(VehicleDto vehicleDto) {
        vehicleDaoContext.deleteVehicle(vehicleDto);
    }

    public VehicleDto getVehicle(String plate) {
        return vehicleDaoContext.getVehicle(plate);
    }
}
