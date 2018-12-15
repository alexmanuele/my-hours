package example.com.myhours;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.ToDoubleBiFunction;

public class ModifyShiftActivity extends AppCompatActivity {

    PayPeriod selectedPeriod;
    LocalDateTime selectedDate;
    TextView title;
    Button submitButton;
    Button cancelButton;
    EditText startTime;
    LocalDateTime start;
    EditText endTime;
    LocalDateTime end;
    Spinner shiftType;
    boolean isNightShift;
    int job = 0;
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("Date")) {
            selectedDate = (LocalDateTime) getIntent().getSerializableExtra("Date");
        }
        if (getIntent().hasExtra("Period")) {
            selectedPeriod = (PayPeriod) getIntent().getSerializableExtra("Period");
        }
        setContentView(R.layout.activity_modify_shift);
        title = findViewById(R.id.shift_Title);
        if (selectedDate != null) {
            title.setText(String.format("Add a shift on %s", dtf.format(selectedDate)));
        }
        startTime = findViewById(R.id.shift_startTimePick);
        endTime = findViewById(R.id.shift_endTimePick);
        submitButton = findViewById(R.id.shift_submit);
        cancelButton = findViewById(R.id.shift_cancel);
        shiftType = findViewById(R.id.shift_typeSelection);
        //Populate dropdown menu
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.shiftTypes,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shiftType.setAdapter(adapter);

        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                job = position;
                Log.d("Job id: ", String.format("%d",job));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                job = 0;
            }
        };
        shiftType.setOnItemSelectedListener(spinnerListener);
        View.OnClickListener timePickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a timepicker dialog and make it set the text of this view to the selected tim
                TimePickerDialog mTimePicker;
                if (v == startTime) {
                    mTimePicker = new TimePickerDialog(ModifyShiftActivity.this, AlertDialog.THEME_HOLO_DARK, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            startTime.setText(String.format(Locale.CANADA, "%02d : %02d", hourOfDay,minute));
                            start = LocalDateTime.of(selectedDate.getYear(), selectedDate.getMonth(), selectedDate.getDayOfMonth(),
                                    hourOfDay, minute);
                        }
                    }, 0, 0, true);
                    mTimePicker.show();
                } else if (v == endTime) {
                    mTimePicker = new TimePickerDialog(ModifyShiftActivity.this, AlertDialog.THEME_HOLO_DARK, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            endTime.setText(String.format(Locale.CANADA, "%02d : %02d", hourOfDay,minute));
                            end = start = LocalDateTime.of(selectedDate.getYear(), selectedDate.getMonth(), selectedDate.getDayOfMonth(),
                                    hourOfDay, minute);
                        }
                    }, 0, 0, true);
                    mTimePicker.show();
                } else if (v == submitButton){
                    if(job == 0){
                        Toast.makeText(ModifyShiftActivity.this, "Please select a shift type.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(startTime.getText().toString().equals("")){
                        Toast.makeText(ModifyShiftActivity.this, "You must enter a start time."
                        ,Toast.LENGTH_SHORT).show();
                    }else if(endTime.getText().toString().equals("")){
                        Toast.makeText(ModifyShiftActivity.this, "You must enter an end time."
                                ,Toast.LENGTH_SHORT).show();
                    }else{
                        //job 1 is mobile
                        Shift shift;
                        if (job==1){
                          shift = new MobileShift(start, end);
                        }
                        else if (job==2){
                            shift = new AddictionsShift(start, end);
                        }
                        /*TODO: PUSH SHIFT TO Firebase here.
                         * Will need to wrap in a null pointer conditional for shift.
                         * not sure if downcasting works like the way I wrote it either, double check.
                         * Make a dialogue appear showing that the shift was added successfully.
                         */
                    }
                }else if (v==cancelButton){
                    Intent intent = new Intent(ModifyShiftActivity.this, AddShiftsActivity.class);
                    ModifyShiftActivity.this.finish();
                    startActivity(intent);
                }

            }
        };


        startTime.setOnClickListener(timePickListener);
        endTime.setOnClickListener(timePickListener);
        submitButton.setOnClickListener(timePickListener);
        cancelButton.setOnClickListener(timePickListener);
    }


}
