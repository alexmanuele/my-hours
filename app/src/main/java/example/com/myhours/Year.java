package example.com.myhours;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Year implements Serializable {
    private int yearNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ConcurrentHashMap<String, PayPeriod> periods;
    private LocalDateTime firstPayPeriodStart;
    private DateTimeFormatter dtf;

    public Year(){}
    public Year(int year, LocalDateTime firstPayPeriodStart){
        this.yearNumber = year;
        this.startDate = LocalDateTime.of(year, 1,1,0,0);
        this.endDate = LocalDateTime.of(year, 12, 31,23,59);
        this.firstPayPeriodStart = firstPayPeriodStart;
        periods = new ConcurrentHashMap<String, PayPeriod>();
        dtf = DateTimeFormatter.ofPattern("MM dd");
        makePeriods();
    }

    public void setYearNumber(int yearNumber){
        this.yearNumber = yearNumber;
    }
    public int getYearNumber(){
        return this.yearNumber;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDateTime getFirstPayPeriodStart() {
        return firstPayPeriodStart;
    }
    public void setFirstPayPeriodStart(LocalDateTime firstPayPeriodStart){
        this.firstPayPeriodStart = firstPayPeriodStart;
    }

    public ConcurrentHashMap<String, PayPeriod> getPeriods() {
        return periods;
    }
    public void setPeriods(ConcurrentHashMap<String, PayPeriod> periods){
        this.periods = periods;
    }

    public void makePeriods(){
        LocalDateTime periodStart = this.firstPayPeriodStart;
        int year = this.yearNumber;
        LocalDateTime periodEnd;
        while(periodStart.getYear() < (year + 1)){
            periodEnd = periodStart.plusDays(13);
            this.periods.put(periodStart.format(dtf), new PayPeriod(periodStart, periodEnd, 0));
            periodStart = periodStart.plusWeeks(2);

        }

    }
    public void addShifts(Shift newShift, PayPeriod payPeriod){
        String periodStart = payPeriod.getStart().format(dtf);
        PayPeriod selectedPeriod = this.periods.get(periodStart);
        if(newShift.end.isAfter(payPeriod.getEnd())){
            LocalDateTime shiftEnd = newShift.getEnd();
            LocalDateTime midnightOfShift = LocalDateTime.of(
                    shiftEnd.getYear(), shiftEnd.getMonth(),
                    shiftEnd.getDayOfMonth(), 0,0);
            double hoursBeforeMidnight = Duration.between(newShift.getStart(), midnightOfShift).toMinutes();
            hoursBeforeMidnight = hoursBeforeMidnight / 60;
            double hoursAfterMidnight = newShift.getHours() - hoursBeforeMidnight;

            String nextPeriodStart = shiftEnd.format(dtf);

            this.periods.get(periodStart).addHours(hoursBeforeMidnight);
            this.periods.get(nextPeriodStart).addHours(hoursAfterMidnight);

        }
        else{
            periods.get(selectedPeriod).addHours(newShift.getHours());

        }
    }
}
