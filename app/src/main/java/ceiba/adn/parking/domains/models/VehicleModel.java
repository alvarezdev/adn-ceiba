package ceiba.adn.parking.domains.models;

import java.util.Date;

import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.enums.VehicleType;

public class VehicleModel {

    public String plate;
    public VehicleType vehicleType;
    public double cylinderCapacity;
    public Date vehicleEntryTime;
    public Date vehicleDepartureTime;

    public VehicleModel(VehicleDto vehicleDto) {
        this.plate = vehicleDto.getPlate();
        this.vehicleType = vehicleDto.vehicleType;
        this.cylinderCapacity = vehicleDto.cylinderCapacity;
        this.vehicleEntryTime = vehicleDto.vehicleEntryTime;
        this.vehicleDepartureTime = vehicleDto.vehicleDepartureTime;
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
