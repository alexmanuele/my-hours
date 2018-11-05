package example.com.myhours;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;

public class AddShiftsActivity extends BaseActivity implements View.OnClickListener  {
    Button dateCheck;
    long calendarEpochValue;
    LocalDateTime calendarDate;
    CalendarView calendarView;
    public FirebaseDatabase firebaseDBInstance;
    public DatabaseReference firebaseReference;
    PayPeriod payPeriod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addNavBar();
        setActivityLayout(R.layout.activity_add_shifts);
        dateCheck = (Button) findViewById(R.id.shifts_datebutton);
        dateCheck.setOnClickListener(this);
        calendarView = (CalendarView) findViewById(R.id.shifts_calendarView);
        firebaseDBInstance = FirebaseDatabase.getInstance();
        firebaseReference = firebaseDBInstance.getReference();
        calendarEpochValue = calendarView.getDate();
        calendarDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(calendarEpochValue), ZoneId.systemDefault());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //calendarEpochValue = calendarView.getDate();
                //calendarDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(calendarEpochValue), ZoneId.systemDefault());
                calendarDate = LocalDateTime.of(year, month+1, dayOfMonth, 0, 0);
                Log.d("date change: ", calendarDate.toString());
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == dateCheck){
          //  calendarEpochValue = calendarView.getDate();

            /*
            use ValueEventListener to query DB for the year using calendarDate.getYear()
            In onDataChange, call a PeriodCalculator to get the period from the year
            Call openDialog(), passing the year and calendarDate
             */
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.d("AddShifts Datbase Query: " , "Successful");
                    Year year = dataSnapshot.getValue(Year.class);
                    PeriodCalculator pc = new PeriodCalculator(year);
                    payPeriod = pc.getPeriod(calendarDate);
                    //openDialog(calendarDate, payPeriod);
                    /* Replaces the dialog fragment with an activity.
                    ModifyShiftDialog dialog = new ModifyShiftDialog();
                    dialog.setInputData(calendarDate, payPeriod);
                    dialog.show(getSupportFragmentManager(), calendarDate.toString());
                    */


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("AddShifts Database Query: ", " Failed");
                }
            };
            firebaseReference.child("years").child(String.format(Locale.CANADA, "%d",
                    calendarDate.getYear())).addValueEventListener(valueEventListener);
            Intent intent = new Intent(this, ModifyShiftActivity.class);
            intent.putExtra("Date", calendarDate);
            intent.putExtra("Period", payPeriod);
            startActivity(intent);

        }
    }
/*
    public void openDialog(LocalDateTime selectedDate, PayPeriod payPeriod){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String periodLength = payPeriod.detailStart().format(dtf) + " - " + payPeriod.detailEnd().format(dtf);
        builder.setTitle(periodLength);
        builder.setMessage(selectedDate.format(dtf)
        + "\nHours in this pay period: " + String.format(Locale.CANADA, "%.1f", payPeriod.getNumHours()));

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("Something else", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        Dialog alertDialog = builder.create();
        alertDialog.show();
    }
*/
}
