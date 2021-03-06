package course.legacy.pointofsale;

import java.util.ArrayList;

public class Sale {
	private SaleEventListener listener;
	private Inventory inventory = new Inventory();
	private ArrayList<Item> items = new ArrayList<Item>();

	public Sale(SaleEventListener listener) {
		this.listener = listener;
	}

	public void addBarcode(String barcode) throws ItemNotFoundException {
		inventory.item(barcode, new Told<Item>() {
			public void tell(Item item) {
				items.add(item);
				listener.itemAdded(item);
			}
		});
	}

	public void subtotal() {
		Money sum = new Money();
		for (Item item : items) {
			sum = sum.add(item.getPrice(items));
		}
		listener.subtotaled(sum);
	}

	public void total() {
		Money sum = new Money();
		for (Item item : items) {
			sum = sum.add(item.getTaxedPrice(items));
		}
		listener.totalled(sum);
	}
}
