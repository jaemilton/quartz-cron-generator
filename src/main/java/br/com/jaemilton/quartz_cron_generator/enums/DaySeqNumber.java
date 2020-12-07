package br.com.jaemilton.quartz_cron_generator.enums;


public enum DaySeqNumber {
	FIRST(1),
	SECOND(2),
	THIRD(3),
    FOURTH(4),
    FIFTH(5);
	
	private int id;
	DaySeqNumber(int id)
	{
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
