package br.com.jaemilton.quartz_cron_generator;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.jaemilton.quartz_cron_generator.enums.DaysOfWeek;
import br.com.jaemilton.quartz_cron_generator.enums.Months;

public class CronConverter 
{
    private CronConverter() {}
    
    public static String toCronRepresentationSingle(DaysOfWeek day)
    {
        switch (day)
        {
            case MONDAY:
                return "MON";
            case TUESDAY:
                return "TUE";
            case WEDNESDAY:
                return "WED";
            case THURSDAY:
                return "THU";
            case FRIDAY:
                return "FRI";
            case SATURDAY:
                return "SAT";
            case SUNDAY:
                return "SUN";
            default:
                throw new IllegalArgumentException();
        }
    }

    public static String toCronRepresentationSingle(Months month)
    {
        switch (month)
        {
            case JANUARY:
                return "JAN";
            case FEBRUARY:
                return "FEB";
            case MARCH:
                return "MAR";
            case APRIL:
                return "APR";
            case MAY:
                return "MAY";
            case JUNE:
                return "JUN";
            case JULY:
                return "JUL";
            case AUGUST:
            	return "AUG";
            case SEPTEMBER:
            	return "SEP";
            case OCTOBER:
            	return "OCT";
            case NOVEMBER:
            	return "NOV";
            case DECEMBER:
            	return "DEC";
            default:
                throw new IllegalArgumentException();
        }
    }

    
    public static  String daysOfWeekToCronRepresentation(Set<DaysOfWeek> days)
    {
        return days.stream().map(CronConverter::toCronRepresentationSingle)
        		.collect(Collectors.joining(","));
    }
    
 
    public static String monthsToCronRepresentation(Set<Months> months)
    {
        return months.stream().map(CronConverter::toCronRepresentationSingle)
        		.collect(Collectors.joining(","));
    }

}
