package example.com.myhours;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class MobileShift extends Shift {
    public MobileShift(){}
    public MobileShift(LocalDateTime start, LocalDateTime end){
        super(start, end);
    }


    @Override
    public double calculateHours() {
        try{
            double hours = Duration.between(this.start, this.end).toMinutes();
            hours = hours/60;
            return hours;
        }catch(Exception e){
            return 0;
        }
    }
}
