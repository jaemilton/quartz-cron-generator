package br.com.jaemilton.quartz_cron_generator;

import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import java.util.EnumSet;

import org.junit.Test;

import br.com.jaemilton.quartz_cron_generator.enums.DaySeqNumber;
import br.com.jaemilton.quartz_cron_generator.enums.DaysOfWeek;
import br.com.jaemilton.quartz_cron_generator.enums.Months;

/**
 * Unit test for simple App.
 */
public class AppTest 
{


    @Test
    public void testEveryNSeconds()
    {
        var ce1 = QuartzCronExpression.everyNSeconds(1);
        var ce2 = QuartzCronExpression.everyNSeconds(59);
        var ce3 = QuartzCronExpression.everyNSeconds(3600);
        

        assertEquals("0/1 * * 1/1 * ? *", ce1.toString());
        assertEquals("0/59 * * 1/1 * ? *", ce2.toString());
        assertEquals("0/3600 * * 1/1 * ? *", ce3.toString());
    }

    @Test
    public void testEveryNMinutes()
    {
        var ce1 = QuartzCronExpression.everyNMinutes(1);
        var ce2 = QuartzCronExpression.everyNMinutes(59);
        var ce3 = QuartzCronExpression.everyNMinutes(3600);

        assertEquals("0 0/1 * 1/1 * ? *", ce1.toString());
        assertEquals("0 0/59 * 1/1 * ? *", ce2.toString());
        assertEquals("0 0/3600 * 1/1 * ? *", ce3.toString());
    }

    @Test
    public void testEveryNHours()
    {
        var ce1 = QuartzCronExpression.everyNHours(1);
        var ce2 = QuartzCronExpression.everyNHours(23);
        var ce3 = QuartzCronExpression.everyNHours(72);

        assertEquals("0 0 0/1 1/1 * ? *", ce1.toString());
        assertEquals("0 0 0/23 1/1 * ? *", ce2.toString());
        assertEquals("0 0 0/72 1/1 * ? *", ce3.toString());
    }

    @Test
    public void testEveryDayAt()
    {
        var ce1 = QuartzCronExpression.everyDayAt(12, 0);
        var ce2 = QuartzCronExpression.everyDayAt(7, 23);
        var ce3 = QuartzCronExpression.everyDayAt(22, 22);

        assertEquals("0 0 12 1/1 * ? *", ce1.toString());
        assertEquals("0 23 7 1/1 * ? *", ce2.toString());
        assertEquals("0 22 22 1/1 * ? *", ce3.toString());
    }

    @Test
    public void testEveryNDaysAt()
    {
        var ce1 = QuartzCronExpression.everyNDaysAt(1, 12, 0);
        var ce2 = QuartzCronExpression.everyNDaysAt(6, 7, 23);
        var ce3 = QuartzCronExpression.everyNDaysAt(30, 22, 22);

        assertEquals("0 0 12 1/1 * ? *", ce1.toString());
        assertEquals("0 23 7 1/6 * ? *", ce2.toString());
        assertEquals("0 22 22 1/30 * ? *", ce3.toString());
    }

    @Test
    public void testEveryWeekDay()
    {
        var ce1 = QuartzCronExpression.everyWeekDayAt(12, 0);
        var ce2 = QuartzCronExpression.everyWeekDayAt(7, 23);
        var ce3 = QuartzCronExpression.everyWeekDayAt(22, 22);

        assertEquals("0 0 12 ? * MON-FRI *", ce1.toString());
        assertEquals("0 23 7 ? * MON-FRI *", ce2.toString());
        assertEquals("0 22 22 ? * MON-FRI *", ce3.toString());
    }

    @Test
    public void testEverySpecificWeekDayAt()
    {
        var ce1 = QuartzCronExpression.everySpecificWeekDayAt(12, 0, DaysOfWeek.MONDAY.asEnumSet());
        var ce2 = QuartzCronExpression.everySpecificWeekDayAt(7, 23, EnumSet.of(DaysOfWeek.MONDAY, DaysOfWeek.WEDNESDAY, DaysOfWeek.FRIDAY));
        var ce3 = QuartzCronExpression.everySpecificWeekDayAt(22, 22,  EnumSet.of(DaysOfWeek.SATURDAY, DaysOfWeek.SUNDAY));
        var ce4 = QuartzCronExpression.everySpecificWeekDayAt(5, 20,  EnumSet.of(DaysOfWeek.THURSDAY, DaysOfWeek.TUESDAY));

        assertEquals("0 0 12 ? * MON *", ce1.toString());
        assertEquals("0 23 7 ? * MON,WED,FRI *", ce2.toString());
        assertEquals("0 22 22 ? * SUN,SAT *", ce3.toString());
        assertEquals("0 20 5 ? * TUE,THU *", ce4.toString());
    }

