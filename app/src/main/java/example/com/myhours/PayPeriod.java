package example.com.myhours;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PayPeriod implements Serializable{
    private String start;
    private String end;
   // private LocalDateTime detailStart;
   //private LocalDateTime detailEnd;
    private double numHours;
    private ArrayList<Shift> shifts;

    public PayPeriod(){

    }
    public PayPeriod(LocalDateTime start, LocalDateTime end, int hours){
        this.start = start.toString();
        this.end = end.toString();
   //     this.detailStart = start;
   //     this.detailEnd = end;
        this.numHours = hours;
        shifts = new ArrayList<Shift>();
    }

    public String getStart() {
        return start;
    }
    public void setStart(String start){
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
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

    public LocalDateTime detailEnd() {
        return LocalDateTime.parse(this.end);
    }

    public LocalDateTime detailStart() {
        return LocalDateTime.parse(this.start);
    }


    public ArrayList<Shift> getShifts(){
        return this.shifts;
    }
    public void addHours(double hours){
        this.numHours += hours;
    }

}
