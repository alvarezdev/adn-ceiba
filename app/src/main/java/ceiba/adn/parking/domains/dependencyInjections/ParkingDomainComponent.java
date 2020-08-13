package ceiba.adn.parking.domains.dependencyInjections;

import javax.inject.Singleton;
import ceiba.adn.parking.domains.features.parking.ParkingDomain;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.mock.VehicleDaoMock;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.real.VehicleDaoReal;
import dagger.Component;

//@Singleton
@Component
public interface ParkingDomainComponent {
    VehicleDaoMock VehicleDaoMock();
    VehicleDaoReal VehicleDaoReal();
    ParkingDomain parkingDomain();
}
