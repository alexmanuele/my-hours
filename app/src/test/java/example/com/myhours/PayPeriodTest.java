package example.com.myhours;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
public class PayPeriodTest {
    @Test
    public void getPayPeriodTest(){

      /*  PeriodCalculator pc = new PeriodCalculator();
        pc.getPeriod();
        for(int i = 0; i < pc.periodStartDates.size(); i++){
            System.out.println(pc.periodStartDates.get(i));
        }
        assertNotNull(pc.getPeriodStart());
        assertNotNull(pc.getPeriodEnd());
*/
      Year year = new Year(2018, LocalDateTime.of(2018,01,07,00,00));
      PeriodCalculator periodCalculator = new PeriodCalculator(year);
      PayPeriod payPeriod = periodCalculator.getPeriod(LocalDateTime.of(2018,01,13,0,0));
      System.out.println(payPeriod.getStart());
    }
}
