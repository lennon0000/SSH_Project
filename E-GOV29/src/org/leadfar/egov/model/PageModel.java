package org.leadfar.egov.model;

public class PageModel {
	private int pageNo = 1;
	private int pageSize = 2;
	private int rowCount;
	private int pageCount;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		this.pageCount = (this.rowCount % this.pageSize == 0) ? (this.rowCount / this.pageSize)
				: (this.rowCount / this.pageSize + 1);
		if (this.pageNo > this.pageCount) {
			this.pageNo = this.pageCount;
		}
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getEndIndex() {
		return this.pageNo * this.pageSize;
	}

	public int getStartIndex() {
		return (this.pageNo - 1) * this.pageSize;
	}

}
