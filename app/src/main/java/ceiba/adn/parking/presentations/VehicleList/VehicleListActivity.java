package ceiba.adn.parking.presentations.VehicleList;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

import ceiba.adn.parking.R;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.presentations.VehicleList.RecyclerElement.VehicleAdapter;

public class VehicleListActivity extends AppCompatActivity {

    private RecyclerView vehicleRecyclerView;
    private VehicleAdapter vehicleAdapter;

    private VehicleListViewModel vehicleListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);
        initComponent();
    }

    private void initComponent() {
        vehicleListViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(VehicleListViewModel.class);
        vehicleRecyclerView = findViewById(R.id.recycler_view_vehicle_list);
        vehicleRecyclerView.setHasFixedSize(true);
        vehicleRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        vehicleAdapter = new VehicleAdapter(vehicleListViewModel.getUserList(), this);
        vehicleRecyclerView.setAdapter(vehicleAdapter);
    }

    public int calculateValueParking(VehicleDto vehicleDto){
        vehicleDto.setVehicleDepartureTime(new Date());
        return vehicleListViewModel.calculateValueParking(vehicleDto);
    }

    public void deleteVehicleFromDialogFragment(VehicleDto vehicleDto, int position){
        vehicleListViewModel.deleteVehicle(vehicleDto);
        vehicleAdapter.vehicleList.remove(position);
        vehicleAdapter.notifyDataSetChanged();
    }
}