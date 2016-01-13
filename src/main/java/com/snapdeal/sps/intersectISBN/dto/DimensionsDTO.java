package com.snapdeal.sps.intersectISBN.dto;

public class DimensionsDTO {

	String length;
	String breadth;
	String hieght;
	
	
	@Override
	public String toString() {
		return "DimensionsDTO [length=" + length + ", breadth=" + breadth
				+ ", hieght=" + hieght + "]";
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
	public String getHieght() {
		return hieght;
	}
	public void setHieght(String hieght) {
		this.hieght = hieght;
	}
	public DimensionsDTO(String length, String breadth, String hieght) {
		super();
		this.length = length;
		this.breadth = breadth;
		this.hieght = hieght;
	}
	public DimensionsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
