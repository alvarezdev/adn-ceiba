package ceiba.adn.parking.presentations;

import android.app.Application;

import ceiba.adn.parking.BuildConfig;
import ceiba.adn.parking.domains.dependencyInjections.DaggerParkingDomainComponent;
import ceiba.adn.parking.domains.dependencyInjections.ParkingDomainComponent;
import ceiba.adn.parking.domains.features.parking.ParkingDomain;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {

    private ParkingDomainComponent parkingDomainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        parkingDomainComponent = DaggerParkingDomainComponent.create();
        if (!BuildConfig.FLAVOR.equals("mocks")){
            Realm.init(this);
            RealmConfiguration config = new RealmConfiguration.Builder().name("parkingDB.realm").build();
            Realm.setDefaultConfiguration(config);
        }
    }

    public ParkingDomain getParkingDomain(){
        return parkingDomainComponent.parkingDomain();
    }
}
