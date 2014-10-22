package course.legacy;

import static org.junit.Assert.*;

import org.junit.Test;

import course.legacy.pointofsale.Inventory;

public class InventoryTest {

	@Test(expected=RuntimeException.class)
	public void returnExceptionWhenItemNotFoundWithBarcode() {
		Inventory inventory = new Inventory();
		assertNull(inventory.itemForBarcode(""));
	}

}
