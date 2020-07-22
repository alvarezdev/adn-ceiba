package ceiba.adn.parking.presentations.VehicleList.RecyclerElement;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;

import ceiba.adn.parking.R;
import ceiba.adn.parking.dtos.VehicleDto;

public class VehicleItem extends RecyclerView.ViewHolder {

    private TextView plate;
    private TextView vehicleType;
    private TextView cylinderCapacity;
    private TextView entryTime;
    public Button leave;


    public VehicleItem(@NonNull View itemView) {
        super(itemView);
        plate = itemView.findViewById(R.id.plate);
        vehicleType = itemView.findViewById(R.id.vehicle_type);
        cylinderCapacity = itemView.findViewById(R.id.cylinder_capacity);
        entryTime = itemView.findViewById(R.id.entry_time);
        leave = itemView.findViewById(R.id.leave_vehicle);
    }

    public void bindVehicle(VehicleDto vehicleDto) {
        if (vehicleDto != null) {
            plate.setText(vehicleDto.getPlate());
            vehicleType.setText(vehicleDto.getVehicleType().toString());
            cylinderCapacity.setText(String.valueOf(vehicleDto.getCylinderCapacity()));
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            String date = formatter.format(vehicleDto.getVehicleEntryTime());
            entryTime.setText(date);
        }
    }
}