package ceiba.adn.parking.domains.dependencyInjections;

import javax.inject.Singleton;

import ceiba.adn.parking.domains.features.parking.ParkingDomain;
import dagger.Component;

@Singleton
@Component(modules={ParkingDomainModule.class})
public interface ParkingDomainComponent {
    void inject(ParkingDomain parkingDomain);
}
