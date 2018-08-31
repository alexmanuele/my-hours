package example.com.myhours;

import java.util.ArrayList;

import java.time.*;

public class PeriodCalculator {
    /* pay period is 14 days, starting on sunday and ending on saturday.
       pay day for a given period is then two thursdays later.
       eg period: Sun Jan 11 - Sat Jan 24, pay on Thur Feb 5
       Aug 5 -18, pay day aug 30
     */

    public ArrayList<LocalDate> periodStartDates;
    private LocalDate today;
    private LocalDate periodStart;
    private LocalDate periodEnd;

    public PeriodCalculator(){
        this.today = LocalDate.now();
        this.getPeriodStarts();
    }
    private void getPeriodStarts(){
        periodStartDates = new ArrayList<LocalDate>();
        LocalDate initial = LocalDate.of(2018, Month.AUGUST, 5);
        while(initial.getYear() < 2019){
            periodStartDates.add(initial);
            initial = initial.plusWeeks(2);
        }
    }

    public ArrayList<LocalDate> getPeriodStartDates(){
        return this.periodStartDates;
    }
    public LocalDate getPeriodStart(){
        return periodStart;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public LocalDate getToday() {
        return today;
    }

    public void getPeriod(){
        for(int i = 0; i < periodStartDates.size(); i++){
            this.periodStart = periodStartDates.get(i);
            this.periodEnd = periodStart.plusDays(13);
            if(today.isEqual(periodStart) || today.isEqual(periodEnd))
                break;
            if(today.isAfter(periodStart) && today.isBefore(periodEnd)){
                break;
            }
        }
    }
}
