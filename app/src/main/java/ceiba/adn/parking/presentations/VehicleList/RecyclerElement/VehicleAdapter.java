package ceiba.adn.parking.presentations.VehicleList.RecyclerElement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import ceiba.adn.parking.R;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.presentations.DialogFragment.DialogFragmentConfirmVehicleDeparture;
import ceiba.adn.parking.presentations.VehicleList.VehicleListActivity;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleItem> {

    public List<VehicleDto> vehicleList;
    VehicleListActivity vehicleListActivity;

    public VehicleAdapter(List<VehicleDto> vehicleList, VehicleListActivity vehicleListActivity) {
        this.vehicleList = vehicleList;
        this.vehicleListActivity = vehicleListActivity;
    }

    @NonNull
    @Override
    public VehicleItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_vehicle_list, viewGroup,false);
        return new VehicleItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleItem vehicleHolder, int position) {
        final VehicleDto vehicleDto = vehicleList.get(position);
        vehicleHolder.bindVehicle(vehicleDto);
        vehicleHolder.leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VehicleDto vehicle = vehicleList.get(position);
                DialogFragmentConfirmVehicleDeparture confirmVehicleDeparture =
                        new DialogFragmentConfirmVehicleDeparture(vehicle,position,vehicleListActivity);
                confirmVehicleDeparture.show(vehicleListActivity.getSupportFragmentManager().beginTransaction(),
                        "confirmVehicleDeparture");
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }
}
