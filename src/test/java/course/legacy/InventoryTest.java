package course.legacy;

import static org.junit.Assert.*;

import org.junit.Test;

import course.legacy.pointofsale.Inventory;
import course.legacy.pointofsale.Item;
import course.legacy.pointofsale.ItemNotFoundException;
import course.legacy.pointofsale.NullItem;
import course.legacy.pointofsale.Told;

public class InventoryTest {

//	@Test
//	public void returnExceptionWhenItemNotFoundWithBarcode() {
//		Inventory inventory = new Inventory();
//		assertNull(inventory.itemForBarcode(""));
//	}
	
	Item toldItem = new NullItem();
	
	@Test
	public void itemIsTold() throws Exception {
		Inventory inventory = new Inventory();
		inventory.item("3", new Told<Item>(){
			public void tell(Item item) {
				toldItem = item;
			}
		});
		assertEquals("Milk", toldItem.getName());
	}

}
