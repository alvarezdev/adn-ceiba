package ceiba.adn.parking;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ceiba.adn.parking.presentations.Home.HomeActivity;

import static androidx.test.espresso.action.ViewActions.click;
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
    public void enterCarParkingAndSuccessMessageTest(){
        //Given
        Home.clicked(Home.getHomeButtonAddVehicle());
        AddVehicle.checkEditTextHintAndTypeTextAndCloseKeyBoard(AddVehicle.getAddVehicleEditTextPlate(),
                AddVehicle.getAddVehicleEditTextHintPlate(),"FIS998");
        AddVehicle.checkEditTextHintAndTypeTextAndCloseKeyBoard(AddVehicle.getAddVehicleEditTextCylinderCapacity(),
                AddVehicle.getAddVehicleEditTextHintCylinderCapacity(),"1500");
        AddVehicle.clicked(AddVehicle.getAddVehicleRadioButtonCar());
        AddVehicle.checked(AddVehicle.getAddVehicleRadioButtonCar());
        AddVehicle.notChecked(AddVehicle.getAddVehicleRadioButtonMotorcycle());

        //When
        AddVehicle.clicked(AddVehicle.getAddVehicleButtonAccept());

        //Assert
        AddVehicle.toastView(AddVehicle.getAddVehicleMessageToast(),activityRule.getActivity().getWindow().getDecorView());
    }

    @Test
    public void enterMotorcycleParkingAndSuccessMessageTest(){
        //Given
        Home.clicked(Home.getHomeButtonAddVehicle());
        AddVehicle.checkEditTextHintAndTypeTextAndCloseKeyBoard(AddVehicle.getAddVehicleEditTextPlate(),
                AddVehicle.getAddVehicleEditTextHintPlate(),"DPA28Z");
        AddVehicle.checkEditTextHintAndTypeTextAndCloseKeyBoard(AddVehicle.getAddVehicleEditTextCylinderCapacity(),
                AddVehicle.getAddVehicleEditTextHintCylinderCapacity(),"200");
        AddVehicle.clicked(AddVehicle.getAddVehicleRadioButtonMotorcycle());
        AddVehicle.checked(AddVehicle.getAddVehicleRadioButtonMotorcycle());
        AddVehicle.notChecked(AddVehicle.getAddVehicleRadioButtonCar());

        //When
        AddVehicle.clicked(AddVehicle.getAddVehicleButtonAccept());

        //Assert
        AddVehicle.toastView(AddVehicle.getAddVehicleMessageToast(),activityRule.getActivity().getWindow().getDecorView());
    }

    @Test
    public void fillFailedFieldsWhenEnterVehicleParkingAndDisplayErrorMessageTest(){
        //Given
        Home.clicked(Home.getHomeButtonAddVehicle());

        AddVehicle.checkEditTextHint(AddVehicle.getAddVehicleEditTextPlate(),AddVehicle.getAddVehicleEditTextHintPlate());
        AddVehicle.checkEditTextHint(AddVehicle.getAddVehicleEditTextCylinderCapacity(),AddVehicle.getAddVehicleEditTextHintCylinderCapacity());

        AddVehicle.clicked(AddVehicle.getAddVehicleRadioButtonCar());
        AddVehicle.checked(AddVehicle.getAddVehicleRadioButtonCar());
        AddVehicle.notChecked(AddVehicle.getAddVehicleRadioButtonMotorcycle());

        //When
        AddVehicle.clicked(AddVehicle.getAddVehicleButtonAccept());

        //Assert
        AddVehicle.toastView(AddVehicle.getAddVehicleMessageWarringToast(),activityRule.getActivity().getWindow().getDecorView());
    }

    @Test
    public void searchPlateFailedResultsAndEmptyListMessageTest(){
        //Given
        Home.clicked(Home.getHomeButtonShowVehicleList());

        //When
        VehicleList.checkEditTextHintAndTypeTextAndCloseKeyBoard(VehicleList.getVehicleListEditTextSearch(),
                VehicleList.getAddVehicleEditTextHintSearch(),"DDD777");

        //Then
        VehicleList.toastView(VehicleList.getAddVehicleMessageListEmptyToast(),activityRule.getActivity().getWindow().getDecorView());
    }

    @Test
    public void leaveCarFromParkingAndRemoveToVehicleListSuccessfulTest(){
        //Given
        Home.clicked(Home.getHomeButtonAddVehicle());

        AddVehicle.checkEditTextHintAndTypeTextAndCloseKeyBoard(AddVehicle.getAddVehicleEditTextPlate(),
                AddVehicle.getAddVehicleEditTextHintPlate(),"AAA112");
        AddVehicle.checkEditTextHintAndTypeTextAndCloseKeyBoard(AddVehicle.getAddVehicleEditTextCylinderCapacity(),
                AddVehicle.getAddVehicleEditTextHintCylinderCapacity(),"1500");
        AddVehicle.clicked(AddVehicle.getAddVehicleRadioButtonCar());
        AddVehicle.checked(AddVehicle.getAddVehicleRadioButtonCar());
        AddVehicle.notChecked(AddVehicle.getAddVehicleRadioButtonMotorcycle());

        AddVehicle.clicked(AddVehicle.getAddVehicleButtonAccept());

        AddVehicle.toastView(AddVehicle.getAddVehicleMessageToast(),activityRule.getActivity().getWindow().getDecorView());

        Home.clicked(Home.getHomeButtonShowVehicleList());

        VehicleList.checkEditTextHintAndTypeTextAndCloseKeyBoard(VehicleList.getVehicleListEditTextSearch(),
                VehicleList.getAddVehicleEditTextHintSearch(),"AAA112");

        VehicleList.checkedRecyclerViewItemDisplay(VehicleList.getVehicleListRecyclerView(),0, "AAA112");

        VehicleList.clickItemComponentOfRecyclerView(VehicleList.getVehicleListRecyclerView(),
                VehicleList.getVehicleListItemComponentRecyclerView(),0);

        ConfirmVehicleDeparture.displayedInView(ConfirmVehicleDeparture.getDialogFragmentTittle());

        //When
        ConfirmVehicleDeparture.clicked(ConfirmVehicleDeparture.getDialogFragmentButtonAccept());

        //Then
        VehicleList.checkedRecyclerViewItemNotDisplay(VehicleList.getVehicleListRecyclerView(),0, "ZZZ999");
    }
}
