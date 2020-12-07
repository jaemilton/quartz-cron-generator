package br.com.jaemilton.quartz_cron_generator;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Set;

import br.com.jaemilton.quartz_cron_generator.enums.CronExpressionType;
import br.com.jaemilton.quartz_cron_generator.enums.DaySeqNumber;
import br.com.jaemilton.quartz_cron_generator.enums.DaysOfWeek;
import br.com.jaemilton.quartz_cron_generator.enums.Months;

public class QuartzCronExpression {

	private Set<DaysOfWeek> daysOfWeek;
	private DaysOfWeek dayOfWeek;
    private Set<Months> months;
    private int interval;
    private DaySeqNumber daySeqNumber;
    private int dayNumber;
    private int startHour;
    private int startMinute;
    private int yearNumber;
    private int startSecond;

    private CronExpressionType expressionType;
    private String cronString;

    public CronExpressionType getExpressionType()
    {
        return expressionType;
    }

    private void buildCronExpression()
    {
        switch (expressionType)
        {
            case EVERY_N_SECONDS:
                cronString = String.format("0/%d * * 1/1 * ? *", interval);
                break;
            case EVERY_N_MINUTES:
                cronString = String.format("0 0/%d * 1/1 * ? *", interval);
                break;
            case EVERY_N_HOURS:
                cronString = String.format("0 0 0/%d 1/1 * ? *", interval);
                break;
            case EVERY_N_DAYS_AT:
            case EVERY_DAY_AT:
                cronString = String.format("0 %d %d 1/%d * ? *",startMinute, startHour, interval);
                break;
            case EVERY_WEEK_DAY:
                cronString = String.format("0 %d %d ? * MON-FRI *", startMinute, startHour);
                break;
            case EVERY_SPECIFIC_WEEK_DAY_AT: //OK
                cronString = String.format("0 %d %d ? * %s *", startMinute, startHour, CronConverter.daysOfWeekToCronRepresentation(daysOfWeek));
                break;
            case EVERY_SPECIFIC_DAY_EVERY_N_MONTH_AT:
                cronString = String.format("0 %d %d %d 1/%d ? *", startMinute, startHour, dayNumber, interval);
                break;
            case EVERY_SPECIFIC_SEQ_WEEK_DAY_EVERY_N_MONTH_AT: //OK
                cronString = String.format("0 %d %d ? 1/%d %s#%d *", startMinute, startHour, interval, CronConverter.toCronRepresentationSingle(dayOfWeek), daySeqNumber.getId());
                break;
            case EVERY_SPECIFIC_DAY_OF_MONTH_AT:
                cronString = String.format("0 %d %d %d %s ? *", startMinute, startHour, dayNumber, CronConverter.monthsToCronRepresentation(months));
                break;
            case EVERY_SPECIFIC_SEQ_WEEKDAY_OF_MONTH_AT:
                cronString = String.format("0 %d %d ? %s %s#%d *", startMinute, startHour, CronConverter.monthsToCronRepresentation(months), CronConverter.toCronRepresentationSingle(dayOfWeek), daySeqNumber.getId());
                break;
            case SPECIFIC_DATE_AT:
                cronString = String.format("%d %d %d %d %s ? %d", startSecond, startMinute, startHour, dayNumber, CronConverter.monthsToCronRepresentation(months), yearNumber);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    protected QuartzCronExpression(int interval, CronExpressionType expressionType)
    {
        this.interval = interval;
        this.expressionType = expressionType;

        buildCronExpression();
    }

    protected QuartzCronExpression(int startHour, int startMinute, CronExpressionType expressionType)
    {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.expressionType = expressionType;

        buildCronExpression();
    }

    protected QuartzCronExpression(int interval, int startHour, int startMinute, CronExpressionType expressionType)
    {
        this.interval = interval;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.expressionType = expressionType;

        buildCronExpression();
    }

    protected QuartzCronExpression(Set<DaysOfWeek> daysOfWeek, int startHour, int startMinute, CronExpressionType expressionType)
    {
        this.daysOfWeek = daysOfWeek;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.expressionType = expressionType;

        buildCronExpression();
    }

    protected QuartzCronExpression(int dayNumber, int interval, int startHour, int startMinute, CronExpressionType expressionType)
    {
        this.dayNumber = dayNumber;
        this.interval = interval;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.expressionType = expressionType;

        buildCronExpression();
    }

    protected QuartzCronExpression(DaySeqNumber daySeqNumber, DaysOfWeek dayOfWeek, int monthInverval, int startHour, int startMinute, CronExpressionType expressionType)
    {
        this.daySeqNumber = daySeqNumber;
        this.dayOfWeek = dayOfWeek;
        this.interval = monthInverval;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.expressionType = expressionType;

        buildCronExpression();
    }

    protected QuartzCronExpression(Set<Months> months, int dayNumber, int startHour, int startMinute, CronExpressionType expressionType)
    {
        this.months = months;
        this.dayNumber = dayNumber;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.expressionType = expressionType;

        buildCronExpression();
    }

    protected QuartzCronExpression(DaySeqNumber daySeqNumber, DaysOfWeek dayOfWeek, Set<Months> months, int startHour, int startMinute, CronExpressionType expressionType)
    {
        this.daySeqNumber = daySeqNumber;
        this.dayOfWeek = dayOfWeek;
        this.months = months;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.expressionType = expressionType;

        buildCronExpression();
    }

    protected QuartzCronExpression(int secondNumber, int startMinute, int startHour, int dayNumber, int monthNumber, int yearNumber, CronExpressionType expressionType)
    {
        this.startSecond = secondNumber;
        this.startMinute = startMinute;
        this.startHour = startHour;
        this.dayNumber = dayNumber;
        this.months = EnumSet.of(Months.fromId(monthNumber));
        this.yearNumber = yearNumber;
        this.expressionType = expressionType;

        buildCronExpression();
    }


    /// <summary>
    /// Create new CronExpression instance, which occurs every *secondInteval* seconds
    /// </summary>
    /// <param name="secondsInteval">Interval in seconds</param>
    /// <returns></returns>
    /**
     * @param secondsInteval
     * @return
     */
    public static QuartzCronExpression everyNSeconds(int secondsInteval)
    {
        return new QuartzCronExpression(secondsInteval, CronExpressionType.EVERY_N_SECONDS);
    }

    /// <summary>
    /// Create new CronExpression instance, which occurs every *minutesInteval* minutes
    /// </summary>
    /// <param name="minutesInteval">Interval in minutes</param>
    /// <returns>New CronExpression instance</returns>
    /**
     * @param minutesInteval
     * @return
     */
    public static QuartzCronExpression everyNMinutes(int minutesInteval)
    {
        return new QuartzCronExpression(minutesInteval, CronExpressionType.EVERY_N_MINUTES);
    }

    /// <summary>
    /// Create new CronExpression instance, which occurs every *hoursInterval* hours
    /// </summary>
    /// <param name="hoursInterval">Interval in hours</param>
    /// <returns>New CronExpression instance</returns>
    /**
     * @param hoursInterval
     * @return
     */
    public static QuartzCronExpression everyNHours(int hoursInterval)
    {
        return new QuartzCronExpression(hoursInterval, CronExpressionType.EVERY_N_HOURS);
    }

    /// <summary>
    /// Create new CronExpression instance, which occurs every day at specified hours
    /// </summary>
    /// <param name="hour">Hour, when occurence will happen</param>
    /// <param name="minute">Minute, when occurence will happen</param>
    /// <returns>New CronExpression instance</returns>
    /**
     * @param hour
     * @param minute
     * @return
     */
    public static QuartzCronExpression everyDayAt(int hour, int minute)
    {
        return new QuartzCronExpression(1, hour, minute, CronExpressionType.EVERY_DAY_AT);
    }

    /// <summary>
    /// Create new CronExpression instance, which occurs every *daysInterval* days at specified hours
    /// </summary>
    /// <param name="daysInterval">Interval in days</param>
    /// <param name="hour">Hour, when occurence will happen</param>
    /// <param name="minute">Minute, when occurence will happen</param>
    /// <returns>New CronExpression instance</returns>
    /**
     * @param daysInterval
     * @param hour
     * @param minute
     * @return
     */
    public static QuartzCronExpression everyNDaysAt(int daysInterval, int hour, int minute)
    {
        return new QuartzCronExpression(daysInterval, hour, minute, CronExpressionType.EVERY_N_DAYS_AT);
    }

    /// <summary>
    /// Create new CronExpression instance, which occurs monday to friday at specified hours
    /// </summary>
    /// <param name="hour">Hour, when occurence will happen</param>
    /// <param name="minute">Minute, when occurence will happen</param>
    /// <returns>New CronExpression instance</returns>
    /**
     * @param hour
     * @param minute
     * @return
     */
    public static QuartzCronExpression everyWeekDayAt(int hour, int minute)
    {
        return new QuartzCronExpression(hour, minute, CronExpressionType.EVERY_WEEK_DAY);
    }

    /// <summary>
    /// Create new CronExpression instance, which occurs on specified days at specified hours
    /// </summary>
    /// <param name="hour">Hour, when occurence will happen</param>
    /// <param name="minute">Minute, when occurence will happen</param>
    /// <param name="days">Days, when occurence will happen</param>
    /// <returns>New CronExpression instance</returns>
    /**
     * @param hour
     * @param minute
     * @param daysOfWeek
     * @return
     */
    public static QuartzCronExpression everySpecificWeekDayAt(int hour, int minute, Set<DaysOfWeek> daysOfWeek)
    {
        return new QuartzCronExpression(daysOfWeek, hour, minute, CronExpressionType.EVERY_SPECIFIC_WEEK_DAY_AT);
    }

    /// <summary>
    /// Create new CronExpression instance, which occurs every specific day of month 
    /// every *monthInterval* month at specified hours
    /// </summary>
    /// <param name="dayNumber">Day of the month, when occurence will happen</param>
    /// <param name="monthInterval">Interval in months</param>
    /// <param name="hour">Hour, when occurence will happen</param>
    /// <param name="minute">Minute, when occurence will happen</param>
    /// <returns>New CronExpression instance</returns>
    
    
    /**
     * @param dayNumber
     * @param monthInterval
     * @param hour
     * @param minute
     * @return
     */
    public static QuartzCronExpression everySpecificDayEveryNMonthAt(int dayNumber, int monthInterval, int hour, int minute)
    {
        return new QuartzCronExpression(dayNumber, monthInterval, hour, minute, CronExpressionType.EVERY_SPECIFIC_DAY_EVERY_N_MONTH_AT);
    }

    /// <summary>
    /// Create new CronExpression instance, which occurs on every first, 
    /// second, third or fourth day of the week each *monthInterval* months
    /// at specified hours
    /// </summary>
    /// <param name="dayNumber">Day sequental number (first, second, third, fourth)</param>
    /// <param name="days">Day of week</param>
    /// <param name="monthInverval">Interval in months</param>
    /// <param name="hour">Hour, when occurence will happen</param>
    /// <param name="minute">Minute, when occurence will happen</param>
    /// <returns>New CronExpression instance</returns>
    
    /**
     * @param daySeqNumber
     * @param dayOfWeek
     * @param monthInverval
     * @param hour
     * @param minute
     * @return
     */
    public static QuartzCronExpression everySpecificSeqWeekDayEveryNMonthAt(DaySeqNumber daySeqNumber, DaysOfWeek dayOfWeek, int monthInverval, int hour, int minute)
    {
        return new QuartzCronExpression(daySeqNumber, dayOfWeek, monthInverval, hour, minute, CronExpressionType.EVERY_SPECIFIC_SEQ_WEEK_DAY_EVERY_N_MONTH_AT);
    }

    /// <summary>
    /// Create new CronExpression instance, which occurs on every specific day *dayNumber* of 
    /// specific month *month* at specified hours
    /// </summary>
    /// <param name="month">Month</param>
    /// <param name="dayNumber">Day number</param>
    /// <param name="hour">Hour, when occurence will happen</param>
    /// <param name="minute">Minute, when occurence will happen</param>
    /// <returns>New CronExpression instance</returns>
    
    
    /**
     * @param months
     * @param dayNumber
     * @param hour
     * @param minute
     * @return
     */
    public static QuartzCronExpression everySpecificDayOfMonthAt(Set<Months> months, int dayNumber, int hour, int minute)
    {
        return new QuartzCronExpression(months, dayNumber, hour, minute, CronExpressionType.EVERY_SPECIFIC_DAY_OF_MONTH_AT);
    }

    /// <summary>
    /// Create new CronExpression instance, which occurs on every first, 
    /// second, third or fourth day of the week on specific month *month*
    /// at specified hours
    /// </summary>
    /// <param name="dayNumber">Day sequental number (first, second, third, fourth)</param>
    /// <param name="days">Day of week</param>
    /// <param name="month">Month</param>
    /// <param name="hour">Hour, when occurence will happen</param>
    /// <param name="minute">Minute, when occurence will happen</param>
    /// <returns>New CronExpression instance</returns>
    
    /**
     * @param daySeqNumber
     * @param dayOfWeek
     * @param month
     * @param hour
     * @param minute
     * @return
     */
    public static QuartzCronExpression everySpecificSeqWeekDayOfMonthAt(DaySeqNumber daySeqNumber, DaysOfWeek dayOfWeek, Set<Months> month, int hour, int minute)
    {
        return new QuartzCronExpression(daySeqNumber, dayOfWeek, month, hour, minute, CronExpressionType.EVERY_SPECIFIC_SEQ_WEEKDAY_OF_MONTH_AT);
    }


    /// <summary>
    /// Create new CronExpression instance, which occurs on specicf day 
    /// at specified hours
    /// </summary>
    /// <param name="dateTime">Date and time</param>
    /// <returns>New CronExpression instance</returns>

    
    /**
     * @param dateTime
     * @return
     */
    public static QuartzCronExpression specificDateAt(LocalDateTime dateTime)
    {
        return new QuartzCronExpression(
            dateTime.getSecond(),
            dateTime.getMinute(),
            dateTime.getHour(),
            dateTime.getDayOfMonth(),
            dateTime.getMonth().getValue(),
            dateTime.getYear(),
            CronExpressionType.SPECIFIC_DATE_AT);
    }

	@Override
    public String toString()
    {
        return cronString;
    }



}
