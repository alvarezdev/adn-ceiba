package ceiba.adn.parking.dtos;

import java.util.Date;

import ceiba.adn.parking.enums.VehicleType;

public class VehicleDto {

    public String plate;
    public VehicleType vehicleType;
    public double cylinderCapacity;
    public Date vehicleEntryTime;
    public Date vehicleDepartureTime;

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
