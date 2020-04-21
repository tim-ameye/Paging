import java.util.ArrayList;
import java.util.List;

public class RAM {
	
	private List<Process> processList;
	private Page[] frameValueArray;
	private int frameCount;
	
	public RAM(int framecount) {
		frameValueArray = new Page[12];
		processList = new ArrayList<>();
		this.frameCount = framecount;
	}

	public List<Process> getProcessList() {
		return processList;
	}

	public void setProcessList(List<Process> processList) {
		this.processList = processList;
	}

	public Page[] getFrameValueArray() {
		return frameValueArray;
	}

	public void setFrameValueArray(Page[] frameValueArray) {
		this.frameValueArray = frameValueArray;
	}

	public int getFrameCount() {
		return frameCount;
	}

	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}
	
	public void addProces(Process process) {
		processList.add(process);
	}
	
	public void removeProcess(Process process) {
		processList.remove(process);
	}
	
	public int getProcessListSize() {
		return processList.size();
	}
	
	public int getFrameValueArrayCount() {
		return frameValueArray.length;
	}
	
	public void setFrameToNull(int i) {
		frameValueArray[i] = null;
	}
	
	public void setFrameValueArraySingleEntry(int i, Page page) {
		frameValueArray[i] = page;
	}
	

	
}
