package br.com.jaemilton.quartz_cron_generator.enums;

import java.util.EnumSet;

public enum DaysOfWeek {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(7),
    SATURDAY(7);
    
	
	private int id;
	DaysOfWeek(int id)
	{
		this.id = id;
	}
	
	public int getId() {
		return id;
    }
    
    public EnumSet<DaysOfWeek> asEnumSet() {
		return EnumSet.of(this);
	}
}
