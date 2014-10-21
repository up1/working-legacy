package course.legacy.generalization;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class Command {

	protected static final byte[] header = { (byte) 0xde, (byte) 0xad };
	protected static final byte[] footer = { (byte) 0xbe, (byte) 0xef };
	protected static final int SIZE_LENGTH = 1;
	protected static final int CMD_BYTE_LENGTH = 1;
	private static final int DEFAULT_LENGTH = header.length + SIZE_LENGTH + CMD_BYTE_LENGTH + footer.length;
	protected byte[] commandChar = { 0x01 };

	protected List<String> datas = new ArrayList<String>();

	public int getSize() {
		int length = 0;
		for (String data : datas) {
			length += data.length() + 1;
		}
		return length;
	}

	public void write(OutputStream outputStream) throws Exception {
		writeHeader(outputStream);
		outputStream.write(DEFAULT_LENGTH + getSize());
		outputStream.write(commandChar);
		writeData(outputStream);
		writeFooter(outputStream);
	}

	protected void writeData(OutputStream outputStream) throws IOException {
		for (String data : datas) {
			outputStream.write(data.getBytes());
			outputStream.write(0x00);
		}
	}

	private void writeFooter(OutputStream outputStream) throws IOException {
		outputStream.write(footer);
	}

	private void writeHeader(OutputStream outputStream) throws IOException {
		outputStream.write(header);
	}

}
