package example.com.myhours;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.time.*;

public class PeriodCalculator {
    /* pay period is 14 days, starting on sunday and ending on saturday.
       pay day for a given period is then two thursdays later.
       eg period: Sun Jan 11 - Sat Jan 24, pay on Thur Feb 5
       Aug 5 -18, pay day aug 30
     */

    public ArrayList<LocalDateTime> periodStartDates;
    private Year year;
    public PeriodCalculator(Year year){
        this.year = year;
        getPeriodStarts(year);
    }

    private void getPeriodStarts(Year year){
        periodStartDates = new ArrayList<LocalDateTime>();
        LocalDateTime initial = LocalDateTime.parse(year.getFirstPayPeriodStart());
        int yearNumber = initial.getYear() + 1;
        while(initial.getYear() < yearNumber){
            periodStartDates.add(initial);
            initial = initial.plusWeeks(2);
        }
    }

    public ArrayList<LocalDateTime> getPeriodStartDates(){
        return this.periodStartDates;
    }

    public PayPeriod getPeriod(LocalDateTime date) {
        LocalDateTime periodStart = periodStartDates.get(0);
        LocalDateTime periodEnd;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM dd");
        for (int i = 0; i < periodStartDates.size(); i++) {
            periodStart = periodStartDates.get(i);
            periodEnd = periodStart.plusDays(13);
            if (date.isEqual(periodStart) || date.isEqual(periodEnd))
                break;
            if (date.isAfter(periodStart) && date.isBefore(periodEnd)) {
                break;
            }
        }
        return this.year.getPeriods().get(periodStart.format(dtf));

    }
}
