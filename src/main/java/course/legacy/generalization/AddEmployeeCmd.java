package course.legacy.generalization;

/*
 * Created by IntelliJ IDEA.
 * User: bbutton
 * Date: Aug 1, 2002
 * Time: 9:34:33 AM
 * To change template for new class use 
 * Code Style | Class Templates options (Tools | IDE Options).
 */


public class AddEmployeeCmd extends Command {

	public AddEmployeeCmd(String name, String address, String city, String state, int yearlySalary) {
		datas.add(name);
		datas.add(address);
		datas.add(city);
		datas.add(state);
		datas.add(Integer.toString(yearlySalary));

		commandChar[0] = 0x02;
	}

}
