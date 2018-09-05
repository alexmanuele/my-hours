package example.com.myhours;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;


public class Year implements Serializable {
    private int yearNumber;
    private String startDate;
    private String endDate;
    private HashMap<String, PayPeriod> periods;
    private String firstPayPeriodStart;
    private DateTimeFormatter dtf;

    public Year(){}
    public Year(int year, LocalDateTime firstPayPeriodStart){
        this.yearNumber = year;
        this.startDate = LocalDateTime.of(year, 1,1,0,0).toString();
        this.endDate = LocalDateTime.of(year, 12, 31,23,59).toString();
        this.firstPayPeriodStart = firstPayPeriodStart.toString();
        periods = new HashMap<String, PayPeriod>();
        dtf = DateTimeFormatter.ofPattern("MM dd");
        makePeriods();
    }

    public void setYearNumber(int yearNumber){
        this.yearNumber = yearNumber;
    }
    public int getYearNumber(){
        return this.yearNumber;
    }
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }
    public LocalDateTime getDetailStartDate(){
        return LocalDateTime.parse(this.startDate);
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public LocalDateTime getDetailEndDate(){return LocalDateTime.parse(this.endDate);}


    public String getFirstPayPeriodStart() {
        return firstPayPeriodStart;
    }
    public void setFirstPayPeriodStart(String firstPayPeriodStart){
        this.firstPayPeriodStart = firstPayPeriodStart;
    }

    public HashMap<String, PayPeriod> getPeriods() {
        return periods;
    }
    public void setPeriods(HashMap<String, PayPeriod> periods){
        this.periods = periods;
    }

    public void makePeriods(){
        LocalDateTime periodStart = LocalDateTime.parse(this.firstPayPeriodStart);
        int year = this.yearNumber;
        LocalDateTime periodEnd;
        while(periodStart.getYear() < (year + 1)){
            periodEnd = periodStart.plusDays(13);
            this.periods.put(periodStart.format(dtf), new PayPeriod(periodStart, periodEnd, 0));
            periodStart = periodStart.plusWeeks(2);

        }

    }
    public void addShifts(Shift newShift, PayPeriod payPeriod){
        String periodStart = payPeriod.detailStart().format(dtf);
        System.out.println("periodStart string: " + periodStart);
        PayPeriod selectedPeriod = this.periods.get(periodStart);
        System.out.println(selectedPeriod.getStart());
        if(newShift.detailEnd().isAfter(payPeriod.detailEnd())){
            LocalDateTime shiftEnd = newShift.detailEnd();
            LocalDateTime midnightOfShift = LocalDateTime.of(
                    shiftEnd.getYear(), shiftEnd.getMonth(),
                    shiftEnd.getDayOfMonth(), 0,0);
            double hoursBeforeMidnight = Duration.between(newShift.detailStart(), midnightOfShift).toMinutes();
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
