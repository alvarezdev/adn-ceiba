package ceiba.adn.parking.presentations.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ceiba.adn.parking.R;
import ceiba.adn.parking.presentations.addVehicle.AddVehicleActivity;
import ceiba.adn.parking.presentations.vehicleList.VehicleListActivity;

public class HomeActivity extends AppCompatActivity {

    Button addVehicle;
    Button showVehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initComponent();
    }

    private void initComponent() {
        addVehicle = findViewById(R.id.button_add_vehicle);
        addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddVehicleActivity.class);
                startActivity(intent);
            }
        });
        showVehicleList = findViewById(R.id.button_get_vehicles);
        showVehicleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VehicleListActivity.class);
                startActivity(intent);
            }
        });
    }
}