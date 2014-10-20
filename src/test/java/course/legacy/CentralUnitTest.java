package course.legacy;

import static org.junit.Assert.*;

import org.junit.Test;

import course.legacy.homeguard.CentralUnit;
import course.legacy.homeguard.Sensor;

public class CentralUnitTest {

	public static void assertSensorMessages(String type, String nonTrippedMessage, String trippedMessage) {
		{
			CentralUnit centralUnit = new CentralUnit();
			Sensor sensor = new Sensor("0", "<x>", type);
			assertEquals(nonTrippedMessage, centralUnit.getSensorMessage(sensor));
		}
		{
			CentralUnit centralUnit = new CentralUnit();
			Sensor sensor = new Sensor("0", "<x>", type);
			sensor.trip();
			assertEquals(trippedMessage, centralUnit.getSensorMessage(sensor));
		}
	}

	@Test
	public void sensorMessage() throws Exception {
		assertSensorMessages(Sensor.DOOR, "<x> is closed", "<x> is open");
		assertSensorMessages(Sensor.WINDOW, "<x> is sealed", "<x> is ajar");
		assertSensorMessages(Sensor.MOTION, "<x> is motionless", "Motion detected in <x>");
		assertSensorMessages(Sensor.FIRE, "<x> temperature is normal", "<x> is on FIRE!");
	}
}
