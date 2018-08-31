package example.com.myhours;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
public class ShiftTest {
    @Test
    public void MobileShiftTest(){
        LocalDateTime start = LocalDateTime.of(2018, 1,1,7,0,0);
        LocalDateTime end = LocalDateTime.of(2018,1,1,19,30,0);
        MobileShift shift = new MobileShift(start, end);
        assertNotNull(shift.getHours());
        System.out.println(shift.getHours());
    }
}
