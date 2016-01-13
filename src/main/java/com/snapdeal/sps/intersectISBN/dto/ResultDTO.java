package com.snapdeal.sps.intersectISBN.dto;

public class ResultDTO {
	
	int totalRecords;
	int accptedRecords;
	int rejectedRecords;
	String totalRecordsInFile;
	
	
	@Override
	public String toString() {
		return "InputTextDTO [totalRecords=" + totalRecords
				+ ", accptedRecords=" + accptedRecords + ", rejectedRecords="
				+ rejectedRecords + ", totalRecordsInFile="
				+ totalRecordsInFile + "]";
	}

	
	public String getTotalRecordsInFile() {
		return totalRecordsInFile;
	}

	public void setTotalRecordsInFile(String totalRecordsInFile) {
		this.totalRecordsInFile = totalRecordsInFile;
	}


	public int getAccptedRecords() {
		return accptedRecords;
	}

	public void setAccptedRecords(int accptedRecords) {
		this.accptedRecords = accptedRecords;
	}

	public int getRejectedRecords() {
		return rejectedRecords;
	}

	public void setRejectedRecords(int rejectedRecords) {
		this.rejectedRecords = rejectedRecords;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

}
