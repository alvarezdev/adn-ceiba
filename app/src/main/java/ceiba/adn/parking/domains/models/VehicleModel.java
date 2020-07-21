package ceiba.adn.parking.domains.models;

import java.util.Date;

import ceiba.adn.parking.domains.exceptions.BusinessException;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.enums.VehicleType;

public class VehicleModel {

    private static final String NULL_VEHICLE_PLATE_MSG = "Vehicle plate can't null or empty";
    private static final String ZERO_CYLINDER_CAPACITY_MSG = "The cylinder capacity of the vehicle must be greater than zero";


    private String plate;
    private VehicleType vehicleType;
    private double cylinderCapacity;
    private Date vehicleEntryTime;
    private Date vehicleDepartureTime;

    public VehicleModel(VehicleDto vehicleDto) {
        this.plate = vehicleDto.getPlate();
        this.vehicleType = vehicleDto.getVehicleType();
        this.cylinderCapacity = vehicleDto.getCylinderCapacity();
        this.vehicleEntryTime = vehicleDto.getVehicleEntryTime();
        this.vehicleDepartureTime = vehicleDto.getVehicleDepartureTime();
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getCylinderCapacity() {
        return cylinderCapacity;
    }

    public void setCylinderCapacity(double cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    public Date getVehicleEntryTime() {
        return vehicleEntryTime;
    }

    public void setVehicleEntryTime(Date vehicleEntryTime) {
        this.vehicleEntryTime = vehicleEntryTime;
    }

    public Date getVehicleDepartureTime() {
        return vehicleDepartureTime;
    }

    public void setVehicleDepartureTime(Date vehicleDepartureTime) {
        this.vehicleDepartureTime = vehicleDepartureTime;
    }

    public void ValidateData(VehicleModel vehicleModel) throws BusinessException {
        if (vehicleModel.getPlate() == null || vehicleModel.getPlate().isEmpty()){
            throw new BusinessException(NULL_VEHICLE_PLATE_MSG);
        }
        if(vehicleModel.getCylinderCapacity() <= 0){
            throw new BusinessException(ZERO_CYLINDER_CAPACITY_MSG);
        }
    }
}
