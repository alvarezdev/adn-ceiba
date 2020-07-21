package ceiba.adn.parking.domains.dependencyInjections;

import javax.inject.Singleton;

import ceiba.adn.parking.infrastructures.repositories.vehicleDao.VehicleDaoContext;
import dagger.Module;
import dagger.Provides;

@Module
public class ParkingDomainModule {

    @Provides
    @Singleton
    public VehicleDaoContext providesVehicleDaoContext(){
        return new VehicleDaoContext();
    }

}
