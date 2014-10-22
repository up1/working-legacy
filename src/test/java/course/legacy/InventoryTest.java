package course.legacy;

import static org.junit.Assert.*;

import org.junit.Test;

import course.legacy.pointofsale.Inventory;
import course.legacy.pointofsale.ItemNotFoundException;

public class InventoryTest {

	@Test
	public void returnExceptionWhenItemNotFoundWithBarcode() {
		Inventory inventory = new Inventory();
		assertNull(inventory.itemForBarcode(""));
	}

}
