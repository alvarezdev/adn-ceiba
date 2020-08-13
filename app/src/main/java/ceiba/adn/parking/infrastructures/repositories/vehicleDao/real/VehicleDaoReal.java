package ceiba.adn.parking.infrastructures.repositories.vehicleDao.real;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.enums.VehicleType;
import ceiba.adn.parking.infrastructures.repositories.entities.VehicleEntity;
import ceiba.adn.parking.contracts.VehicleDao;
import io.realm.Realm;
import io.realm.RealmResults;

public class VehicleDaoReal implements VehicleDao {

    Realm realm;
    @Inject
    public VehicleDaoReal() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void addVehicle(VehicleDto vehicleDto) {
        VehicleEntity vehicleEntity = new VehicleEntity(vehicleDto);
        realm.executeTransaction(realm -> {
            if (getVehicle(vehicleEntity.getPlate()) == null) {
                VehicleEntity vehicleRealm = realm.createObject(VehicleEntity.class, vehicleEntity.getPlate());
                vehicleRealm.setVehicleType(vehicleEntity.getVehicleType());
                vehicleRealm.setCylinderCapacity(vehicleEntity.getCylinderCapacity());
                vehicleRealm.setVehicleEntryTime(vehicleEntity.getVehicleEntryTime());
            }
        });
    }

    @Override
    public VehicleDto getVehicle(String plate) {
        VehicleEntity vehicleEntity = realm.where(VehicleEntity.class).equalTo("plate", plate).findFirst();
        if(vehicleEntity != null){
            VehicleDto vehicleDto = new VehicleDto();
            vehicleDto.setPlate(vehicleEntity.getPlate());
            vehicleDto.setVehicleType(VehicleType.valueOf(vehicleEntity.getVehicleType()));
            vehicleDto.setCylinderCapacity(vehicleEntity.getCylinderCapacity());
            vehicleDto.setVehicleEntryTime(new Date(vehicleEntity.getVehicleEntryTime()));
            return vehicleDto;
        }
        return null;
    }

    @Override
    public List<VehicleDto> getListVehicle() {
        List<VehicleDto> vehicles = new ArrayList<>();
        RealmResults<VehicleEntity> vehicleRealmList = realm.where(VehicleEntity.class).findAll();
        for(VehicleEntity vehicleEntity: vehicleRealmList){
            if (vehicleEntity != null)
            {
                VehicleDto vehicleDto = new VehicleDto();
                vehicleDto.setPlate(vehicleEntity.getPlate());
                vehicleDto.setVehicleType(VehicleType.valueOf(vehicleEntity.getVehicleType()));
                vehicleDto.setCylinderCapacity(vehicleEntity.getCylinderCapacity());
                vehicleDto.setVehicleEntryTime(new Date(vehicleEntity.getVehicleEntryTime()));
                vehicles.add(vehicleDto);
            }
        }
        return vehicles;
    }

    @Override
    public void deleteVehicle(VehicleDto vehicleDto) {
        realm.executeTransaction(realm -> {
            VehicleDto vehicle = getVehicle(vehicleDto.getPlate());
            if (vehicle != null) {
                VehicleEntity result = realm.where(VehicleEntity.class).equalTo("plate", vehicle.getPlate()).findFirst();
                result.deleteFromRealm();
            }
        });

    }

    @Override
    public void deleteAll() {
        realm.executeTransaction(realm -> {
            RealmResults<VehicleEntity> result = realm.where(VehicleEntity.class).findAll();
            if (!result.isEmpty()) {
                result.deleteAllFromRealm();
            }
        });
    }
}
