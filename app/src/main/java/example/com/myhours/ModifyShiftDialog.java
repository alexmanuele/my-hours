package example.com.myhours;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ModifyShiftDialog extends DialogFragment {

    public ModifyShiftDialog() {
        // Required empty public constructor
    }

    PayPeriod selectedPeriod;
    LocalDateTime selectedDate;
    TextView textView;
    CheckBox checkBox;
    boolean isNightShift;
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd");


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.fragment_modify_shift, null);

            String periodDescription = String.format("%s - %s", selectedPeriod.detailStart().format(dtf), selectedPeriod.detailEnd().format(dtf));
            builder.setTitle(periodDescription);
            builder.setView(dialogView)
                    .setMessage(String.format("Hours remaining: %.1f", (75 - selectedPeriod.getNumHours())));
            textView = (TextView) dialogView.findViewById(R.id.modShift_date);
            checkBox = (CheckBox) dialogView.findViewById(R.id.modShift_checkBox);
            textView.setText(selectedDate.format(dtf));

            builder.setPositiveButton("Add a Shift", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    textView = (TextView) getDialog().findViewById(R.id.modShift_date);
                    checkBox = (CheckBox) getDialog().findViewById(R.id.modShift_checkBox);
                    textView.setText(selectedDate.format(dtf));
                    if(checkBox.isChecked()){
                        isNightShift = true;
                    }else{
                        isNightShift = false;
                    }
                    showTimePicker(selectedDate);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            return builder.create();
        }
    /*
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            textView = (TextView) getDialog().findViewById(R.id.modShift_date);
            checkBox = (CheckBox) getDialog().findViewById(R.id.modShift_checkBox);
            textView.setText(selectedDate.format(dtf));
        }
    */
    public void setInputData(LocalDateTime localDateTimeIn, PayPeriod payPeriodIn){
        this.selectedDate = localDateTimeIn;
        this.selectedPeriod = payPeriodIn;
    }
    public void showTimePicker(LocalDateTime selectedDate){
        TimePickDialog newFragment = new TimePickDialog();
        newFragment.passDate(selectedDate);
        newFragment.show(getFragmentManager(), "timePicker");
    }
}