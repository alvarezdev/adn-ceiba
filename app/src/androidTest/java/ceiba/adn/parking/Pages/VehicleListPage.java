package ceiba.adn.parking.Pages;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import ceiba.adn.parking.Pages.BasePage;
import ceiba.adn.parking.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public class VehicleListPage extends BasePage {

    public static int getVehicleListEditTextSearch() {
        return R.id.input_search;
    }

    public static int getAddVehicleEditTextHintSearch() {
        return R.string.hint_edit_text_search;
    }

    public static int getVehicleListItemComponentRecyclerView() {
        return R.id.leave_vehicle;
    }

    public static int getVehicleListRecyclerView() {
        return R.id.recycler_view_vehicle_list;
    }

    public static int getAddVehicleMessageListEmptyToast() {
        return R.string.list_is_empty;
    }

    public static void checkedRecyclerViewItemDisplay(Integer resourceId, Integer position, String text){
        onView(withId(resourceId))
                .check(matches(atPosition(position, hasDescendant(withText(text)))));
    }

    public static void checkedRecyclerViewItemNotDisplay(Integer resourceId, Integer position, String text){
        onView(withId(resourceId))
                .check(matches(not(atPosition(position, hasDescendant(withText(text))))));
    }

    public static void clickItemComponentOfRecyclerView(Integer resourceIdRecycler,Integer resourceIdComponent, Integer position){
        onView(withId(resourceIdRecycler)).perform(
                RecyclerViewActions.actionOnItemAtPosition(position, clickChildViewWithId(resourceIdComponent)));
    }

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
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

    public static ViewAction clickChildViewWithId(final int id) {
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
