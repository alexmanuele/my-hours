package example.com.myhours;

import java.io.Serializable;
import java.time.*;
public abstract class Shift implements Serializable {
    public Shift(){}
    public Shift(LocalDateTime start, LocalDateTime end){
        this.start = start.toString();
        this.end = end.toString();
        this.hours = calculateHours();
    }
    protected String start;
    protected String end;
    protected double hours;
    public String getStart(){
        return this.start;
    }
    public void setStart(String start){
        this.start = start;
    }
    public String getEnd(){
        return this.end;
    }
    public void setEnd(String end){
        this.end = end;
    }
    public double getHours(){
        return this.hours;
    }
    public LocalDateTime detailStart(){return LocalDateTime.parse(this.start);}
    public LocalDateTime detailEnd(){return LocalDateTime.parse(this.end);}

    public abstract double calculateHours();



}
