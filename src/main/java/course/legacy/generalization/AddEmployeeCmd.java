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

	public AddEmployeeCmd(String name, String address, String city, String state, int yearlySalary) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.yearlySalary = Integer.toString(yearlySalary);
		
		datas.add(name);
		datas.add(address);
		datas.add(city);
		datas.add(state);
		datas.add(this.yearlySalary);
		
		commandChar[0] = 0x02;
	}

}
