package course.legacy.generalization;

public class AddEmployeeCmd extends Command {

	public AddEmployeeCmd(String name, String address, String city, String state, int yearlySalary) {
		commandChar[0] = 0x02;
		datas.add(name);
		datas.add(address);
		datas.add(city);
		datas.add(state);
		datas.add(Integer.toString(yearlySalary));
	}

}
