package com.snapdeal.sps.intersectISBN.dto;

public class DimensionsDTO {

	String length;
	String breadth;
	String height;
	
	
	@Override
	public String toString() {
		return "DimensionsDTO [length=" + length + ", breadth=" + breadth
				+ ", hieght=" + height + "]";
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getBreadth() {
		return breadth;
	}
	public void setBreadth(String breadth) {
		this.breadth = breadth;
	}
	
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public DimensionsDTO(String length, String breadth, String hieght) {
		super();
		this.length = length;
		this.breadth = breadth;
		this.height = hieght;
	}
	public DimensionsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
