
public class TableEntry {
	private int pageNumber;
	private boolean presentBit;
	private boolean modifyBit;
	private int lastAccessTime;
	private int frameNumber; 	//in interval [0,12]
	
	public TableEntry() {
		presentBit = false;
		modifyBit = false;
		lastAccessTime = -1;
		frameNumber = 0;
	}
	
	public TableEntry(int pageNumber, boolean presentBit, boolean modifyBit, int lastAccessTime, int frameNumber) {
		this.pageNumber = pageNumber;
		this.modifyBit = modifyBit;
		this.presentBit = presentBit;
		this.lastAccessTime = lastAccessTime;
		this.frameNumber = frameNumber;
	}
	
	public TableEntry(TableEntry t) {
		this.pageNumber = t.pageNumber;
		this.modifyBit = t.modifyBit;
		this.presentBit = t.presentBit;
		this.lastAccessTime = t.lastAccessTime;
		this.frameNumber = t.frameNumber;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public boolean isPresentBit() {
		return presentBit;
	}

	public void setPresentBit(boolean presentBit) {
		this.presentBit = presentBit;
	}

	public boolean isModifyBit() {
		return modifyBit;
	}

	public void setModifyBit(boolean modifyBit) {
		this.modifyBit = modifyBit;
	}

	public int getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(int lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(int frameNumber) {
		this.frameNumber = frameNumber;
	}
	
	
}
