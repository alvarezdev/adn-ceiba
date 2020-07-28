package ceiba.adn.parking.domains.dependencyInjections;

import javax.inject.Singleton;

import ceiba.adn.parking.BuildConfig;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.VehicleDaoContext;
import ceiba.adn.parking.contracts.VehicleDaoImpl;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.mock.VehicleDaoDaoMock;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.real.VehicleDaoDaoReal;
import dagger.Module;
import dagger.Provides;

@Module
public class ParkingDomainModule {

    VehicleDaoImpl vehicleDao;

    public ParkingDomainModule(VehicleDaoImpl vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @Provides
    @Singleton
    public VehicleDaoContext providesVehicleDaoContext(){
        if (BuildConfig.FLAVOR.equals("mocks")){
            vehicleDao = new VehicleDaoDaoMock();
        }else {
            vehicleDao = new VehicleDaoDaoReal();
        }
        VehicleDaoContext vehicleDaoContext = new VehicleDaoContext();
        vehicleDaoContext.setVehicleDaoContext(vehicleDao);
        return vehicleDaoContext;
    }

}
