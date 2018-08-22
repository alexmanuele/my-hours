package example.com.myhours;

import java.io.Serializable;
import java.time.LocalDate;

public class PayPeriod implements Serializable{
    private LocalDate start;
    private LocalDate end;
    private double numHours;

    public PayPeriod(){}
    public PayPeriod(LocalDate start, LocalDate end, int hours){
        this.start = start;
        this.end = end;
        this.numHours = hours;
    }

    public LocalDate getStart() {
        return start;
    }
    public void setStart(LocalDate start){
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
    public double getNumHours(){
        return this.numHours;
    }
    public void setNumHours(double hours){
        this.numHours = hours;
    }
}
