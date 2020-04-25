import java.util.ArrayList;
import java.util.List;

public class Process {

	private int processId;
	private int lastAcces;
	private List<PageTableEntry> lijst;
	private int toRamCount;
	private int fromRamCount;
	
	public Process(int p) {
		processId = p;
		lijst = new ArrayList<>();
		for(int i = 0; i < 16; i++) {
			lijst.add(new PageTableEntry(new Page(processId, i), new TableEntry()));
		}
		toRamCount = 0;
		fromRamCount = 0;
	}
	
	//Getters - Setters
	
	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public int getLastAcces() {
		return lastAcces;
	}

	public void setLastAcces(int lastAcces) {
		this.lastAcces = lastAcces;
	}
	
	public void addOneToRamCount() {
		toRamCount++;
	}
	
	public void addOneFromRamCount() {
		fromRamCount++;
	}
	
	public List<PageTableEntry> getLijst() {
		return lijst;
	}

	public void setLijst(List<PageTableEntry> lijst) {
		this.lijst = lijst;
	}

	public int getToRamCount() {
		return toRamCount;
	}

	public void setToRamCount(int toRamCount) {
		this.toRamCount = toRamCount;
	}

	public int getFromRamCount() {
		return fromRamCount;
	}

	public void setFromRamCount(int fromRamCount) {
		this.fromRamCount = fromRamCount;
	}
	
	//Methoden

	public void update(int pageN, int frameN, boolean presentB, boolean modifyB) {
		TableEntry x = lijst.get(pageN).getTableEntry();
		x.setFrameNumber(frameN);
		x.setPresentBit(presentB);
		x.setModifyBit(modifyB);
	}
	
	public int getFrameNumber(int pageNumber) {
		for(PageTableEntry pte : lijst) {
			if(pte.getPage().getPageNumber() == pageNumber) return pte.getTableEntry().getFrameNumber();
		}
		return 0;
	}
	
	public int getPagesInRam() {
		int count = 0;
		for(PageTableEntry pte : lijst) {
			if(pte.getTableEntry().isPresentBit()) count++;
		}
		return count;
	}
	
	public Page findNonPresentPage() {
		for(PageTableEntry pte : lijst) {
			if(!pte.getTableEntry().isPresentBit()) return pte.getPage();
		}
		return null;
	}
	
	public int getLeastRecentUsedFrameNumber() {
		TableEntry old = new TableEntry();
		for(PageTableEntry pte : lijst) {
			if(pte.getTableEntry().isPresentBit() && pte.getTableEntry().getLastAccessTime() < old.getLastAccessTime()) {
				old = pte.getTableEntry();
			}
		}
		return old.getFrameNumber();
	}
	
	public Page getLeastRecentUsedPage() {
		int index = -1;
		int min = Integer.MAX_VALUE;
		Page p = null;
		for(PageTableEntry pte : lijst) {
			if(pte.getTableEntry().isPresentBit() && pte.getTableEntry().getLastAccessTime() < min) {
				min = pte.getTableEntry().getLastAccessTime();
				p = pte.getPage();	
			}
		}
		return p;
	}
	
	public boolean checkPresentBitOfPage(List<Integer> adress){
        return lijst.get(adress.get(0)).getTableEntry().isPresentBit();
    }
}
