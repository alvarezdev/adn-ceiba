package ceiba.adn.parking.features.parking;

import ceiba.adn.parking.domains.features.BaseDomain;
import ceiba.adn.parking.domains.features.parking.ParkingDomain;

public class BaseTest extends BaseDomain {

    protected ParkingDomain parkingDomain;

    protected ParkingDomainTextAux parkingDomainTextAux;

    public BaseTest() {
        parkingDomainTextAux = new ParkingDomainTextAux();
        parkingDomain = getParkingDomainComponent().parkingDomain();
    }
}
