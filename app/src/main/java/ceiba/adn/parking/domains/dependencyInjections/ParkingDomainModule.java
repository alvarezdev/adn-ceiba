package ceiba.adn.parking.domains.dependencyInjections;

import javax.inject.Singleton;

import ceiba.adn.parking.BuildConfig;
import ceiba.adn.parking.contracts.VehicleDao;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.mock.VehicleDaoMock;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.real.VehicleDaoReal;
import dagger.Module;
import dagger.Provides;

@Module
public class ParkingDomainModule {

    @Provides
    @Singleton
    public VehicleDao providesVehicleDao(){
        if (BuildConfig.FLAVOR.equals("mocks")){
            return new VehicleDaoMock();
        }else {
            return new VehicleDaoReal();
        }
    }
}
