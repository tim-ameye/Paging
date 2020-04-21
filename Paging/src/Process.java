import java.util.ArrayList;
import java.util.List;

public class Process {

	private int processID;
	private int lastAccesTime;
	private int readRAM;
	private int writeRAM;
	
	List<Page> pageList = new ArrayList<>();
	List<PageTableEntry> pageTable = new ArrayList<>();
	
	public Process(int pID) {
		this.processID = pID;
		pageList();
		pageTable();
		readRAM = 0;
		writeRAM = 0;
	}
	
	private void pageList() {
		for (int i = 0; i < 16; i++) {
			this.pageList.add(new Page(this.processID, i));
		}
	}
	
	private void pageTable() {
		for (int i = 0; i < 16; i++) {
			this.pageTable.add(new PageTableEntry());
		}
	}

	public int getLastAccesTime() {
		return lastAccesTime;
	}

	public void setLastAccesTime(int lastAccesTime) {
		this.lastAccesTime = lastAccesTime;
	}

	public int getProcessID() {
		return processID;
	}

	public int getReadRAM() {
		return readRAM;
	}

	public int getWriteRAM() {
		return writeRAM;
	}
	
	public void addReadRAM() {
		
	}
}
