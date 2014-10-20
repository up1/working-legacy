package course.legacy;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import course.legacy.scheduler.DayTime;
import course.legacy.scheduler.Display;
import course.legacy.scheduler.Event;
import course.legacy.scheduler.Scheduler;

class FakeDisplay implements Display {

	public void showEvent(Event event) {
	}
	
}


public class SchedulerTest {

	@Test
	public void createScheduler() {
		Date now = new Date();
		new Scheduler("somkiat", new FakeDisplay()).addEvent(new Event(now, DayTime.Time10AM));
	}

}
