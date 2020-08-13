package ceiba.adn.parking.presentations.addVehicle;

import androidx.lifecycle.ViewModel;

import ceiba.adn.parking.domains.exceptions.BusinessException;
import ceiba.adn.parking.domains.features.parking.ParkingDomain;
import ceiba.adn.parking.dtos.VehicleDto;

public class AddVehicleViewModel extends ViewModel {

    public ParkingDomain parkingDomain;

    public AddVehicleViewModel() {
    }

    public void setParkingDomain(ParkingDomain parkingDomain){
        this.parkingDomain = parkingDomain;
    }

    public void addVehicle(VehicleDto vehicleDto) throws BusinessException {
        parkingDomain.enterParking(vehicleDto);
    }
}
