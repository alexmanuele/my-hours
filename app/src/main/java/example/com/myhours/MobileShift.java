package example.com.myhours;

import java.time.Duration;
import java.time.LocalDateTime;

public class MobileShift extends Shift {
    public MobileShift(){}
    public MobileShift(LocalDateTime start, LocalDateTime end){
        super(start, end);
    }

    @Override
    public double calculateHours() {
        try{
            double hours = Duration.between(LocalDateTime.parse(this.start),
                    LocalDateTime.parse(this.end)).toMinutes();
            hours = hours/60;
            if(hours >= 12){
                hours -= 0.75;
            }
            else if(hours >= 8){
                hours -= 0.5;
            }
            return hours;
        }catch(Exception e){
            return 0;
        }
    }
}
