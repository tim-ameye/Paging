
public class PageTableEntry {

	private boolean presentBit;
	private boolean modifyBit;
	private int lastAccesTime;
	private int frameNumber;
	
//Getters - Setters	
	
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
	public int getLastAccesTime() {
		return lastAccesTime;
	}
	public void setLastAccesTime(int lastAccesTime) {
		this.lastAccesTime = lastAccesTime;
	}
	public int getFrameNumber() {
		return frameNumber;
	}
	public void setFrameNumber(int frameNumber) {
		this.frameNumber = frameNumber;
	}	
}
