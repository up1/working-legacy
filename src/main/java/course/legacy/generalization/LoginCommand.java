package course.legacy.generalization;

/*
 * Created by IntelliJ IDEA.
 * User: bbutton
 * Date: Jul 31, 2002
 * Time: 11:30:29 PM
 * To change template for new class use
 * Code Style | Class Templates options (Tools | IDE Options).
 */


public class LoginCommand extends Command {

	public LoginCommand(String userName, String passwd) {
		datas.add(userName);
		datas.add(passwd);
		commandChar[0] = 0x01;
	}

}
