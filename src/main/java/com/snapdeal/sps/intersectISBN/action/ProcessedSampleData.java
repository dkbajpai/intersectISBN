package com.snapdeal.sps.intersectISBN.action;

import java.util.List;

public class ProcessedSampleData {

	private List<SampleData> acceptedList;
	private List<SampleData> rejectedList;
	
	
	public List<SampleData> getAcceptedList() {
		return acceptedList;
	}
	public void setAcceptedList(List<SampleData> acceptedList) {
		this.acceptedList = acceptedList;
	}
	public List<SampleData> getRejectedList() {
		return rejectedList;
	}
	public void setRejectedList(List<SampleData> rejectedList) {
		this.rejectedList = rejectedList;
	}
	public ProcessedSampleData(List<SampleData> acceptedList,
			List<SampleData> rejectedList) {
		super();
		this.acceptedList = acceptedList;
		this.rejectedList = rejectedList;
	}
}
