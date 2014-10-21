package course.legacy.homeguard;

public class Packet {

	private String id;
	private String status;

	public Packet(String packet) {
		String[] tokens = packet.split(",");
		this.id = tokens[0];
		this.status = tokens[1];
	}

	public String getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

}
