import java.util.List;

public class InstructionControl {

	private RAMControl ramControl;
	
	public InstructionControl(RAM ram) {
		ramControl = new RAMControl(ram);
	}
	
	public void start(Process cProcess) {
		ramControl.addProcess(cProcess);
	}
	
	public void read(int pID, int time, List<Integer> adr) {
		Process p = ramControl.findProcessInRAMId(pID);
		if(p==null) ramControl.addProcess(p);
		support(p, time, adr);
		p.setLastAcces(time);
	}
	
	public void write(int pID, int time, List<Integer> adr) {
		Process p = ramControl.findProcessInRAMId(pID);
		if(p==null) {
			p = new Process(pID);
			ramControl.addProcess(p);
		}
		support(p, time, adr);
		p.setLastAcces(time);
	}
	
	public void terminate(int pID) {
		ramControl.removeProcess(pID);
		ramControl.updateAllFrames();
	}
	
	public void support(Process p, int time, List<Integer> adr) {
		if(p.checkPresentBitOfPage(adr)) ramControl.newAccesT(time, p, adr);
		else {
			Page old = p.getLeastRecentUsedPage();
			int oldP = p.getLijst().get(old.getPageNumber()).getTableEntry().getFrameNumber();
			ramControl.swap(oldP, p.getLijst().get(adr.get(0)).getPage());
			p.update(p.getLijst().get(old.getPageNumber()).getPage().getPageNumber(), oldP, true, true);
			p.getLijst().get(adr.get(0)).getTableEntry().setLastAccessTime(time);
		}
	}

	public RAMControl getRamControl() {
		return ramControl;
	}

	public void setRamControl(RAMControl ramControl) {
		this.ramControl = ramControl;
	}
	
}
