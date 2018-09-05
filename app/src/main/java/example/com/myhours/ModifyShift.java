package example.com.myhours;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ModifyShift extends DialogFragment {

    public ModifyShift() {
        // Required empty public constructor
    }

    PayPeriod selectedPeriod;
    LocalDateTime selectedDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd");
        String periodDescription = String.format("%s - %s", selectedPeriod.detailStart().format(dtf), selectedPeriod.detailEnd().format(dtf));
        builder.setTitle(periodDescription);
        builder.setView(inflater.inflate(R.layout.fragment_modify_shift, null))
                .setMessage(String.format("Hours remaining: %.1f", (75 - selectedPeriod.getNumHours())));
        builder.setPositiveButton("Add a Shift", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }

    public void setInputData(LocalDateTime localDateTimeIn, PayPeriod payPeriodIn){
        this.selectedDate = localDateTimeIn;
        this.selectedPeriod = payPeriodIn;
    }
}