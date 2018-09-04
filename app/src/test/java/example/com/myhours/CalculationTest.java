package example.com.myhours;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CalculationTest {
    @Test
    public void nightShiftTest(){
        Year testYear = new Year(2018, LocalDateTime.of(2018,1,7,0,0));
        MobileShift nightShift = new MobileShift(LocalDateTime.of(2018,1,20,23,0),
                LocalDateTime.of(2018,1,21,7,0));
        testYear.addShifts(nightShift, testYear.getPeriods().get("01 07"));
        System.out.println(testYear.getPeriods().get("01 07").getNumHours());
        System.out.println(testYear.getPeriods().get("01 21").getNumHours());
        System.out.println(testYear.getPeriods().get("01 21").getStart());
    }
}
