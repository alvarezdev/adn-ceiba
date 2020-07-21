package ceiba.adn.parking.features.parking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import ceiba.adn.parking.domains.features.parking.ParkingDomain;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.enums.VehicleType;

public class ParkingDomainTest {

    @Inject
    public ParkingDomain parkingDomain;

    @Before
    public void setUp() {
        parkingDomain = new ParkingDomain();
        parkingDomain.deleteAll();
    }

    @Test
    public void enterVehicleInParkingSucessfulTest() {
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("FIS100");
        vehicleDto.setVehicleType(VehicleType.CAR);
        vehicleDto.setCylinderCapacity(1500);
        vehicleDto.setVehicleEntryTime(new Date());

        //Act
        parkingDomain.enterParking(vehicleDto);

        //Assert
        List<VehicleDto> list = parkingDomain.getListVehicle();
        VehicleDto vehicle = list.stream().filter(v -> v.getPlate().equals("FIS100")).findFirst().get();
        Assert.assertEquals(vehicleDto.getPlate(),vehicle.getPlate());
    }

    @Test
    public void enterVehicleInParkingWithPlateNullTest() {
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("FIS100");
        vehicleDto.setVehicleType(VehicleType.CAR);
        vehicleDto.setCylinderCapacity(1500);
        vehicleDto.setVehicleEntryTime(new Date());
        parkingDomain.enterParking(vehicleDto);

        //Act
        VehicleDto vehicle = parkingDomain.getVehicle(vehicleDto.getPlate());

        //Assert;
        Assert.assertEquals(vehicleDto.getPlate(),vehicle.getPlate());
    }

    @Test
    public void getVehicleFromParkingSucessfulTest() {
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("FIS100");
        vehicleDto.setVehicleType(VehicleType.CAR);
        vehicleDto.setCylinderCapacity(1500);
        vehicleDto.setVehicleEntryTime(new Date());
        parkingDomain.enterParking(vehicleDto);

        //Act
        VehicleDto vehicle = parkingDomain.getVehicle(vehicleDto.getPlate());

        //Assert;
        Assert.assertEquals(vehicleDto.getPlate(),vehicle.getPlate());
    }

    @Test
    public void leaveVehicleParkingSucessfulTest(){
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("FIS100");
        vehicleDto.setVehicleType(VehicleType.CAR);
        vehicleDto.setCylinderCapacity(1500);
        vehicleDto.setVehicleEntryTime(new Date());
        parkingDomain.enterParking(vehicleDto);

        //Act
        parkingDomain.leaveVehicle(vehicleDto);

        //Assert
        List<VehicleDto> list = parkingDomain.getListVehicle();
        Assert.assertTrue(list.isEmpty());
    }
}