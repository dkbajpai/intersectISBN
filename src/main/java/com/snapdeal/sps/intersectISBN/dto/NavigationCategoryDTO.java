package com.snapdeal.sps.intersectISBN.dto;

public class NavigationCategoryDTO {

	String productCategory;
	String navigationCategory;
	
	
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getNavigationCategory() {
		return navigationCategory;
	}
	public void setNavigationCategory(String navigationCategory) {
		this.navigationCategory = navigationCategory;
	}
	public NavigationCategoryDTO(String productCategory,
			String navigationCategory) {
		super();
		this.productCategory = productCategory;
		this.navigationCategory = navigationCategory;
	}
	@Override
	public String toString() {
		return "NavigationCategoryDTO [productCategory=" + productCategory
				+ ", navigationCategory=" + navigationCategory + "]";
	}
	
	
	
}
