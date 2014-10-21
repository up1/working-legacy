package course.legacy.generalization;

import java.io.IOException;
import java.io.OutputStream;

public abstract class Command {

	protected static final byte[] header = { (byte) 0xde, (byte) 0xad };
	protected static final byte[] footer = { (byte) 0xbe, (byte) 0xef };
	protected static final int SIZE_LENGTH = 1;
	protected static final int CMD_BYTE_LENGTH = 1;

	public void write(OutputStream outputStream) throws Exception {
		writeHeader(outputStream);
		outputStream.write(getSize());
		writeBody(outputStream);
		writeFooter(outputStream);
	}

	public abstract int getSize();

	public abstract void writeBody(OutputStream outputStream) throws Exception;

	private void writeFooter(OutputStream outputStream) throws IOException {
		outputStream.write(footer);
	}

	private void writeHeader(OutputStream outputStream) throws IOException {
		outputStream.write(header);
	}

}
