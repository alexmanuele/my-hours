package example.com.myhours;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class YearTest {
    @Test
    public void constructorTest(){
        Year testYear = new Year(2018, LocalDateTime.of(2018,1,7,0,0));
        testYear.makePeriods();


    }

}
