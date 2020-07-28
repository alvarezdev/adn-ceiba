package ceiba.adn.parking.domains.features.parking;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import ceiba.adn.parking.domains.dependencyInjections.DaggerParkingDomainComponent;
import ceiba.adn.parking.domains.dependencyInjections.ParkingDomainComponent;
import ceiba.adn.parking.domains.dependencyInjections.ParkingDomainModule;
import ceiba.adn.parking.domains.exceptions.BusinessException;
import ceiba.adn.parking.domains.exceptions.DataBaseException;
import ceiba.adn.parking.domains.models.VehicleModel;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.enums.VehicleType;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.VehicleDaoContext;
import ceiba.adn.parking.contracts.VehicleDaoImpl;

public class ParkingDomain {

    private static final String FULL_PARKING_MSG = "The parking is full";
    private static final String REPEAT_VEHICLE_PLATE_MSG = "The vehicle plate is repeat";
    private static final String UNAUTHORIZED_ENTRY = "Unauthorized entry";
    private static final String MAX_CAPACITY_CAR_MSG = "Parking capacity for cars is full";
    private static final String MAX_CAPACITY_MOTORCYCLE_MSG = "Parking capacity for MotorCycles is full";
    private static final String VEHICLE_NOT_EXIST_MSG = "This vehicle is not in the parking";
    private static final int MAX_CAPACITY_CAR = 20;
    private static final int MAX_CAPACITY_MOTORCYCLE = 10;
    private static final int MAX_CAPACITY_PARKING = 30;
    private static final int CAR_HOUR_VALUE = 1000;
    private static final int MOTORCYCLE_HOUR_VALUE = 500;
    private static final int CAR_DAY_VALUE = 8000;
    private static final int MOTORCYCLE_DAY_VALUE = 4000;
    private static final int ADD_MOTORCYCLE_PAY = 2000;
    private static final double CYLINDER_CAPACITY_GREATER = 500;

    @Inject
    public VehicleDaoContext vehicleDaoContext;
    public ParkingDomainComponent parkingDomainComponent;
    private VehicleDaoImpl vehicleDaoImpl;

    @Inject
    public ParkingDomain() {
        parkingDomainComponent = DaggerParkingDomainComponent.builder().parkingDomainModule(new ParkingDomainModule(vehicleDaoImpl)).build();
        parkingDomainComponent.inject(this);
    }

    public void deleteAll() {
        vehicleDaoContext.deleteAll();
    }

    public void enterParking(VehicleDto vehicleDto) throws BusinessException {
        VehicleModel vehicleModel = new VehicleModel(vehicleDto);
        vehicleModel.ValidateData(vehicleModel);
        List<VehicleDto> vehicleDtoList = getListVehicle();
        if (vehicleDtoList.size() >= MAX_CAPACITY_PARKING){
            throw new BusinessException(FULL_PARKING_MSG);
        }else{
            boolean plateRepeat = checkPlateRepeat(vehicleDtoList, vehicleDto);
            boolean checkDate = checkDate(vehicleDto);
            boolean MaxCapacityVehicle = maxCapacityVehicle(vehicleDtoList, vehicleDto.getVehicleType());
            if (plateRepeat && checkDate && MaxCapacityVehicle){
                vehicleDaoContext.addVehicle(vehicleDto);
            }
        }
    }

    private boolean checkPlateRepeat(List<VehicleDto> vehicleDtoList, VehicleDto vehicleDto) throws BusinessException {
        boolean vehicleExist = false;
        for (VehicleDto vehicle: vehicleDtoList) {
            if(vehicleDto.getPlate().equals(vehicle.getPlate())){
                vehicleExist = true;
            }
        }
        if (vehicleExist){
            throw new BusinessException(REPEAT_VEHICLE_PLATE_MSG);
        }else{
            return true;
        }
    }

    private boolean checkDate(VehicleDto vehicleDto) throws BusinessException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(vehicleDto.getVehicleEntryTime());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if((dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.MONDAY) && vehicleDto.getPlate().substring(0,1).equals("A")){
            throw new BusinessException(UNAUTHORIZED_ENTRY);
        }else{
            return true;
        }
    }

    private boolean maxCapacityVehicle(List<VehicleDto> vehicleDtoList, VehicleType vehicleType) throws BusinessException {
        int capacityNumber = 0;
        for (int i = 0; i < vehicleDtoList.size(); i++) {
            VehicleDto vehicleDto = vehicleDtoList.get(i);
            if (vehicleDto.getVehicleType() == vehicleType){
                capacityNumber++;
            }
        }
        switch (vehicleType){
            case CAR:
                if(capacityNumber >= MAX_CAPACITY_CAR && vehicleType == VehicleType.CAR){
                    throw new BusinessException(MAX_CAPACITY_CAR_MSG);
                }
                break;
            case MOTORCYCLE:
                if(capacityNumber >= MAX_CAPACITY_MOTORCYCLE && vehicleType == VehicleType.MOTORCYCLE){
                    throw new BusinessException(MAX_CAPACITY_MOTORCYCLE_MSG);
                }
                break;
        }
        return true;
    }

    public List<VehicleDto> getListVehicle() {
        return vehicleDaoContext.getListVehicle();
    }

    public VehicleDto getVehicle(String plate) throws DataBaseException {
        VehicleDto vehicleDto = vehicleDaoContext.getVehicle(plate);
        if (vehicleDto == null){
            throw new DataBaseException(VEHICLE_NOT_EXIST_MSG);
        }
        return vehicleDto;
    }

    public void leaveVehicle(VehicleDto vehicleDto) {
        vehicleDaoContext.deleteVehicle(vehicleDto);
    }

    public int calculateValueParking(VehicleDto vehicleDto){
        long entryHour = TimeUnit.MILLISECONDS.toHours(vehicleDto.getVehicleEntryTime().getTime());
        long departureHour = TimeUnit.MILLISECONDS.toHours(vehicleDto.getVehicleDepartureTime().getTime());
        int time = (int) (departureHour - entryHour);
        if (time == 0){
            time++;
        }

        int hourPrice = 0;
        int dayPrice = 0;

        switch (vehicleDto.getVehicleType()){
            case CAR:
                hourPrice = CAR_HOUR_VALUE;
                dayPrice = CAR_DAY_VALUE;
                break;
            case MOTORCYCLE:
                hourPrice = MOTORCYCLE_HOUR_VALUE;
                dayPrice = MOTORCYCLE_DAY_VALUE;
        }

        int result =0;
        if (time >= 1 && time <= 9){
            result = time * hourPrice;
        }else if(time > 9 && time <= 24){
            result = dayPrice;
        }else if(time > 24){
            int hours = time - 24;
            if (hours < 0){
                hours = hours * -1;
            }
            result = dayPrice + (hours * hourPrice);
        }
        if(vehicleDto.getVehicleType() == VehicleType.MOTORCYCLE && vehicleDto.getCylinderCapacity() > CYLINDER_CAPACITY_GREATER){
            result = result + ADD_MOTORCYCLE_PAY;
        }
        return result;
    }

}
