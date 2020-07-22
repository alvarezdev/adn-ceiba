package ceiba.adn.parking.presentations.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import ceiba.adn.parking.R;
import ceiba.adn.parking.domains.exceptions.DataBaseException;
import ceiba.adn.parking.domains.features.parking.ParkingDomain;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.presentations.AddVehicle.AddVehicleActivity;
import ceiba.adn.parking.presentations.VehicleList.VehicleListActivity;

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