    @Test
    public void testDaysOfWeekRepresentation()
    {
        var monString = CronConverter.toCronRepresentationSingle(DaysOfWeek.MONDAY);
        assertEquals("MON", monString);

        EnumSet<DaysOfWeek> days = EnumSet.of(DaysOfWeek.MONDAY, DaysOfWeek.WEDNESDAY, DaysOfWeek.FRIDAY);

        String expectedString = "MON,WED,FRI";
        assertEquals(expectedString, CronConverter.daysOfWeekToCronRepresentation(days));

        String exprectedString2 = "SUN,SAT";
        assertEquals(exprectedString2, CronConverter.daysOfWeekToCronRepresentation( EnumSet.of(DaysOfWeek.SATURDAY , DaysOfWeek.SUNDAY)));
    }

    @Test
    public void testMothsRepresentation()
    {
        var julyString = CronConverter.toCronRepresentationSingle(Months.JULY);
        assertEquals("JUL", julyString);

        EnumSet<Months> months = EnumSet.of(Months.JANUARY, Months.FEBRUARY, Months.JUNE);

        String expectedString = "JAN,FEB,JUN";
        assertEquals(expectedString, CronConverter.monthsToCronRepresentation(months));

        String exprectedString2 = "JUL,DEC";
        assertEquals(exprectedString2, CronConverter.monthsToCronRepresentation( EnumSet.of(Months.DECEMBER , Months.JULY)));
    }

    @Test
    public void testEverySpecificDayEveryNMonthAt()
    {
        var ce1 = QuartzCronExpression.everySpecificDayEveryNMonthAt(1, 1, 12, 0);
        var ce2 = QuartzCronExpression.everySpecificDayEveryNMonthAt(7, 3, 7, 15);
        var ce3 = QuartzCronExpression.everySpecificDayEveryNMonthAt(28, 6, 21, 30);

        assertEquals("0 0 12 1 1/1 ? *", ce1.toString());
        assertEquals("0 15 7 7 1/3 ? *", ce2.toString());
        assertEquals("0 30 21 28 1/6 ? *", ce3.toString());
    }

    @Test
    public void testEverySpecificSeqWeekDayEveryNMonthAt()
    {
        var ce1 = QuartzCronExpression.everySpecificSeqWeekDayEveryNMonthAt(DaySeqNumber.FIRST, DaysOfWeek.MONDAY, 1, 12, 0);
        var ce2 = QuartzCronExpression.everySpecificSeqWeekDayEveryNMonthAt(DaySeqNumber.SECOND, DaysOfWeek.WEDNESDAY, 3, 7, 15);
        var ce3 = QuartzCronExpression.everySpecificSeqWeekDayEveryNMonthAt(DaySeqNumber.THIRD, DaysOfWeek.FRIDAY, 6, 21, 30);
        var ce4 = QuartzCronExpression.everySpecificSeqWeekDayEveryNMonthAt(DaySeqNumber.FOURTH, DaysOfWeek.SUNDAY, 77, 22, 45);
        var ce5 = QuartzCronExpression.everySpecificSeqWeekDayEveryNMonthAt(DaySeqNumber.FIFTH, DaysOfWeek.MONDAY, 77, 22, 45);

        assertEquals("0 0 12 ? 1/1 MON#1 *", ce1.toString());
        assertEquals("0 15 7 ? 1/3 WED#2 *", ce2.toString());
        assertEquals("0 30 21 ? 1/6 FRI#3 *", ce3.toString());
        assertEquals("0 45 22 ? 1/77 SUN#4 *", ce4.toString());
        assertEquals("0 45 22 ? 1/77 MON#5 *", ce5.toString());
    }

   

