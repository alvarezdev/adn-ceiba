package ceiba.adn.parking.presentations.vehicleList;

import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ceiba.adn.parking.domains.features.parking.ParkingDomain;
import ceiba.adn.parking.dtos.VehicleDto;

public class VehicleListViewModel extends ViewModel {

    @Inject
    public ParkingDomain parkingDomain;

    public VehicleListViewModel() {
        parkingDomain = new ParkingDomain();
    }

    public List<VehicleDto> getUserList() {
        return parkingDomain.getListVehicle();
    }

    public void deleteVehicle(VehicleDto vehicleDto){
        parkingDomain.leaveVehicle(vehicleDto);
    }

    public int calculateValueParking(VehicleDto vehicleDto){
        return parkingDomain.calculateValueParking(vehicleDto);
    }
}
