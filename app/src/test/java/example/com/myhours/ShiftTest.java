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
        System.out.println("Twelve and half hour shift billable for:" + shift.getHours());
        MobileShift overnight = new MobileShift(
                LocalDateTime.of(2018,2,1,23,0),
                LocalDateTime.of(2018,2,2,7,0)
        );
        assertEquals(7.5, overnight.getHours(), 0.002);

    }
    @Test
    public void AddictionsShiftTest(){
        AddictionsShift shift = new AddictionsShift(
                LocalDateTime.of(2018,1,1,8,0),
                LocalDateTime.of(2018,1,1,16,30)
        );
        assertEquals(7.5, shift.getHours(), 0.002);
    }
}