    @Test
    public void testEverySpecificSeqWeekDayOfMonthAt()
    {
        var ce1 = QuartzCronExpression.everySpecificSeqWeekDayOfMonthAt(DaySeqNumber.FIRST, DaysOfWeek.MONDAY, Months.JANUARY.asEnumSet(), 12, 0);
        var ce2 = QuartzCronExpression.everySpecificSeqWeekDayOfMonthAt(DaySeqNumber.SECOND, DaysOfWeek.WEDNESDAY, Months.FEBRUARY.asEnumSet(), 7, 15);
        var ce3 = QuartzCronExpression.everySpecificSeqWeekDayOfMonthAt(DaySeqNumber.THIRD, DaysOfWeek.FRIDAY, Months.AUGUST.asEnumSet(), 21, 30);
        var ce4 = QuartzCronExpression.everySpecificSeqWeekDayOfMonthAt(DaySeqNumber.FOURTH, DaysOfWeek.SUNDAY, Months.DECEMBER.asEnumSet(), 22, 45);

        assertEquals("0 0 12 ? JAN MON#1 *", ce1.toString());
        assertEquals("0 15 7 ? FEB WED#2 *", ce2.toString());
        assertEquals("0 30 21 ? AUG FRI#3 *", ce3.toString());
        assertEquals("0 45 22 ? DEC SUN#4 *", ce4.toString());
    }


    @Test
    public void testEverySpecificDayOfMonthAt()
    {
        var ce1 = QuartzCronExpression.everySpecificDayOfMonthAt(Months.APRIL.asEnumSet(), 1, 10, 0);
        var ce2 = QuartzCronExpression.everySpecificDayOfMonthAt(EnumSet.of(Months.JANUARY, Months.JULY), 3, 3, 0);
        var ce3 = QuartzCronExpression.everySpecificDayOfMonthAt(EnumSet.of(Months.DECEMBER, Months.JANUARY), 6, 1, 10);
        var ce4 = QuartzCronExpression.everySpecificDayOfMonthAt(EnumSet.of(Months.JANUARY, Months.MARCH, Months.JUNE, Months.SEPTEMBER), 25, 23, 0);

        assertEquals("0 0 10 1 APR ? *", ce1.toString());
        assertEquals("0 0 3 3 JAN,JUL ? *", ce2.toString());
        assertEquals("0 10 1 6 JAN,DEC ? *", ce3.toString());
        assertEquals("0 0 23 25 JAN,MAR,JUN,SEP ? *", ce4.toString());
    }

    @Test
    public void testSpecificDateAt()
    {
        var ce1 = QuartzCronExpression.specificDateAt(LocalDateTime.of(2020, 1, 1, 8, 30, 0));
        var ce2 = QuartzCronExpression.specificDateAt(LocalDateTime.of(2019, 2, 1, 0, 0, 0));
        var ce3 = QuartzCronExpression.specificDateAt(LocalDateTime.of(2022, 3, 27, 9, 0, 0));
        var ce4 = QuartzCronExpression.specificDateAt(LocalDateTime.of(2015, 4, 1, 7, 0, 30));
        var ce5 = QuartzCronExpression.specificDateAt(LocalDateTime.of(2020, 5, 1, 8, 30, 0));
        var ce6 = QuartzCronExpression.specificDateAt(LocalDateTime.of(2019, 6, 1, 0, 0, 0));
        var ce7 = QuartzCronExpression.specificDateAt(LocalDateTime.of(2022, 7, 27, 9, 0, 0));
        var ce8 = QuartzCronExpression.specificDateAt(LocalDateTime.of(2015, 8, 1, 7, 0, 30));
        var ce9 = QuartzCronExpression.specificDateAt(LocalDateTime.of(2020, 9, 1, 8, 30, 0));
        var ce10 = QuartzCronExpression.specificDateAt(LocalDateTime.of(2019, 10, 1, 0, 0, 0));
        var ce11 = QuartzCronExpression.specificDateAt(LocalDateTime.of(2022, 11, 27, 9, 0, 0));
        var ce12 = QuartzCronExpression.specificDateAt(LocalDateTime.of(2015, 12, 1, 7, 0, 30));

        assertEquals("0 30 8 1 JAN ? 2020", ce1.toString());
        assertEquals("0 0 0 1 FEB ? 2019", ce2.toString());
        assertEquals("0 0 9 27 MAR ? 2022", ce3.toString());
        assertEquals("30 0 7 1 APR ? 2015", ce4.toString());
        assertEquals("0 30 8 1 MAY ? 2020", ce5.toString());
        assertEquals("0 0 0 1 JUN ? 2019", ce6.toString());
        assertEquals("0 0 9 27 JUL ? 2022", ce7.toString());
        assertEquals("30 0 7 1 AUG ? 2015", ce8.toString());
        assertEquals("0 30 8 1 SEP ? 2020", ce9.toString());
        assertEquals("0 0 0 1 OCT ? 2019", ce10.toString());
        assertEquals("0 0 9 27 NOV ? 2022", ce11.toString());
        assertEquals("30 0 7 1 DEC ? 2015", ce12.toString());
    }
}
