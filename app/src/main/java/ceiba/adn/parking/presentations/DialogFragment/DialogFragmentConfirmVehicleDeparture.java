package ceiba.adn.parking.presentations.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ceiba.adn.parking.R;
import ceiba.adn.parking.dtos.VehicleDto;
import ceiba.adn.parking.presentations.VehicleList.VehicleListActivity;

public class DialogFragmentConfirmVehicleDeparture extends DialogFragment {

    private TextView payMessage;
    private Button accept;
    private Button cancel;

    private VehicleDto vehicleDto;
    private int position;
    private VehicleListActivity vehicleListActivity;

    public DialogFragmentConfirmVehicleDeparture(VehicleDto vehicleDto, int position, VehicleListActivity vehicleListActivity) {
        this.vehicleDto = vehicleDto;
        this.position = position;
        this.vehicleListActivity = vehicleListActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_confirm_vehicle_departure,container,false);
        payMessage = view.findViewById(R.id.pay_message);
        accept = view.findViewById(R.id.accept_dialog);
        cancel = view.findViewById(R.id.cancel_dialog);

        int valuePayForParking = vehicleListActivity.calculateValueParking(vehicleDto);

        payMessage.setText(getString(R.string.text_pay_message_dialog_fragment) + " " + valuePayForParking);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicleListActivity.deleteVehicleFromDialogFragment(vehicleDto, position);
                getDialog().dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }
}
