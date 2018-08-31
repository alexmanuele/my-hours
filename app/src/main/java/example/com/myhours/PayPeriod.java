package example.com.myhours;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PayPeriod implements Serializable{
    private LocalDateTime start;
    private LocalDateTime end;
    private double numHours;
    private ArrayList<Shift> shifts;

    public PayPeriod(){}
    public PayPeriod(LocalDateTime start, LocalDateTime end, int hours){
        this.start = start;
        this.end = end;
        this.numHours = hours;
        shifts = new ArrayList<Shift>();
    }

    public LocalDateTime getStart() {
        return start;
    }
    public void setStart(LocalDateTime start){
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    public double getNumHours(){
        return this.numHours;
    }
    public void setNumHours(double hours){
        this.numHours = hours;
    }
    public void setShifts(ArrayList<Shift> shifts){
        this.shifts = shifts;
    }
    public ArrayList<Shift> getShifts(){
        return this.shifts;
    }

}
