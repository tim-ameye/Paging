import java.util.ArrayList;
import java.util.List;

public class RAM {

	List<Process> pList = new ArrayList<>();
	List<Page> frameList = new ArrayList<>(12);
	private int frames;
	
	public RAM(int f) {
		this.frames = f;
	}
	
	public void addProcess(Process process, int timer) {
		if(pList.size()>=4) {
			int min = Integer.MAX_VALUE;
			int index = -1;
			for (int i = 0; i < pList.size(); i++) {
				if(pList.get(i).getLastAccesTime() < min) {
			}
		}
	}
	
}
