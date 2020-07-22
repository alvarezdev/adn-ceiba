package ceiba.adn.parking.presentations.AddVehicle;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import ceiba.adn.parking.domains.exceptions.BusinessException;
import ceiba.adn.parking.domains.features.parking.ParkingDomain;
import ceiba.adn.parking.dtos.VehicleDto;

public class AddVehicleViewModel extends ViewModel {

    @Inject
    public ParkingDomain parkingDomain;

    public AddVehicleViewModel() {
        parkingDomain = new ParkingDomain();
    }

    public void addVehicle(VehicleDto vehicleDto) throws BusinessException {
        parkingDomain.enterParking(vehicleDto);
    }
}
