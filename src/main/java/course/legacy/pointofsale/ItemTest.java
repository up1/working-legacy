package course.legacy.pointofsale;
import static org.junit.Assert.assertEquals;

import org.junit.Test;



public class ItemTest {
	
	@Test
	public void canGenerateDisplayLine() {
		assertEquals("Milk $70.00", new Item("Milk", new Money(7000)).getDisplayLine());
	}

}
