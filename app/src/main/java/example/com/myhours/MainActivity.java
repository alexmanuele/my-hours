package example.com.myhours;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity extends BaseActivity{
    DateTimeFormatter dtf;
    TextView payPeriod;
    public FirebaseDatabase firebaseDBInstance;
    public DatabaseReference firebaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addNavBar();
        setActivityLayout(R.layout.activity_main);
        firebaseDBInstance = FirebaseDatabase.getInstance();
        firebaseReference = firebaseDBInstance.getReference();

        payPeriod = (TextView) findViewById(R.id.main_payPeriodDates);
        PeriodCalculator pc = new PeriodCalculator();
        pc.getPeriod();
        dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate start = pc.getPeriodStart();
        LocalDate end = pc.getPeriodEnd();

        String periodDescription = start.format(dtf) + " - " + end.format(dtf);
        payPeriod.setText(periodDescription);


    }
/* This method was used during development to populate the database with
   objects.

    @Override
    public void onClick(View v) {
        if (v == button) {
            Year year = new Year(2019, LocalDateTime.of(2019, 1, 6, 0 ,0));
            try{
                firebaseReference.child("years").child("2019").setValue(year).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Log.d("year make ", "Write Successful");
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Write failed
                                // ...
                                String error = e.getMessage() + ": " + e.getCause();
                                Log.d("year make ", "Write Failed: " + error);
                            }
                        });

            }catch (Exception e){
                Toast.makeText(this, "Exception", Toast.LENGTH_LONG);
                String error = e.getMessage() + ": " + e.getCause();
                Log.d("year make exception ", error);
            };
        }
    } */
}
