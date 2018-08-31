package example.com.myhours;

import java.time.Duration;
import java.time.LocalDateTime;

public class AddictionsShift extends Shift {

    public AddictionsShift(){}
    public AddictionsShift(LocalDateTime start, LocalDateTime end){
        super(start, end);
    }
    @Override
    public double calculateHours() {
        try{
            double hours = Duration.between(this.start, this.end).toMinutes();
            hours = hours/60;
            if(hours >= 8) {
                hours -= 1;
            }
            return hours;
        }catch(Exception e){
            return 0;
        }
    }
}
