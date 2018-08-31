package example.com.myhours;

import java.io.Serializable;
import java.time.*;
public abstract class Shift implements Serializable {
    public Shift(){}
    public Shift(LocalDateTime start, LocalDateTime end){
        this.start = start;
        this.end = end;
        this.hours = calculateHours();
    }
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected double hours;
    public LocalDateTime getStart(){
        return this.start;
    }
    public void setStart(LocalDateTime start){
        this.start = start;
    }
    public LocalDateTime getEnd(){
        return this.end;
    }
    public void setEnd(LocalDateTime end){
        this.end = end;
    }
    public double getHours(){
        return this.hours;
    }

    public abstract double calculateHours();



}
