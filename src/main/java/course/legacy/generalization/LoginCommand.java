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

	private static final byte[] commandChar = { 0x01 };

	public LoginCommand(String userName, String passwd) {
		this.userName = userName;
		this.passwd = passwd;
	}

	public int getSize() {
		return header.length + SIZE_LENGTH + CMD_BYTE_LENGTH + footer.length + userName.getBytes().length + 1 + passwd.getBytes().length + 1;
	}

	public void writeBody(OutputStream outputStream) throws Exception {
		outputStream.write(commandChar);
		outputStream.write(userName.getBytes());
		outputStream.write(0x00);
		outputStream.write(passwd.getBytes());
		outputStream.write(0x00);
	}

}
