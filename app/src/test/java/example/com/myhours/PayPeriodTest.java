package example.com.myhours;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static org.junit.Assert.*;
public class PayPeriodTest {
    @Test
    public void getPayPeriodTest(){
        //SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
        PeriodCalculator pc = new PeriodCalculator();
        pc.getPeriod();
        for(int i = 0; i < pc.periodStartDates.size(); i++){
            System.out.println(pc.periodStartDates.get(i));
        }
        assertNotNull(pc.getPeriodStart());
        assertNotNull(pc.getPeriodEnd());

    }
}
