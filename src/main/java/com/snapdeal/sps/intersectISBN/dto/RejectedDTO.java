package com.snapdeal.sps.intersectISBN.dto;

public class RejectedDTO {

	FileFields fileFields;
	String reason;
	
	public FileFields getFileFields() {
		return fileFields;
	}
	public void setFileFields(FileFields fileFields) {
		this.fileFields = fileFields;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "RejectedDTO [fileFields=" + fileFields + ", reason=" + reason
				+ "]";
	}
	public RejectedDTO(FileFields fileFields, String reason) {
		super();
		this.fileFields = fileFields;
		this.reason = reason;
	}
	public RejectedDTO() {
		super();
	}
	
	
}
