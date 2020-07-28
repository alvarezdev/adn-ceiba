package ceiba.adn.parking;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class Util {
    static boolean logEnabled = false;
    public static void logTest(String log){
        if (logEnabled) {
            Log.v("Ëspresso Test: ", log);
        }
    }


}
