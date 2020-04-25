import java.util.ArrayList;
import java.util.List;

import org.omg.PortableServer.POA;

public class RAMControl {

	private RAM ram;
	
	public RAMControl(RAM r) {
		ram = r;
	}
	
	public Process findProcessInRAMId(int pID) {
		for(Process p:ram.getProcessList()) {
			if(p.getProcessId() == pID) return p;
		}
		return null;
	}
	
	public Process findProcessInRAMPage(Page pa) {
		for(Process p:ram.getProcessList()) {
			if(p.getProcessId() == pa.getpID()) return p;
		}
		return null;
	}
	
	public Process LowestTimeProcess() {
		int min = Integer.MAX_VALUE;
		int processId = -1;
		
		for(Process p:ram.getProcessList()) {
			if(p.getLastAcces() < min) {
				min = p.getLastAcces();
				processId = p.getProcessId();
			}
		}
		return findProcessInRAMId(processId);
	}
	
	public void addProcess(Process process) {
		if(ram.getProcessListSize() >= 4) {
			removeProcess(LowestTimeProcess().getProcessId());
		}
		ram.addProces(process);
		updateAllFrames();
	}
	
	public void removeProcess(int pID) {
		Process p = new Process(pID);
		for(Process pr:ram.getProcessList()) {
			if(pr.getProcessId() == pID) p = pr;
		}
		for(int i = 0; i < ram.getFrameCount(); i++) {
			if(ram.getFrameValueArray()[i] != null) {
				if(ram.getFrameValueArray()[i].getpID() == p.getProcessId()) removePage(ram.getFrameValueArray()[i]);
			}
		}
		ram.removeProcess(p);
	}
	
	public void removePage(Page page) {
		Process p = findProcessInRAMPage(page);
		int number = p.getLijst().get(page.getPageNumber()).getTableEntry().getFrameNumber();
		p.update(page.getPageNumber(), number, false, false);
		ram.setFrameToNull(number);
	}
	
	public void updateAllFrames() {
		if(ram.getProcessListSize() > 0) {
			int frameCount = ram.getFrameCount()/ram.getProcessListSize();
			for(Process p:ram.getProcessList()) {
				while (p.getPagesInRam() != frameCount) {
					if(p.getPagesInRam() > frameCount) {
						removePage(p.getLeastRecentUsedPage());
					}
					else {
						Page np = p.findNonPresentPage();
						boolean nextEmpty = false;
						int empty = 0;
						while(!nextEmpty) {
							if(ram.getFrameValueArray()[empty] ==null) {
								nextEmpty=true;
							}
							else {
								empty++;
							}
						}
						ram.setFrameValueArraySingleEntry(empty, np);
						p.update(np.getPageNumber(), empty, true, false);
					}
				}
			}
		}
	}
	
	public void newAccesT(int time, Process p, List<Integer> adr) {
		p.getLijst().get(adr.get(0)).getTableEntry().setModifyBit(true);
		p.getLijst().get(adr.get(0)).getTableEntry().setLastAccessTime(time);;		
	}
	
	public void swap(int oldPage, Page p) {
		Page old = ram.getFrameValueArray()[oldPage];
		if(old != null) {
			Process oldPP = findProcessInRAMPage(old);
			oldPP.update(old.getPageNumber(), 0, false, false);
			oldPP.addOneToRamCount();
		}
		Process newPP = findProcessInRAMPage(p);
		newPP.update(p.getPageNumber(), oldPage, true, false);
		newPP.addOneFromRamCount();
		ram.setFrameValueArraySingleEntry(oldPage, p);
	}
	
	public Page[] getFrames() {
		if(ram !=null) return ram.getFrameValueArray();
		return null;
	}
	
	public RAM getRAM() {
		return ram;
	}
	
	public void setRAM(RAM r) {
		ram = r;
	}
	
	public boolean isRamNull() {
		if(ram!=null)return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
