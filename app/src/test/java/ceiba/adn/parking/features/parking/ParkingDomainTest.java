package ceiba.adn.parking.features.parking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import ceiba.adn.parking.domains.exceptions.BusinessException;
import ceiba.adn.parking.domains.exceptions.DataBaseException;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.enums.VehicleType;

public class ParkingDomainTest extends BaseTest {

    @Before
    public void setUp() {
        parkingDomain.deleteAll();
    }

    @Test
    public void enterParking_EnterCarWhenParkingHasSpace_Successful_Test() throws BusinessException {
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
    public void enterParking_EnterCarWithEmptyPlate_BusinessException_Test() {
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
    public void enterParking_EnterCarWithZeroCylinderCapacity_BusinessException_Test() {
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
    public void enterParking_EnterCarWithPlateRepeat_BusinessException_Test() throws BusinessException {
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
    public void enterParking_EnterMotorcycleWithEmptyPlate_BusinessException_Test() {
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate(null);
        vehicleDto.setVehicleType(VehicleType.MOTORCYCLE);
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
    public void enterParking_EnterMotorcycleWithZeroCylinderCapacity_BusinessException_Test() {
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("FIS100");
        vehicleDto.setVehicleType(VehicleType.MOTORCYCLE);
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
    public void enterParking_EnterMotorcycleWithPlateRepeat_BusinessException_Test() throws BusinessException {
        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("FIS100");
        vehicleDto.setVehicleType(VehicleType.MOTORCYCLE);
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
    public void enterParking_EnterCarWhenIsSundayAndPlateBeginsA_BusinessException_Test() {
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
    public void enterParking_EnterCarWhenParkingNotHaveCapacityForCars_BusinessException_Test() throws BusinessException {
        //Arrenge
        for (VehicleDto vehicle : parkingDomainTextAux.createListCar()) {
            parkingDomain.enterParking(vehicle);
        }

        try {
            //Act
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setPlate("ZBC500");
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
    public void enterParking_enterMotorcycleWhenParkingNotHaveCapacityForMotorcycles_BusinessException_Test() throws BusinessException {
        //Arrenge
        for (VehicleDto vehicle : parkingDomainTextAux.createListMotorCycle()) {
            parkingDomain.enterParking(vehicle);
        }

        try {
            //Act
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setPlate("ZBC900");
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
    public void enterParking_enterVehicleWhenParkingNotHaveCapacityForVehicles_BusinessException_Test() throws BusinessException {
        //Arrenge
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
    public void getVehicle_getCarFromParking_Successful_Test() throws BusinessException, DataBaseException {
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
    public void getVehicle_getCarFromParkingNonexistent_DataBaseException_Test() {
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
    public void calculateValueParking_calculateForHoursPriceCars_Successful_Test() throws BusinessException, DataBaseException {

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
        int valuePay = parkingDomain.calculateValueParking(vehicleDto);

        //Assert;
        Assert.assertEquals(valuePay,2000);
    }

    @Test
    public void calculateValueParking_calculateForDayPriceCars_Successful_Test() throws BusinessException, DataBaseException {

        //Arrenge
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlate("FIS100");
        vehicleDto.setVehicleType(VehicleType.CAR);
        vehicleDto.setCylinderCapacity(1500);
        vehicleDto.setVehicleEntryTime(new GregorianCalendar(2020, 6, 19,0,5).getTime());
        parkingDomain.enterParking(vehicleDto);

        //Act
        VehicleDto vehicle = parkingDomain.getVehicle(vehicleDto.getPlate());
        vehicle.setVehicleDepartureTime(new GregorianCalendar(2020, 6, 20,2,25).getTime());
        int valuePay = parkingDomain.calculateValueParking(vehicleDto);

        //Assert;
        Assert.assertEquals(valuePay,10000);
    }

    @Test
    public void calculateValueParking_calculateForHoursPriceMotorcycle_Successful_Test() throws BusinessException, DataBaseException {
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
        int valuePay = parkingDomain.calculateValueParking(vehicleDto);

        //Assert;
        Assert.assertEquals(valuePay,1000);
    }

    @Test
    public void calculateValueParking_calculateForHoursPriceMotorcycleGreatear500_Successful_Test() throws BusinessException, DataBaseException {
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
        int valuePay = parkingDomain.calculateValueParking(vehicleDto);
        parkingDomain.leaveVehicle(vehicleDto);

        //Assert;
        Assert.assertEquals(valuePay,3000);
    }

    @Test
    public void leaveVehicle_leaveCarParkingAndRemoveToVehicleList_Successful_Test() throws BusinessException, DataBaseException {
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
        parkingDomain.leaveVehicle(vehicleDto);

        try {
            //Act
            parkingDomain.getVehicle(vehicleDto.getPlate());
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(e instanceof  DataBaseException);
            //Assert;
            Assert.assertEquals("This vehicle is not in the parking", e.getMessage());
        }
    }
}