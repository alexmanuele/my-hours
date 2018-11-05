package example.com.myhours;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ModifyShiftActivity extends AppCompatActivity {

    PayPeriod selectedPeriod;
    LocalDateTime selectedDate;
    TextView title;
    Button submitButton;
    Button cancelButton;
    EditText startTime;
    EditText endTime;
    EditText endDate;
    boolean isNightShift;
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().hasExtra("Date")){
            selectedDate = (LocalDateTime) getIntent().getSerializableExtra("Date");
        }
        if(getIntent().hasExtra("Period")){
            selectedPeriod = (PayPeriod) getIntent().getSerializableExtra("Period");
        }
        setContentView(R.layout.activity_modify_shift);
        title = findViewById(R.id.shift_Title);
        if(selectedDate != null) {
            title.setText(String.format("Add a shift on %s", dtf.format(selectedDate)));
        }
        startTime = findViewById(R.id.shift_startTimePick);
        endTime = findViewById(R.id.shift_endTimePick);
        View.OnClickListener timePickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a timepicker dialog and make it set the text of this view to the selected tim
                TimePickerDialog mTimePicker;
                if (v == startTime) {
                    mTimePicker = new TimePickerDialog(ModifyShiftActivity.this, AlertDialog.THEME_HOLO_DARK, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            startTime.setText(hourOfDay + ":" + minute);

                        }
                    }, 0, 0, true);
                    mTimePicker.show();
                }else if (v == endTime){
                    mTimePicker = new TimePickerDialog(ModifyShiftActivity.this, AlertDialog.THEME_HOLO_DARK, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            endTime.setText(hourOfDay + ":" + minute);
                        }
                    } ,0, 0, true);
                    mTimePicker.show();
                }

            }
        };
        startTime.setOnClickListener(timePickListener);
        endTime.setOnClickListener(timePickListener);
    }


}
