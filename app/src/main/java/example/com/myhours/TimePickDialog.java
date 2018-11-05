package example.com.myhours;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.time.LocalDateTime;
import java.util.Calendar;


public class TimePickDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private LocalDateTime selectedDate;
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    public TimePickDialog() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT,this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        shiftStart = LocalDateTime.of(selectedDate.getYear(),selectedDate.getMonth(), selectedDate.getDayOfMonth(), hourOfDay,minute);

    }
    public void passDate(LocalDateTime selectedDate){
        this.selectedDate = selectedDate;
    }


}
