import java.util.*;

public class MainControl {

	private InstructionControl instructionControl;
	private Instruction currentI;
	private Queue<Instruction> wachtRij;
	private List<Instruction> finished;
	private InstructionProcessor instrProcessor;
	private List<Process> lijst;
	private Process currentP;
	private List<Integer> address;
	private int time;
	
	
	public MainControl() {
		instructionControl = new InstructionControl(new RAM(12));
		finished = new ArrayList<>();
		instrProcessor = new InstructionProcessor();
		wachtRij = new PriorityQueue<>();
		lijst = new ArrayList<>();
		currentI = null;
		currentP = null;
		time = 0;
		address = null;
	}
	
	public void lees(String filename) {
		wachtRij = new LinkedList<>();
		wachtRij = instrProcessor.getInstructions(filename);
	}
	
	public List<Integer> translate(int input) {
		List<Integer> out = new ArrayList<>();
		String binair = Integer.toString(input,2);
		int zeros = 16-binair.length();
		String translation = "";
		for(int i = 0; i < zeros; i++) {
			translation += "0";
		}
		translation += binair;
		String page = translation.substring(0,translation.length()-12);
		String off = translation.substring(translation.length()-12);
		int []address = new int[] {Integer.parseInt(page,2),Integer.parseInt(off,2)};
		
		for(int i = 0; i < address.length; i++) {
			out.add(address[i]);
		}
		return out;
	}
	
	public void setCurrentProcess(int pID) {
		for(Process p:lijst) {
			if(p.getProcessId() == pID) currentP = p;
		}
	}
	
	public void execute() {
		String op = currentI.getOperation();
		if(op.equals("Start")) {
			address = null;
			currentP = new Process(currentI.getProcessID());
			lijst.add(currentP);
			instructionControl.start(currentP);
		}
		else if(op.equals("Read")) {
			address = translate(currentI.getAddress());
			setCurrentProcess(currentI.getProcessID());
			instructionControl.read(currentI.getProcessID(), time, address);
		}
		else if(op.equals("Write")) {
			address = translate(currentI.getAddress());
			setCurrentProcess(currentI.getProcessID());
			instructionControl.write(currentI.getProcessID(), time, address);
		}
		else {
			setCurrentProcess(currentI.getProcessID());
			address = null;
			instructionControl.terminate(currentI.getProcessID());
		}
		time++;
	}
	
	public Integer convert(int pN, int off) {
		int fN = currentP.getFrameNumber(pN);
		return fN*16 + off;
	}
	
	public void cancel() {
		instructionControl = new InstructionControl(new RAM(12));
		finished = new ArrayList<>();
		instrProcessor = new InstructionProcessor();
		wachtRij = new PriorityQueue<>();
		lijst = new ArrayList<>();
		currentI = null;
		currentP = null;
		time = 0;
		address = null;
	}
	
	public void initialise() {
		instructionControl = new InstructionControl(new RAM(12));
		finished = new ArrayList<>();
		instrProcessor = new InstructionProcessor();
		wachtRij = new PriorityQueue<>();
		lijst = new ArrayList<>();
		currentI = null;
		currentP = null;
		time = 0;
		address = null;
	}
	
	public void executeOne(String val) {
		if(wachtRij.isEmpty()) lees(val);
		if(wachtRij.peek()!= null) {
			currentI = wachtRij.poll();
			execute();
			finished.add(currentI);
		}
	}
	
	public void executeQueue(String val) {
		if(wachtRij.isEmpty()) lees(val);
		while(wachtRij.peek()!=null) {
			currentI = wachtRij.poll();
			execute();
			finished.add(currentI);
		}
	}
	
	public Page[] getFrames() {
		return instructionControl.getRamControl().getFrames();
	}
	
	public int getTime() {
		return time;
	}
	
	public List<Process> getAllProcesses() {
		if(!instructionControl.getRamControl().isRamNull()) {
			return lijst;
		}
		return null;
	}
	
	public Process getCurrentP() {
		return currentP;
	}
	
	public Instruction getCurrentI() {
		return currentI;
	}
	
	public List<Process> getProcessesList(){
		return lijst;
	}
}
