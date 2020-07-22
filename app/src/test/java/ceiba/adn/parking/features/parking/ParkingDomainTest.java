package ceiba.adn.parking.features.parking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import ceiba.adn.parking.domains.exceptions.BusinessException;
import ceiba.adn.parking.domains.exceptions.DataBaseException;
import ceiba.adn.parking.domains.features.parking.ParkingDomain;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.enums.VehicleType;

public class ParkingDomainTest {

    @Inject
    public ParkingDomain parkingDomain;

    public ParkingDomainTextAux parkingDomainTextAux;

    @Before
    public void setUp() {
        parkingDomainTextAux = new ParkingDomainTextAux();
        parkingDomain = new ParkingDomain();
        parkingDomain.deleteAll();
    }

    @Test
    public void enterVehicleInParkingSucessfulTest() throws BusinessException, DataBaseException {
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
        vehicleDto.setCylinderCapacity(0);
        vehicleDto.setVehicleEntryTime(new Date());

        try {
            //Act
            parkingDomain.enterParking(vehicleDto);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(e instanceof  BusinessException);
            //Assert;
            Assert.assertEquals("The cylinder capacity of the vehicle must be greater than zero", e.getMessage());
        }
    }

    @Test
    public void enterVehicleInParkingWithZeroCylinderCapacityTest() {
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate(null);
        vehicleDto.setVehicleType(VehicleType.CAR);
        vehicleDto.setCylinderCapacity(1500);
        vehicleDto.setVehicleEntryTime(new Date());

        try {
            //Act
            parkingDomain.enterParking(vehicleDto);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(e instanceof  BusinessException);
            //Assert;
            Assert.assertEquals("Vehicle plate can't null or empty", e.getMessage());
        }
    }

    @Test
    public void enterVehicleInParkingPlateRepeatTest() throws BusinessException {
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("FIS100");
        vehicleDto.setVehicleType(VehicleType.CAR);
        vehicleDto.setCylinderCapacity(1500);
        vehicleDto.setVehicleEntryTime(new Date());
        parkingDomain.enterParking(vehicleDto);

        try {
            //Act
            VehicleDto vehicle = new VehicleDto();
            vehicle.setPlate("FIS100");
            vehicle.setVehicleType(VehicleType.CAR);
            vehicle.setCylinderCapacity(1600);
            vehicle.setVehicleEntryTime(new Date());
            parkingDomain.enterParking(vehicleDto);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(e instanceof  BusinessException);
            //Assert;
            Assert.assertEquals("The vehicle plate is repeat", e.getMessage());
        }
    }

    @Test
    public void enterVehicleInParkingSundayAndPlateBeginsATest() {
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("AIS100");
        vehicleDto.setVehicleType(VehicleType.CAR);
        vehicleDto.setCylinderCapacity(1500);
        vehicleDto.setVehicleEntryTime(new GregorianCalendar(2020, 6, 19).getTime());

        try {
            //Act
            parkingDomain.enterParking(vehicleDto);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(e instanceof  BusinessException);
            //Assert;
            Assert.assertEquals("Unauthorized entry", e.getMessage());
        }
    }

    @Test
    public void parkingCapacityCarsFullTest() throws BusinessException {
        //Arrenge
        for (VehicleDto vehicle : parkingDomainTextAux.createListCar()) {
            parkingDomain.enterParking(vehicle);
        }

        try {
            //Act
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setPlate("ABC500");
            vehicleDto.setVehicleType(VehicleType.CAR);
            vehicleDto.setCylinderCapacity(1500);
            vehicleDto.setVehicleEntryTime(new Date());
            parkingDomain.enterParking(vehicleDto);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(e instanceof  BusinessException);
            //Assert;
            Assert.assertEquals("Parking capacity for cars is full", e.getMessage());
        }
    }

