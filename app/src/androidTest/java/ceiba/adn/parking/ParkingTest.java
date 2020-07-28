package ceiba.adn.parking;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ceiba.adn.parking.Pages.AddVehiclePage;
import ceiba.adn.parking.Pages.ConfirmVehicleDeparturePage;
import ceiba.adn.parking.Pages.HomePage;
import ceiba.adn.parking.Pages.VehicleListPage;
import ceiba.adn.parking.presentations.home.HomeActivity;

@RunWith(AndroidJUnit4.class)
public class ParkingTest {

    @Rule
    public ActivityTestRule<HomeActivity> activityRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void enterCarParkingAndSuccessMessageTest(){
        //Given
        HomePage.clicked(HomePage.getHomeButtonAddVehicle());
        AddVehiclePage.checkEditTextHintAndTypeTextAndCloseKeyBoard(AddVehiclePage.getAddVehicleEditTextPlate(),
                AddVehiclePage.getAddVehicleEditTextHintPlate(),"FIS998");
        AddVehiclePage.checkEditTextHintAndTypeTextAndCloseKeyBoard(AddVehiclePage.getAddVehicleEditTextCylinderCapacity(),
                AddVehiclePage.getAddVehicleEditTextHintCylinderCapacity(),"1500");
        AddVehiclePage.clicked(AddVehiclePage.getAddVehicleRadioButtonCar());
        AddVehiclePage.checked(AddVehiclePage.getAddVehicleRadioButtonCar());
        AddVehiclePage.notChecked(AddVehiclePage.getAddVehicleRadioButtonMotorcycle());

        //When
        AddVehiclePage.clicked(AddVehiclePage.getAddVehicleButtonAccept());

        //Assert
        AddVehiclePage.toastView(AddVehiclePage.getAddVehicleMessageToast(),activityRule.getActivity().getWindow().getDecorView());
    }

    @Test
    public void enterMotorcycleParkingAndSuccessMessageTest(){
        //Given
        HomePage.clicked(HomePage.getHomeButtonAddVehicle());
        AddVehiclePage.checkEditTextHintAndTypeTextAndCloseKeyBoard(AddVehiclePage.getAddVehicleEditTextPlate(),
                AddVehiclePage.getAddVehicleEditTextHintPlate(),"DPA28Z");
        AddVehiclePage.checkEditTextHintAndTypeTextAndCloseKeyBoard(AddVehiclePage.getAddVehicleEditTextCylinderCapacity(),
                AddVehiclePage.getAddVehicleEditTextHintCylinderCapacity(),"200");
        AddVehiclePage.clicked(AddVehiclePage.getAddVehicleRadioButtonMotorcycle());
        AddVehiclePage.checked(AddVehiclePage.getAddVehicleRadioButtonMotorcycle());
        AddVehiclePage.notChecked(AddVehiclePage.getAddVehicleRadioButtonCar());

        //When
        AddVehiclePage.clicked(AddVehiclePage.getAddVehicleButtonAccept());

        //Assert
        AddVehiclePage.toastView(AddVehiclePage.getAddVehicleMessageToast(),activityRule.getActivity().getWindow().getDecorView());
    }

    @Test
    public void fillFailedFieldsWhenEnterVehicleParkingAndDisplayErrorMessageTest(){
        //Given
        HomePage.clicked(HomePage.getHomeButtonAddVehicle());

        AddVehiclePage.checkEditTextHint(AddVehiclePage.getAddVehicleEditTextPlate(), AddVehiclePage.getAddVehicleEditTextHintPlate());
        AddVehiclePage.checkEditTextHint(AddVehiclePage.getAddVehicleEditTextCylinderCapacity(), AddVehiclePage.getAddVehicleEditTextHintCylinderCapacity());

        AddVehiclePage.clicked(AddVehiclePage.getAddVehicleRadioButtonCar());
        AddVehiclePage.checked(AddVehiclePage.getAddVehicleRadioButtonCar());
        AddVehiclePage.notChecked(AddVehiclePage.getAddVehicleRadioButtonMotorcycle());

        //When
        AddVehiclePage.clicked(AddVehiclePage.getAddVehicleButtonAccept());

        //Assert
        AddVehiclePage.toastView(AddVehiclePage.getAddVehicleMessageWarringToast(),activityRule.getActivity().getWindow().getDecorView());
    }

    @Test
    public void searchPlateFailedResultsAndEmptyListMessageTest(){
        //Given
        HomePage.clicked(HomePage.getHomeButtonShowVehicleList());

        //When
        VehicleListPage.checkEditTextHintAndTypeTextAndCloseKeyBoard(VehicleListPage.getVehicleListEditTextSearch(),
                VehicleListPage.getAddVehicleEditTextHintSearch(),"DDD777");

        //Then
        VehicleListPage.toastView(VehicleListPage.getAddVehicleMessageListEmptyToast(),activityRule.getActivity().getWindow().getDecorView());
    }

    @Test
    public void leaveCarFromParkingAndRemoveToVehicleListSuccessfulTest(){
        //Given
        HomePage.clicked(HomePage.getHomeButtonAddVehicle());

        AddVehiclePage.checkEditTextHintAndTypeTextAndCloseKeyBoard(AddVehiclePage.getAddVehicleEditTextPlate(),
                AddVehiclePage.getAddVehicleEditTextHintPlate(),"AAA112");
        AddVehiclePage.checkEditTextHintAndTypeTextAndCloseKeyBoard(AddVehiclePage.getAddVehicleEditTextCylinderCapacity(),
                AddVehiclePage.getAddVehicleEditTextHintCylinderCapacity(),"1500");
        AddVehiclePage.clicked(AddVehiclePage.getAddVehicleRadioButtonCar());
        AddVehiclePage.checked(AddVehiclePage.getAddVehicleRadioButtonCar());
        AddVehiclePage.notChecked(AddVehiclePage.getAddVehicleRadioButtonMotorcycle());

        AddVehiclePage.clicked(AddVehiclePage.getAddVehicleButtonAccept());

        AddVehiclePage.toastView(AddVehiclePage.getAddVehicleMessageToast(),activityRule.getActivity().getWindow().getDecorView());

        HomePage.clicked(HomePage.getHomeButtonShowVehicleList());

        VehicleListPage.checkEditTextHintAndTypeTextAndCloseKeyBoard(VehicleListPage.getVehicleListEditTextSearch(),
                VehicleListPage.getAddVehicleEditTextHintSearch(),"AAA112");

        VehicleListPage.checkedRecyclerViewItemDisplay(VehicleListPage.getVehicleListRecyclerView(),0, "AAA112");

        VehicleListPage.clickItemComponentOfRecyclerView(VehicleListPage.getVehicleListRecyclerView(),
                VehicleListPage.getVehicleListItemComponentRecyclerView(),0);

        ConfirmVehicleDeparturePage.displayedInView(ConfirmVehicleDeparturePage.getDialogFragmentTittle());

        //When
        ConfirmVehicleDeparturePage.clicked(ConfirmVehicleDeparturePage.getDialogFragmentButtonAccept());

        //Then
        VehicleListPage.checkedRecyclerViewItemNotDisplay(VehicleListPage.getVehicleListRecyclerView(),0, "ZZZ999");
    }
}
