package com.snapdeal.sps.intersectISBN.dto;

public class DecisionDTO {

	boolean valid;
	String rejectReason;
	
	@Override
	public String toString() {
		return "DecisionDTO [valid=" + valid + ", rejectReason=" + rejectReason
				+ "]";
	}
	public DecisionDTO(boolean valid, String rejectReason) {
		super();
		this.valid = valid;
		this.rejectReason = rejectReason;
	}
	public DecisionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	
	
	
}
