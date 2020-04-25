
public class Instruction {

	private int processID;
	private String operation;
	private int address;
	
	public Instruction() {}
	
	public Instruction(int p, String o, int a) {
		processID = p;
		operation = o;
		address = a;
	}

	public int getProcessID() {
		return processID;
	}

	public void setProcessID(int processID) {
		this.processID = processID;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

}
