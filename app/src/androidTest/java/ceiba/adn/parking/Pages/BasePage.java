package ceiba.adn.parking.Pages;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import ceiba.adn.parking.Util;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class BasePage {

    public static void clicked(Integer resourceId){
        onView(withId(resourceId))
                .perform(click());
    }

    public static void checked(Integer resourceId){
        onView(withId(resourceId))
                .check(matches(isChecked()));
    }

    public static void notChecked(Integer resourceId){
        onView(withId(resourceId))
                .check(matches(isNotChecked()));
    }

    public static void displayedInView(Integer resourceId){
        onView(withId(resourceId))
                .check(matches(isDisplayed()));
    }

    public static void checkEditTextHint(Integer resourceId,Integer stringId){
        onView(withId(resourceId))
                .check(matches(withHint(stringId)));
    }

    public static void checkEditTextHintAndTypeTextAndCloseKeyBoard(Integer resourceId,Integer stringId,String text){
       onView(withId(resourceId))
            .check(matches(withHint(stringId)))
                .perform(typeText(text), ViewActions.closeSoftKeyboard());
    }

    public static void toastView(Integer resourceId, View view){
        onView(withText(resourceId))
                .inRoot(withDecorView(not(is(view))))
                .check(matches(isDisplayed()));
    }
}
