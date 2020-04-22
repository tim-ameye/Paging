
public class PageTableEntry {
	Page page;
	TableEntry tableEntry;
	
	public PageTableEntry(Page p, TableEntry t) {
		this.page = p;
		this.tableEntry = t;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public TableEntry getTableEntry() {
		return tableEntry;
	}

	public void setTableEntry(TableEntry tableEntry) {
		this.tableEntry = tableEntry;
	}
	
	

}
