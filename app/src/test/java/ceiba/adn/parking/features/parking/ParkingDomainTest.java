package ceiba.adn.parking.features.parking;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import ceiba.adn.parking.domains.services.ParkingDomainService;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.enums.VehicleType;


public class ParkingDomainTest {

    ParkingDomainService parkingDomainService;

    public ParkingDomainTest() {
        parkingDomainService = new ParkingDomainService();
        parkingDomainService.deleteAll();
    }

    @Test
    public void enterParkingTest() {
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("FIS100");
        vehicleDto.setVehicleType(VehicleType.CAR);
        vehicleDto.setCylinderCapacity(1500);
        vehicleDto.setVehicleEntryTime(new Date());

        //Act
        parkingDomainService.enterParking(vehicleDto);

        //Assert
        List<VehicleDto> listVehicle = parkingDomainService.getListVehicle();
        VehicleDto vehicle = listVehicle.stream().filter(v -> v.getPlate().equals("FIS100")).findFirst().get();
        Assert.assertEquals(vehicleDto.getPlate(),vehicle.getPlate());
        Assert.assertEquals(vehicleDto.getVehicleDepartureTime(),vehicle.getVehicleDepartureTime());
        Assert.assertEquals(vehicleDto.getCylinderCapacity(),vehicle.getCylinderCapacity());
        Assert.assertEquals(vehicleDto.getVehicleEntryTime(),vehicle.getVehicleEntryTime());
    }

    @Test
    public void leaveParkingTest(){
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("FIS100");
        vehicleDto.setVehicleType(VehicleType.CAR);
        vehicleDto.setCylinderCapacity(1500);
        vehicleDto.setVehicleEntryTime(new Date());

        //Act
        parkingDomainService.leaveParking(vehicleDto);

        //Assert
        List<VehicleDto> listVehicle = parkingDomainService.getListVehicle();
        VehicleDto vehicle = listVehicle.stream().filter(v -> v.getPlate().equals("FIS100")).findFirst().get();
        Assert.assertNull(vehicle);
    }
}