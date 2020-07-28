package ceiba.adn.parking.presentations;

import android.app.Application;

import ceiba.adn.parking.BuildConfig;
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
