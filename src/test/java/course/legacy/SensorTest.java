package course.legacy;

import static org.junit.Assert.*;

import org.junit.Test;

import course.legacy.homeguard.CentralUnit;
import course.legacy.homeguard.Sensor;

public class SensorTest {

	public static void assertSensorMessages(String type, String nonTrippedMessage, String trippedMessage) {
		{
			Sensor sensor = new Sensor("0", "<x>", type);
			assertEquals(nonTrippedMessage, sensor.getMessage());
		}
		{
			Sensor sensor = new Sensor("0", "<x>", type);
			sensor.trip();
			assertEquals(trippedMessage, sensor.getMessage());
		}
	}

	@Test
	public void sensorMessage() throws Exception {
		SensorTest.assertSensorMessages(Sensor.DOOR, "<x> is closed", "<x> is open");
		SensorTest.assertSensorMessages(Sensor.WINDOW, "<x> is sealed", "<x> is ajar");
		SensorTest.assertSensorMessages(Sensor.MOTION, "<x> is motionless", "Motion detected in <x>");
		SensorTest.assertSensorMessages(Sensor.FIRE, "<x> temperature is normal", "<x> is on FIRE!");
	}

}
