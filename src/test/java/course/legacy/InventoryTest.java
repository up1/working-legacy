package course.legacy;

import static org.junit.Assert.*;

import org.junit.Test;

import course.legacy.pointofsale.Inventory;

public class InventoryTest {

	@Test
	public void returnNullWhenItemNotFoundWithBarcode() {
		Inventory inventory = new Inventory();
		assertNull(inventory.itemForBarcode(""));
	}

}
