package ceiba.adn.parking.presentations.addVehicle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;

import ceiba.adn.parking.R;
import ceiba.adn.parking.domains.exceptions.BusinessException;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.enums.VehicleType;

public class AddVehicleActivity extends AppCompatActivity {

    private static final String TAG = "AddVehicleActivity";

    private EditText plate;
    private EditText cylinderCapacity;
    private RadioGroup vehicleGroup;
    private RadioButton vehicleOption;
    private Button accept;
    private Button cancel;

    private AddVehicleViewModel addVehicleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        initComponent();
    }

    private void initComponent() {
        addVehicleViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(AddVehicleViewModel.class);
        plate = findViewById(R.id.input_plate);
        cylinderCapacity = findViewById(R.id.input_cylinder_capacity);
        vehicleGroup = findViewById(R.id.radio_group_vehicle);
        accept = findViewById(R.id.btn_accept_add_vehicle);
        cancel = findViewById(R.id.btn_cancel_add_vehicle);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plateVehicle = plate.getText().toString();
                String cylinderCapacityVehicle= cylinderCapacity.getText().toString();
                if (!plateVehicle.isEmpty() || !cylinderCapacityVehicle.isEmpty()){
                    VehicleDto vehicleDto = new VehicleDto();
                    int selectedId = vehicleGroup.getCheckedRadioButtonId();
                    vehicleOption = findViewById(selectedId);
                    String getStringVehicleType = vehicleOption.getText().toString();
                    VehicleType vehicleType;
                    if(getStringVehicleType.equals("Carro")){
                        vehicleType = VehicleType.CAR;
                    }else{
                        vehicleType = VehicleType.MOTORCYCLE;
                    }
                    vehicleDto.setPlate(plateVehicle);
                    vehicleDto.setVehicleType(vehicleType);
                    vehicleDto.setCylinderCapacity(Double.parseDouble(cylinderCapacityVehicle));
                    vehicleDto.setVehicleEntryTime(new Date());
                    try {
                        addVehicleViewModel.addVehicle(vehicleDto);
                        Toast.makeText(getApplicationContext(),R.string.add_vehicle_message_successful,Toast.LENGTH_LONG).show();
                        finish();
                    }catch (BusinessException e){
                        Log.d(TAG, e.getMessage());
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),R.string.message_warring_add_vehicle,Toast.LENGTH_LONG).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}