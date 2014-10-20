package course.legacy;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import course.legacy.scheduler.DayTime;
import course.legacy.scheduler.Display;
import course.legacy.scheduler.Event;
import course.legacy.scheduler.MailService;
import course.legacy.scheduler.Meeting;
import course.legacy.scheduler.Scheduler;

class FakeDisplay implements Display {

	public void showEvent(Event event) {
	}
	
}

class TestingScheduler extends Scheduler {

	public TestingScheduler(String owner, Display display) {
		super(owner, display);
	}
	
	protected void sendMail(String address, String subject, String message) {
	}
	
}


public class SchedulerTest {

	@Test
	public void createScheduler() {
		Date now = new Date();
		new TestingScheduler("somkiat", new FakeDisplay()).addEvent(new Event(now, DayTime.Time10AM));
	}

}
