package ceiba.adn.parking;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ceiba.adn.parking.presentations.Home.HomeActivity;

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
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class ParkingTest {

    @Rule
    public ActivityTestRule<HomeActivity> activityRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void enterCarParkingSuccessfulTest(){
        //Given
        onView(withId(R.id.button_add_vehicle)).perform(click());
        onView(withId(R.id.input_plate)).check(matches(withHint(R.string.hint_edit_text_plate)))
                .perform(typeText("FIS300"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.input_cylinder_capacity)).check(matches(withHint(R.string.hint_edit_text_cylinder)))
                .perform(typeText("1600"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.radio_button_car)).perform(click());
        onView(withId(R.id.radio_button_car)).check(matches(isChecked()));
        onView(withId(R.id.radio_button_motorcycle)).check(matches(isNotChecked()));

        //When
        onView(withId(R.id.btn_accept_add_vehicle)).perform(click());

        //Assert
        onView(withText(R.string.add_vehicle_message_successful))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void enterMotorcycleParkingSuccessfulTest(){
        //Given
        onView(withId(R.id.button_add_vehicle)).perform(click());
        onView(withId(R.id.input_plate)).check(matches(withHint(R.string.hint_edit_text_plate)))
                .perform(typeText("DPB90T"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.input_cylinder_capacity)).check(matches(withHint(R.string.hint_edit_text_cylinder)))
                .perform(typeText("150"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.radio_button_motorcycle)).perform(click());
        onView(withId(R.id.radio_button_car)).check(matches(isNotChecked()));
        onView(withId(R.id.radio_button_motorcycle)).check(matches(isChecked()));

        //When
        onView(withId(R.id.btn_accept_add_vehicle)).perform(click());

        //Assert
        onView(withText(R.string.add_vehicle_message_successful))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void enterVehicleRepeatPlateParkingTest(){
        //Given
        onView(withId(R.id.button_add_vehicle)).perform(click());
        onView(withId(R.id.input_plate)).check(matches(withHint(R.string.hint_edit_text_plate)))
                .perform(typeText("FIS100"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.input_cylinder_capacity)).check(matches(withHint(R.string.hint_edit_text_cylinder)))
                .perform(typeText("1600"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.radio_button_car)).perform(click());
        onView(withId(R.id.radio_button_car)).check(matches(isChecked()));
        onView(withId(R.id.radio_button_motorcycle)).check(matches(isNotChecked()));

        //When
        onView(withId(R.id.btn_accept_add_vehicle)).perform(click());

        //Assert
        onView(withText("The vehicle plate is repeat"))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void fieldsAreNotFilledCorrectlyTest(){
        //Given
        onView(withId(R.id.button_add_vehicle)).perform(click());
        onView(withId(R.id.input_plate)).check(matches(withHint(R.string.hint_edit_text_plate)));
        onView(withId(R.id.input_cylinder_capacity)).check(matches(withHint(R.string.hint_edit_text_cylinder)));
        onView(withId(R.id.radio_button_car)).perform(click());
        onView(withId(R.id.radio_button_car)).check(matches(isChecked()));
        onView(withId(R.id.radio_button_motorcycle)).check(matches(isNotChecked()));

        //When
        onView(withId(R.id.btn_accept_add_vehicle)).perform(click());

        //Assert
        onView(withText(R.string.message_warring_add_vehicle))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
    @Test
    public void noSearchVehicleResultsTest(){
        //Given
        onView(withId(R.id.button_get_vehicles)).perform(click());

        //When
        onView(withId(R.id.input_search)).check(matches(withHint(R.string.hint_edit_text_search)))
                .perform(typeText("UDD789"), ViewActions.closeSoftKeyboard());

        //Then
        onView(withText(R.string.list_is_empty))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
    @Test
    public void leaveVehicleFromParkingSuccessfulTest(){
        //Given
        onView(withId(R.id.button_get_vehicles)).perform(click());
        onView(withId(R.id.input_search)).check(matches(withHint(R.string.hint_edit_text_search)))
                .perform(typeText("UPI940"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.recycler_view_vehicle_list))
                .check(matches(atPosition(0, hasDescendant(withText("UPI940")))));
        onView(withId(R.id.recycler_view_vehicle_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.leave_vehicle)));
        onView(withId(R.id.title_advice)).check(matches(isDisplayed()));

        //When
        onView(withId(R.id.accept_dialog)).perform(click());

        //Then
        onView(withId(R.id.recycler_view_vehicle_list))
                .check(matches(not(atPosition(0, hasDescendant(withText("UPI940"))))));
    }

    private static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        //checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    private static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
}
