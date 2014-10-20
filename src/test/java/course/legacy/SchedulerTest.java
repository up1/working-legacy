package course.legacy;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import course.legacy.scheduler.DayTime;
import course.legacy.scheduler.Display;
import course.legacy.scheduler.Event;
import course.legacy.scheduler.MailService;
import course.legacy.scheduler.Meeting;
import course.legacy.scheduler.Scheduler;
import course.legacy.scheduler.TimeServices;

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

	Scheduler scheduler;

	@Before
	public void init() {
		scheduler = new TestingScheduler("somkiat", new FakeDisplay());
	}

	TimeServices nonHolidayTimeService = new TimeServices() {
		public boolean isDateAHoliday(Date date) {
			return false;
		}
	};

	@Test
	public void createScheduler() {
		Date now = new Date();
		scheduler.addEvent(new Meeting(now, DayTime.Time10AM, "For test"));
	}

	@Test
	public void returnMeetingWhenNotHoliday() throws Exception {
		Date now = new Date();
		Scheduler scheduler = new TestingScheduler("somkiat", new FakeDisplay());
		scheduler.addEvent(new Meeting(now, DayTime.Time10AM, "For test"));
		assertEquals("For test", scheduler.getMeeting(now, DayTime.Time10AM, nonHolidayTimeService).getText());
	}
}