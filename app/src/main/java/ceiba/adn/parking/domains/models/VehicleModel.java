package ceiba.adn.parking.domains.models;

import java.util.Date;

import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.enums.VehicleType;

public class VehicleModel {

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
}
