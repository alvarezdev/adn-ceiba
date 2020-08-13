package ceiba.adn.parking.domains.features;

import ceiba.adn.parking.BuildConfig;
import ceiba.adn.parking.contracts.VehicleDao;
import ceiba.adn.parking.domains.dependencyInjections.DaggerParkingDomainComponent;
import ceiba.adn.parking.domains.dependencyInjections.ParkingDomainComponent;

public class BaseDomain {

    private ParkingDomainComponent parkingDomainComponent;

    public BaseDomain() {
        parkingDomainComponent = DaggerParkingDomainComponent.create();
    }

    public ParkingDomainComponent getParkingDomainComponent(){
        return parkingDomainComponent;
    }

    public VehicleDao VehicleDao(){
        if (BuildConfig.FLAVOR.equals("mocks")){
            return parkingDomainComponent.VehicleDaoMock();
        }else {
            return parkingDomainComponent.VehicleDaoReal();
        }
    }
}
