
public class Page {
	
	private int frameNumber;
	private int pID;
	private int pageNumber;
	
	public Page(int pID, int pageNumber) {
		this.pID = pID;
		this.pageNumber = pageNumber;
	}
	
	public Page(int pID, int pageNumber, int frameNumber) {
		this.pID = pID;
		this.pageNumber = pageNumber;
		this.frameNumber = frameNumber;
	}
	
	public Page(Page page) {
		this.pID = page.pID;
		this.pageNumber = page.pageNumber;
		this.frameNumber = page.frameNumber;
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(int frameNumber) {
		this.frameNumber = frameNumber;
	}

	public int getPID() {
		return pID;
	}

	public void setPID(int pID) {
		this.pID = pID;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	

}
