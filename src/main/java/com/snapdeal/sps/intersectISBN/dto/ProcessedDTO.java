package com.snapdeal.sps.intersectISBN.dto;

import java.util.List;

public class ProcessedDTO {
	
	List<FileFields> acceptedRecords;
	List<RejectedDTO> rejectedRecords;
	
	
	@Override
	public String toString() {
		return "ProcessedDTO [acceptedRecords=" + acceptedRecords
				+ ", rejectedRecords=" + rejectedRecords + "]";
	}
	public ProcessedDTO(List<FileFields> acceptedRecords,
			List<RejectedDTO> rejectedRecords) {
		super();
		this.acceptedRecords = acceptedRecords;
		this.rejectedRecords = rejectedRecords;
	}
	
	public void setAllAcceptedRecords(List<FileFields> acceptedRecords) {
		this.acceptedRecords.addAll(acceptedRecords);
	}
	
	public void setAllRejectedRecords(List<RejectedDTO> rejectedRecords) {
		this.rejectedRecords.addAll(rejectedRecords);
	}
	
	public List<FileFields> getAcceptedRecords() {
		return acceptedRecords;
	}
	public void setAcceptedRecords(List<FileFields> acceptedRecords) {
		this.acceptedRecords = acceptedRecords;
	}
	public List<RejectedDTO> getRejectedRecords() {
		return rejectedRecords;
	}
	public void setRejectedRecords(List<RejectedDTO> rejectedRecords) {
		this.rejectedRecords = rejectedRecords;
	}
	public ProcessedDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
