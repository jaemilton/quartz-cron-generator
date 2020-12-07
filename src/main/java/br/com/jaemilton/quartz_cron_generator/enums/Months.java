package br.com.jaemilton.quartz_cron_generator.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Months  {

	JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

	private int id;
	Months(int id)
	{
		this.id = id;
	}
	
	public int getId() {
		return id;
    }
    
    public EnumSet<Months> asEnumSet() {
		return EnumSet.of(this);
    }
	
	private static final Map<Integer, Months> monthsMap;
	
	static {
        monthsMap = new HashMap<>(12);
		for (Months month : Months.values()) {
			monthsMap.put(month.getId(), month);
		}
	}
	
	public static Months fromId(int id)
	{
		return monthsMap.get(Integer.valueOf(id));
	}
	
}
