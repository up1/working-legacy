package course.legacy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import course.legacy.homeguard.AudibleAlarm;
import course.legacy.homeguard.CentralUnit;
import course.legacy.homeguard.DoorSensor;
import course.legacy.homeguard.HomeGuardView;
import course.legacy.homeguard.Sensor;

public class CentralUnitTest {

	class FakeView implements HomeGuardView {

		public void showMessage(String message) {
		}

	}

	class FakeAudibleAlarm implements AudibleAlarm {

		public int count = 0;

		public void sound() {
			count++;
		}

		public void silence() {
		}

	}

	@Test
	public void parseparseRadioBroadcastWithTRIPPED() throws Exception {
		CentralUnit centralUnit = new CentralUnit();
		centralUnit.registerSensor(new DoorSensor("1", "", Sensor.DOOR));
		centralUnit.setView(new FakeView());
		centralUnit.setAudibleAlarm(new FakeAudibleAlarm());

		centralUnit.parseRadioBroadcast("1,TRIPPED");
	}

	@Test
	public void parseparseRadioBroadcastWithTRIPPEDAndArm() throws Exception {
		CentralUnit centralUnit = new CentralUnit();
		centralUnit.registerSensor(new DoorSensor("1", "", Sensor.DOOR));
		centralUnit.setView(new FakeView());
		FakeAudibleAlarm alarm = new FakeAudibleAlarm();
		centralUnit.setAudibleAlarm(alarm);
		centralUnit.arm();

		centralUnit.parseRadioBroadcast("1,TRIPPED");
		assertTrue(alarm.count == 1);
	}

	@Test
	public void parseparseRadioBroadcastWithTRIPPEDAndNotArm() throws Exception {
		CentralUnit centralUnit = new CentralUnit();
		centralUnit.registerSensor(new DoorSensor("1", "", Sensor.DOOR));
		centralUnit.setView(new FakeView());
		FakeAudibleAlarm alarm = new FakeAudibleAlarm();
		centralUnit.setAudibleAlarm(alarm);

		centralUnit.parseRadioBroadcast("1,TRIPPED");
		assertTrue(alarm.count == 0);
	}

}
