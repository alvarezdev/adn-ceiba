package ceiba.adn.parking.infrastructures.repositories.entities;

import ceiba.adn.parking.dtos.VehicleDto;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class VehicleEntity extends RealmObject {

    @PrimaryKey
    private String plate;
    private String vehicleType;
    private double cylinderCapacity;
    private long vehicleEntryTime;

    public VehicleEntity() {
    }

    public VehicleEntity(VehicleDto vehicleDto) {
        this.plate = vehicleDto.getPlate();
        this.vehicleType = vehicleDto.getVehicleType().toString();
        this.cylinderCapacity = vehicleDto.getCylinderCapacity();
        this.vehicleEntryTime = vehicleDto.getVehicleEntryTime().getTime();
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getCylinderCapacity() {
        return cylinderCapacity;
    }

    public void setCylinderCapacity(double cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    public long getVehicleEntryTime() {
        return vehicleEntryTime;
    }

    public void setVehicleEntryTime(long vehicleEntryTime) {
        this.vehicleEntryTime = vehicleEntryTime;
    }
}