    @Test
    public void parkingCapacityMotorCycleFullTest() throws BusinessException {
        //Arrenge
        for (VehicleDto vehicle : parkingDomainTextAux.createListMotorCycle()) {
            parkingDomain.enterParking(vehicle);
        }

        try {
            //Act
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setPlate("ABC900");
            vehicleDto.setVehicleType(VehicleType.MOTORCYCLE);
            vehicleDto.setCylinderCapacity(300);
            vehicleDto.setVehicleEntryTime(new Date());
            parkingDomain.enterParking(vehicleDto);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(e instanceof  BusinessException);
            //Assert;
            Assert.assertEquals("Parking capacity for MotorCycles is full", e.getMessage());
        }
    }

    @Test
    public void fullParkingTest() throws BusinessException, DataBaseException {
        //Arrenge
        List<VehicleDto> list = parkingDomainTextAux.createListVehicle();
        for (VehicleDto vehicle : parkingDomainTextAux.createListVehicle()) {
            parkingDomain.enterParking(vehicle);
        }

        try {
            //Act
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setPlate("ABC100");
            vehicleDto.setVehicleType(VehicleType.CAR);
            vehicleDto.setCylinderCapacity(1500);
            vehicleDto.setVehicleEntryTime(new Date());
            parkingDomain.enterParking(vehicleDto);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(e instanceof  BusinessException);
            //Assert;
            Assert.assertEquals("The parking is full", e.getMessage());
        }
    }

    @Test
    public void getVehicleFromParkingSucessfulTest() throws BusinessException, DataBaseException {
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
    public void getVehicleNotExitTest() {
        //Arrenge

        try {
            //Act
            parkingDomain.getVehicle("FIS100");
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(e instanceof  DataBaseException);
            //Assert;
            Assert.assertEquals("This vehicle is not in the parking", e.getMessage());
        }
    }

    @Test
    public void leaveCarFromParkingAndCalculatePriceSucessfulTest() throws BusinessException, DataBaseException {

        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("FIS100");
        vehicleDto.setVehicleType(VehicleType.CAR);
        vehicleDto.setCylinderCapacity(1500);
        vehicleDto.setVehicleEntryTime(new GregorianCalendar(2020, 6, 19,0,5).getTime());
        parkingDomain.enterParking(vehicleDto);

        //Act
        VehicleDto vehicle = parkingDomain.getVehicle(vehicleDto.getPlate());
        vehicle.setVehicleDepartureTime(new GregorianCalendar(2020, 6, 19,2,25).getTime());
        int valuePay = parkingDomain.leaveVehicle(vehicleDto);

        //Assert;
        Assert.assertEquals(valuePay,2000);
    }

    @Test
    public void leaveMotorCycleFromParkingAndCalculatePriceSucessfulTest() throws BusinessException, DataBaseException {
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("BBC100");
        vehicleDto.setVehicleType(VehicleType.MOTORCYCLE);
        vehicleDto.setCylinderCapacity(200);
        vehicleDto.setVehicleEntryTime(new GregorianCalendar(2020, 6, 19,0,5).getTime());
        parkingDomain.enterParking(vehicleDto);

        //Act
        VehicleDto vehicle = parkingDomain.getVehicle(vehicleDto.getPlate());
        vehicle.setVehicleDepartureTime(new GregorianCalendar(2020, 6, 19,2,25).getTime());
        int valuePay = parkingDomain.leaveVehicle(vehicleDto);

        //Assert;
        Assert.assertEquals(valuePay,1000);
    }

    @Test
    public void leaveMotorCycleGreatear500FromParkingAndCalculatePriceSucessfulTest() throws BusinessException, DataBaseException {
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("BBC100");
        vehicleDto.setVehicleType(VehicleType.MOTORCYCLE);
        vehicleDto.setCylinderCapacity(650);
        vehicleDto.setVehicleEntryTime(new GregorianCalendar(2020, 6, 19,0,5).getTime());
        parkingDomain.enterParking(vehicleDto);

        //Act
        VehicleDto vehicle = parkingDomain.getVehicle(vehicleDto.getPlate());
        vehicle.setVehicleDepartureTime(new GregorianCalendar(2020, 6, 19,2,25).getTime());
        int valuePay = parkingDomain.leaveVehicle(vehicleDto);

        //Assert;
        Assert.assertEquals(valuePay,3000);
    }
}