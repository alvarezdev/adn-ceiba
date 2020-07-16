package ceiba.adn.parking.domains.features;

import ceiba.adn.parking.domains.features.parking.ParkingDomain;

public class BaseDomain {

    protected static ParkingDomain parkingDomain;

    protected BaseDomain() {
        //parkingDomain = DaggerMotorComponent.builder().motorModule(new MotorModule()).build();
    }

}
