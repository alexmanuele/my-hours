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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends BaseActivity{
    DateTimeFormatter dtf;
    TextView payPeriod;
    TextView numberOfHours;
    public FirebaseDatabase firebaseDBInstance;
    public DatabaseReference firebaseReference;
    PayPeriod currentPeriod;
    //Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addNavBar();
        setActivityLayout(R.layout.activity_main);
        firebaseDBInstance = FirebaseDatabase.getInstance();
        firebaseReference = firebaseDBInstance.getReference();
       // button = (Button) findViewById(R.id.button);
       // button.setOnClickListener(this);
        payPeriod = (TextView) findViewById(R.id.main_payPeriodDates);
        numberOfHours = (TextView) findViewById(R.id.main_numHours);

        PeriodCalculator pc = new PeriodCalculator();
        pc.getPeriod();
        dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate start = pc.getPeriodStart();
        LocalDate end = pc.getPeriodEnd();
        DateTimeFormatter periodKey = DateTimeFormatter.ofPattern("MM dd");

        ValueEventListener payPeriodListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentPeriod = (PayPeriod) dataSnapshot.getValue(PayPeriod.class);
                Log.w("Database read: ", "listener:onDataChanged");
                if(currentPeriod != null) {
                    String periodDescription = currentPeriod.detailStart().format(dtf) + " - " + currentPeriod.detailEnd().format(dtf);
                    payPeriod.setText(periodDescription);
                    numberOfHours.setText(String.format(Locale.CANADA, "Number of hours: %.1f", currentPeriod.getNumHours()));
                }else {
                    payPeriod.setText(R.string.main_databaseReadError);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Database read: ", "listener:onCancelled", databaseError.toException());
            }
        };

        firebaseReference.child("years").child(String.format(Locale.CANADA, "%d", start.getYear())).child("periods")
                .child(start.format(periodKey)).addValueEventListener(payPeriodListener);




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
