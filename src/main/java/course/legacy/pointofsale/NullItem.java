package course.legacy.pointofsale;

public class NullItem extends Item {

	public NullItem() {
		super("Item not found", new Money(0));
	}
	
	public boolean isReal() {
		return true;
	}

}
