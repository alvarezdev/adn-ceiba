package ceiba.adn.parking.features.parking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ceiba.adn.parking.domains.features.parking.ParkingDomain;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.enums.VehicleType;

public class ParkingDomainTextAux {

    public ParkingDomainTextAux() {
    }

    public List<VehicleDto> createListVehicle() {
        List<VehicleDto> vehicleDtoList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String plate;
            if (i >= 0 && i <= 9){
                plate = "FIS10" + i;
            }else{
                plate = "FIS1" + i;
            }
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setPlate(plate);
            if (i <= 19){
                vehicleDto.setVehicleType(VehicleType.CAR);
                vehicleDto.setCylinderCapacity(1500);
            }else{
                vehicleDto.setVehicleType(VehicleType.MOTORCYCLE);
                vehicleDto.setCylinderCapacity(100);
            }
            vehicleDto.setVehicleEntryTime(new Date());
            vehicleDtoList.add(vehicleDto);
        }
        return vehicleDtoList;
    }

    public List<VehicleDto> createListCar() {
        List<VehicleDto> vehicleDtoList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String plate;
            if (i >= 0 && i <= 9){
                plate = "FIS10" + i;
            }else{
                plate = "FIS1" + i;
            }
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setPlate(plate);
            vehicleDto.setVehicleType(VehicleType.CAR);
            vehicleDto.setCylinderCapacity(1500);
            vehicleDto.setVehicleEntryTime(new Date());
            vehicleDtoList.add(vehicleDto);
        }
        return vehicleDtoList;
    }

    public List<VehicleDto> createListMotorCycle() {
        List<VehicleDto> vehicleDtoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setPlate("ZBC10" + i);
            vehicleDto.setVehicleType(VehicleType.MOTORCYCLE);
            vehicleDto.setCylinderCapacity(100);
            vehicleDto.setVehicleEntryTime(new Date());
            vehicleDtoList.add(vehicleDto);
        }
        return vehicleDtoList;
    }

}
