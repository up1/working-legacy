package course.legacy.generalization;

/*
 * Created by IntelliJ IDEA.
 * User: bbutton
 * Date: Jul 31, 2002
 * Time: 11:30:29 PM
 * To change template for new class use
 * Code Style | Class Templates options (Tools | IDE Options).
 */

import java.io.IOException;
import java.io.OutputStream;

public class LoginCommand extends Command {

	private String userName;
	private String passwd;

	public LoginCommand(String userName, String passwd) {
		this.userName = userName;
		this.passwd = passwd;
		
		datas.add(this.userName);
		datas.add(this.passwd);
		commandChar[0] = 0x01;
	}

}
