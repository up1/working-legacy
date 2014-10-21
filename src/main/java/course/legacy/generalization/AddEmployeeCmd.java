package course.legacy.generalization;

/*
 * Created by IntelliJ IDEA.
 * User: bbutton
 * Date: Aug 1, 2002
 * Time: 9:34:33 AM
 * To change template for new class use 
 * Code Style | Class Templates options (Tools | IDE Options).
 */

import java.io.OutputStream;

public class AddEmployeeCmd extends Command {
	String name;
	String address;
	String city;
	String state;
	String yearlySalary;

	private static final byte[] commandChar = { 0x02 };

	public int getSize() {
		return header.length + SIZE_LENGTH + CMD_BYTE_LENGTH + footer.length + name.getBytes().length + 1 + address.getBytes().length + 1 + city.getBytes().length + 1 + state.getBytes().length + 1
				+ yearlySalary.getBytes().length + 1;
	}

	public AddEmployeeCmd(String name, String address, String city, String state, int yearlySalary) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.yearlySalary = Integer.toString(yearlySalary);
	}

	public void writeBody(OutputStream outputStream) throws Exception {
		outputStream.write(commandChar);
		outputStream.write(name.getBytes());
		outputStream.write(0x00);
		outputStream.write(address.getBytes());
		outputStream.write(0x00);
		outputStream.write(city.getBytes());
		outputStream.write(0x00);
		outputStream.write(state.getBytes());
		outputStream.write(0x00);
		outputStream.write(yearlySalary.getBytes());
		outputStream.write(0x00);
	}

}
