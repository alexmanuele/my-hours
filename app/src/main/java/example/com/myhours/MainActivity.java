package example.com.myhours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity extends BaseActivity {
    DateTimeFormatter dtf;
    TextView payPeriod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addNavBar();
        setActivityLayout(R.layout.activity_main);
        payPeriod = (TextView) findViewById(R.id.main_payPeriodDates);
        PeriodCalculator pc = new PeriodCalculator();
        pc.getPeriod();
        dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate start = pc.getPeriodStart();
        LocalDate end = pc.getPeriodEnd();

        String periodDescription = start.format(dtf) + " - " + end.format(dtf);
        payPeriod.setText(periodDescription);


    }
}
