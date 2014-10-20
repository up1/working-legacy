package course.legacy;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import course.legacy.scheduler.DayTime;
import course.legacy.scheduler.Event;
import course.legacy.scheduler.Scheduler;

public class SchedulerTest {

	@Test
	public void testCreate() {
		Date now = new Date();
		new Scheduler("somkiat").addEvent(new Event(now, DayTime.Time10AM));
	}

}
