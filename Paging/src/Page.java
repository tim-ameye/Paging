
public class Page {

	private int processID;
	private int pageNumber;
	
	public Page(int pr, int pa) {
		processID = pr;
		pageNumber = pa;
	}

	public int getProcessID() {
		return processID;
	}

	public int getPageNumber() {
		return pageNumber;
	}	
}
