package ceiba.adn.parking.presentations;

import android.app.Application;

import ceiba.adn.parking.BuildConfig;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.mock.VehicleDaoMock;
import ceiba.adn.parking.infrastructures.repositories.vehicleDao.real.VehicleDaoReal;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (!BuildConfig.FLAVOR.equals("mocks")){
            Realm.init(this);
            RealmConfiguration config = new RealmConfiguration.Builder().name("parkingDB.realm").build();
            Realm.setDefaultConfiguration(config);
        }
    }
}
